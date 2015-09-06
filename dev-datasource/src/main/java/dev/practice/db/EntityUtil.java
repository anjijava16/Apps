package dev.practice.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.practice.util.NumberUtil;
import dev.practice.util.StringUtil;

/**
 * Provides utility methods to truncate entity fields to the database column
 * sizes.
 * 
 * <br>
 * <b>NOTE:</b> This only handles non-PK fields. PK fields will need to be
 * truncated manually.
 * 
 * @author KondalReddy
 */
public final class EntityUtil {

  private static final Log                             log                   = LogFactory.getLog(EntityUtil.class);
  private static final Long                            INITIAL_LONG_VALUE    = 0L;
  private static final Integer                         INITIAL_INTEGER_VALUE = 0;
  public static int                                    MODE_PERSIST          = 1;
  public static int                                    MODE_LOAD             = 2;
  public static int                                    MODE_FIND             = 3;

  private static Map<Class<?>, Map<String, FieldInfo>> entityMap             = new HashMap<Class<?>, Map<String, FieldInfo>>();

  /**
   * Truncate specified field of specified entity object to database column
   * size.
   * 
   * @param type
   *          Entity class.
   * @param entity
   *          Entity object.
   * @param field
   *          Field name.
   */
  public static void truncateField(Class<?> type, Object entity, String field) {
    if (type != null && entity != null && entity.getClass() == type && !StringUtil.isEmpty(field)) {
      Map<String, FieldInfo> map = entityMap.get(type);

      if (map == null) {
        loadFieldInfo(type);
        map = entityMap.get(type);
      }

      FieldInfo fieldInfo = map.get(field);

      if (fieldInfo != null) {
        try {
          if (fieldInfo.getFieldType() == String.class) {
            String value = (String) fieldInfo.getGetter().invoke(entity);
            String truncValue = StringUtil.substring(value, 0, fieldInfo.getLength());
            fieldInfo.getSetter().invoke(entity, truncValue);
          }
          else if (fieldInfo.getFieldType() == Integer.class) {
            Integer value = (Integer) fieldInfo.getGetter().invoke(entity);
            //KC-4539
            if (value == null) {
              String truncValue = StringUtil.substring(StringUtil.toString(value), 0, fieldInfo.getLength());
              fieldInfo.getSetter().invoke(entity, NumberUtil.parseInt(truncValue));
            }
            else {
              int sign = Integer.signum(value);//kc-4288
              String truncValue = StringUtil.substring(StringUtil.toString(Math.abs(value)), 0, fieldInfo.getLength());
              fieldInfo.getSetter().invoke(entity, NumberUtil.parseInt(truncValue) * sign);
            }
          }
          else if (fieldInfo.getFieldType() == Long.class) {
            Long value = (Long) fieldInfo.getGetter().invoke(entity);
            //KC-4539
            if (value == null) {
              String truncValue = StringUtil.substring(StringUtil.toString(value), 0, fieldInfo.getLength());
              fieldInfo.getSetter().invoke(entity, NumberUtil.parseLong(truncValue));
            }
            else {
              int sign = Long.signum(value);//kc-4288
              String truncValue = StringUtil.substring(StringUtil.toString(Math.abs(value)), 0, fieldInfo.getLength());
              fieldInfo.getSetter().invoke(entity, NumberUtil.parseLong(truncValue) * sign);
            }
          }
          else if (fieldInfo.getFieldType() == BigDecimal.class) {
            BigDecimal value = (BigDecimal) fieldInfo.getGetter().invoke(entity);
            if (value == null)
              value = BigDecimal.ZERO;
            int scale = value.scale();
            long longValue = NumberUtil.toUnscaledLong(value);
            int sign = Long.signum(longValue);//kc-4288
            String truncValue = StringUtil
                .substring(StringUtil.toString(Math.abs(longValue)), 0, fieldInfo.getLength());
            long truncLong = NumberUtil.parseLong(truncValue) * sign;
            fieldInfo.getSetter().invoke(entity, BigDecimal.valueOf(truncLong, scale));
          }
          else {
            Object value = fieldInfo.getGetter().invoke(entity);
            fieldInfo.getSetter().invoke(entity, value);
          }
        }
        catch (IllegalArgumentException e) {
          log.error("Error", e);
        }
        catch (IllegalAccessException e) {
          log.error("Error", e);
        }
        catch (InvocationTargetException e) {
          log.error("Error", e);
        }
      }
    }
  }

