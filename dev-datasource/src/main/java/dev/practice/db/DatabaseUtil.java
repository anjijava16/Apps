package dev.practice.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.practice.util.NumberUtil;
import dev.practice.util.StringUtil;

public final class DatabaseUtil {

  private static final Log log = LogFactory.getLog(DatabaseUtil.class);

  /**
   * Returns length of database table POJO column.
   * 
   * @param type
   *          The POJO class.
   * @param columnName
   *          The column name.
   * @return Column length. Returns 0 if not found.
   */
  public static int getColumnLength(Class<?> type, String columnName) {
    if (type == null || StringUtil.isEmpty(columnName))
      return 0;

    int length = 0;
    Column column = getColumn(type, columnName);

    if (column != null) {
      length = column.length();
    }

    return length;
  }

  /**
   * Get the column name from property annotation.
   * 
   * E.g. fileNoAlpha --> FILE_NO_ALPHA file --> FILE
   * 
   * @param columnName
   *          the property to convert.
   * @return Column name from property. Returns null if not found.
   */
  public static String getColumnName(Class<?> type, String columnName) {
    if (type == null || StringUtil.isEmpty(columnName))
      return null;

    String name = null;
    Column column = getColumn(type, columnName);

    if (column != null) {
      name = column.name();
    }

    return name;
  }

  /**
   * Get the table name from annotation of the given class.
   * 
   * @param <T>
   *          generic type.
   * @param type
   *          the entity type.
   * @return
   */
  public static <T> String getTableName(Class<T> type) {
    Table tab = type.getAnnotation(Table.class);

    if (tab != null) {
      return tab.name();
    }

    return null;
  }

  /**
   * Returns javax.persistence.Column from specific POJO class for the specified
   * column name.
   * 
   * @param type
   *          The POJO class.
   * @param columnName
   *          The column name.
   * @return Column from name. Returns null if not found.
   */
  public static Column getColumn(Class<?> type, String columnName) {
    Column column = null;

    // Try to get Column from specified type.
    try {
      column = type.getDeclaredField(columnName).getAnnotation(Column.class);
    }
    catch (NoSuchFieldException e) {
    }

    // If not found, try to get from superclass.
    if (column == null) {
      try {
        column = type.getSuperclass().getDeclaredField(columnName).getAnnotation(Column.class);
      }
      catch (NoSuchFieldException e) {
      }
    }

    // FIXME This is ugly. This was done for specific cases where the POJO class has a 2 level superclass.
    // For example: SecurityPreferences -> AbstractSecurityPreferences -> AbstractPreferences.
    // If not found, try to get from superclass's superclass.
    if (column == null) {
      try {
        Class<?> superClass = type.getSuperclass();
        column = superClass.getSuperclass().getDeclaredField(columnName).getAnnotation(Column.class);
      }
      catch (NoSuchFieldException e) {
      }
    }

    // If still not found, try to find in PK class.
    if (column == null) {
      try {
        Field idFld = type.getDeclaredField("id");
        Class<?>[] interfaces = idFld.getType().getSuperclass().getInterfaces();
        boolean found = false;

        for (Class<?> intf : interfaces) {
          if (intf == IEntityId.class) {
            found = true;
            break;
          }
        }

        if (found) {
          Field[] idFields = idFld.getType().getDeclaredFields();

          for (Field fld : idFields) {
            if (fld.getName().equals(columnName)) {
              column = fld.getAnnotation(Column.class);
            }
          }
        }
      }
      catch (NoSuchFieldException e) {
      }
    }

    return column;
  }

  public static String columnNameToPropertyName(String columnName) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < columnName.length(); i++) {
      Character c = columnName.charAt(i);
      if (c == '_') {
        c = columnName.charAt(++i);
        sb.append(Character.toUpperCase(c));
      }
      else {
        sb.append(Character.toLowerCase(c));
      }
    }
    return sb.toString();
  }

  /**
   * <b>USE EntityUtil methods instead</b><br>
   * Truncates the field values of the specified POJO object to the field
   * lengths defined by the column annotations in the POJO class definitions.
   * 
   * NOTE: This only handles non-PK fields. PK fields will need to be truncated
   * manually.
   * 
   * @param entity
   *          The database POJO object.
   */
  @Deprecated
  public static void truncateFields(Object entity) {
    Class<?> entityClass = entity.getClass();
    Field[] fields = entityClass.getSuperclass().getDeclaredFields();

    for (Field field : fields) {
      String fieldName = field.getName();
      int colLength = getColumnLength(entityClass, fieldName);
      //      log.debug(name + " " + colLength + " " + field.getType());

      if (colLength > 0) {
        StringBuilder getterName = new StringBuilder();
        getterName.append("get");
        getterName.append(StringUtil.toUpperCase(StringUtil.substring(fieldName, 0, 1)));
        getterName.append(StringUtil.substring(fieldName, 1, StringUtil.length(fieldName) - 1));

        StringBuilder setterName = new StringBuilder();
        setterName.append("set");
        setterName.append(StringUtil.toUpperCase(StringUtil.substring(fieldName, 0, 1)));
        setterName.append(StringUtil.substring(fieldName, 1, StringUtil.length(fieldName) - 1));

        try {
          Method getter = entityClass.getMethod(getterName.toString());
          Method setter = null;
          Object truncValue = null;
          //          log.debug("Current: " + getter.invoke(entity));

          if (field.getType() == String.class) {
            setter = entityClass.getMethod(setterName.toString(), String.class);
            truncValue = StringUtil.truncateToFieldSize((String) getter.invoke(entity), entityClass, fieldName);
            setter.invoke(entity, truncValue);
          }
          else if (field.getType() == Integer.class) {
            setter = entityClass.getMethod(setterName.toString(), Integer.class);
            truncValue = NumberUtil.truncateToFieldSize((Integer) getter.invoke(entity), entityClass, fieldName);
            setter.invoke(entity, truncValue);
          }
          else if (field.getType() == Long.class) {
            setter = entityClass.getMethod(setterName.toString(), Long.class);
            truncValue = NumberUtil.truncateToFieldSize((Long) getter.invoke(entity), entityClass, fieldName);
            setter.invoke(entity, truncValue);
          }
          else if (field.getType() == BigDecimal.class) {
            setter = entityClass.getMethod(setterName.toString(), BigDecimal.class);
            truncValue = NumberUtil.truncateToFieldSize((BigDecimal) getter.invoke(entity), entityClass, fieldName);
            setter.invoke(entity, truncValue);
          }
          else {
            setter = entityClass.getMethod(setterName.toString(), field.getType());
            setter.invoke(entity, getter.invoke(entity));
          }

          //          log.debug("Truncated: " + truncValue);
        }
        catch (SecurityException e) {
          log.error("Error", e);
        }
        catch (NoSuchMethodException e) {
          log.error("Error", e);
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