  /**
   * Truncate all fields of specified entity object to database column sizes.
   * 
   * @param type
   *          Entity class.
   * @param entity
   *          Entity object.
   */
  public static void truncateRecord(Class<?> type, Object entity) {
    if (type != null && entity != null && entity.getClass() == type) {
      log.trace("Start truncate record");
      Map<String, FieldInfo> map = entityMap.get(type);

      if (map == null) {
        loadFieldInfo(type);
        map = entityMap.get(type);
      }

      Iterator<Entry<String, FieldInfo>> iter = map.entrySet().iterator();

      while (iter.hasNext()) {
        Entry<String, FieldInfo> entry = iter.next();
        truncateField(type, entity, entry.getKey());
      }

      log.trace("End truncate record");
    }
  }

  private synchronized static void loadFieldInfo(Class<?> type) {
    Map<String, FieldInfo> map = entityMap.get(type);
    if (map != null)
      return;

    if (log.isTraceEnabled())
      log.trace("Start initialize " + type.getName());

    map = new HashMap<String, FieldInfo>();
    List<Field> fieldList = new ArrayList<Field>();
    findColumnFields(type, fieldList);

    for (Field field : fieldList) {
      String fieldName = field.getName();
      Method getter = getGetterMethod(type, getGetterName(fieldName));
      Method setter = getSetterMethod(type, field, getSetterName(fieldName));
      int length = DatabaseUtil.getColumnLength(type, fieldName);

      if (getter != null && setter != null && length > 0) {
        log.trace(fieldName + ", " + field.getType() + ", " + length);
        FieldInfo fieldInfo = new FieldInfo(field.getType(), getter, setter, length);
        map.put(fieldName, fieldInfo);
      }
      else {
        log.trace(fieldName + ": no getter/setter or size available");
      }
    }

    entityMap.put(type, map);

    if (log.isTraceEnabled())
      log.trace("End initialize");
  }

  private static void findColumnFields(Class<?> type, List<Field> fieldList) {
    Field[] fields = type.getDeclaredFields();

    for (Field field : fields) {
      Column column = null;

      try {
        column = type.getDeclaredField(field.getName()).getAnnotation(Column.class);
      }
      catch (SecurityException e) {
      }
      catch (NoSuchFieldException e) {
      }

      if (column != null) {
        fieldList.add(field);
      }
    }

    if (type.getSuperclass() != null) {
      findColumnFields(type.getSuperclass(), fieldList);
    }
  }

  private static String getGetterName(String field) {
    StringBuilder getterName = new StringBuilder();
    getterName.append("get");
    getterName.append(StringUtil.toUpperCase(StringUtil.substring(field, 0, 1)));
    getterName.append(StringUtil.substring(field, 1, StringUtil.length(field) - 1));
    return getterName.toString();
  }

  private static String getSetterName(String field) {
    StringBuilder setterName = new StringBuilder();
    setterName.append("set");
    setterName.append(StringUtil.toUpperCase(StringUtil.substring(field, 0, 1)));
    setterName.append(StringUtil.substring(field, 1, StringUtil.length(field) - 1));
    return setterName.toString();
  }

  private static Method getGetterMethod(Class<?> type, String methodName) {
    Method method = null;

    try {
      method = type.getMethod(methodName);
    }
    catch (SecurityException e) {
      log.error("Error", e);
    }
    catch (NoSuchMethodException e) {
      //intentionally left blank to ignore the error
    }

    return method;
  }

  private static Method getSetterMethod(Class<?> type, Field field, String methodName) {
    Method method = null;

    try {
      if (field.getType() == String.class) {
        method = type.getMethod(methodName, String.class);
      }
      else if (field.getType() == Integer.class) {
        method = type.getMethod(methodName, Integer.class);
      }
      else if (field.getType() == Long.class) {
        method = type.getMethod(methodName, Long.class);
      }
      else if (field.getType() == BigDecimal.class) {
        method = type.getMethod(methodName, BigDecimal.class);
      }
      else {
        method = type.getMethod(methodName, field.getType());
      }
    }
    catch (SecurityException e) {
      log.error("Error", e);
    }
    catch (NoSuchMethodException e) {
      //intentionally left blank to ignore the error
    }

    return method;
  }

  private static class FieldInfo {

    private Class<?> fieldType = null;
    private Method   getter    = null;
    private Method   setter    = null;
    private int      length    = 0;

    public FieldInfo(Class<?> fieldType, Method getter, Method setter, int length) {
      this.fieldType = fieldType;
      this.getter = getter;
      this.setter = setter;
      this.length = length;
    }

    public Class<?> getFieldType() {
      return fieldType;
    }

    public Method getGetter() {
      return getter;
    }

    public Method getSetter() {
      return setter;
    }

    public int getLength() {
      return length;
    }

  }

  public static <T> void nullSafe(final Class<T> clazz, final Object entity, int mode) {
    if (clazz != null && entity != null && entity.getClass() == clazz) {
      Map<String, FieldInfo> map = entityMap.get(clazz);

      if (map == null) {
        loadFieldInfo(clazz);
        map = entityMap.get(clazz);
      }

      Iterator<Entry<String, FieldInfo>> iter = map.entrySet().iterator();

      while (iter.hasNext()) {
        Entry<String, FieldInfo> entry = iter.next();
        FieldInfo fieldInfo = entry.getValue();

        if (fieldInfo != null) {
          try {
            if (fieldInfo.getFieldType() == String.class) {
              String value = (String) fieldInfo.getter.invoke(entity);
              if (mode == MODE_PERSIST) {
                if (value != null && value.length() == 0)
                  fieldInfo.getSetter().invoke(entity, (String) null);
                else if (!StringUtil.isEmpty(value))
                  fieldInfo.getSetter().invoke(entity, value.trim());
              }
              else if (mode == MODE_LOAD) {
                fieldInfo.getSetter().invoke(entity, StringUtil.trim(value));
              }
              else if (mode == MODE_FIND)
                if (value == null || value.length() == 0)
                  fieldInfo.getSetter().invoke(entity, " ");
            }
            else if (fieldInfo.getFieldType() == Integer.class) {
              Integer value = (Integer) fieldInfo.getGetter().invoke(entity);
              if (value == null)
                fieldInfo.getSetter().invoke(entity, INITIAL_INTEGER_VALUE);
            }
            else if (fieldInfo.getFieldType() == Long.class) {
              Long value = (Long) fieldInfo.getGetter().invoke(entity);
              if (value == null)
                fieldInfo.getSetter().invoke(entity, INITIAL_LONG_VALUE);
            }
          }
          catch (IllegalArgumentException e) {
            log.error("Error", e);
          }
          catch (IllegalAccessException e) {
            log.error("Error", e);
          }
          catch (InvocationTargetException e) {
            log.error("Error", e);
          }
        }
      }
    }
  }

  // KC-3770
  public static <T> void padProperties(final Class<T> clazz, Map<String, Object> properties) {
    Map<String, FieldInfo> map = entityMap.get(clazz);

    if (map == null) {
      loadFieldInfo(clazz);
      map = entityMap.get(clazz);
    }

    for (Map.Entry<String, Object> e : properties.entrySet()) {
      String property = e.getKey();
      Object value = e.getValue();

      FieldInfo fieldInfo = map.get(property);
      if (fieldInfo != null) {
        if (fieldInfo.getFieldType() == String.class) {
          String strVal = (String) value;
          e.setValue(StringUtil.rpad(strVal, fieldInfo.getLength()));
        }
        else if (fieldInfo.getFieldType() == Integer.class) {
          Integer intVal = (Integer) value;
          if (intVal == null)
            e.setValue(INITIAL_INTEGER_VALUE);
        }
        else if (fieldInfo.getFieldType() == Long.class) {
          Long lngVal = (Long) value;
          if (lngVal == null)
            e.setValue(INITIAL_LONG_VALUE);
        }
      }
    }
  }

}
