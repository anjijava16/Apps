package dev.practice.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Number utility.
 * 
 * @author joseph.gagnon
 */
/**
 * @author KondalReddy
 * 
 */
public class NumberUtil {

  public static final Double KG_TO_LB_RATIO = 0.45359237;

  /**
   * Returns Integer value parsed from specified string. If string is null or
   * not a numeric value, zero is returned.
   * 
   * @param value
   *          The string to parse.
   * @return The Integer value.
   */
  public static Integer parseInt(String value) {
    try {
      return Integer.parseInt(value);
    }
    catch (Exception e) {
      return Integer.valueOf(0);
    }
  }

  /**
   * Returns Integer value parsed from specified object. If object is null or
   * not a numeric value, zero is returned.
   * 
   * @param value
   *          The object to parse.
   * @return The Integer value.
   */
  public static Integer parseInt(Object value) {
    try {
      return Integer.parseInt(value.toString());
    }
    catch (Exception e) {
      return Integer.valueOf(0);
    }
  }

  /**
   * Returns Long value parsed from specified string. If string is null or not a
   * numeric value, zero is returned.
   * 
   * @param value
   *          The string to parse.
   * @return The Long value.
   */
  public static Long parseLong(String value) {
    try {
      return Long.parseLong(value);
    }
    catch (Exception e) {
      return Long.valueOf(0L);
    }
  }

  /**
   * Returns Long value parsed from specified object. If object is null or not a
   * numeric value, zero is returned.
   * 
   * @param value
   *          The object to parse.
   * @return The Long value.
   */
  public static Long parseLong(Object value) {
    if (value instanceof BigDecimal) {
      return ((BigDecimal) value).longValue();
    }
    try {
      return Long.parseLong(value.toString());
    }
    catch (Exception e) {
      return Long.valueOf(0L);
    }
  }

  /**
   * Returns Float value parsed from specified string. If string is null or not
   * a numeric value, zero is returned.
   * 
   * @param value
   *          The string to parse.
   * @return The Float value.
   */
  public static Float parseFloat(String value) {
    try {
      return Float.parseFloat(value);
    }
    catch (Exception e) {
      return new Float(0.0F);
    }
  }

  /**
   * Returns Float value parsed from specified object. If object is null or not
   * a numeric value, zero is returned.
   * 
   * @param value
   *          The object to parse.
   * @return The Float value.
   */
  public static Float parseFloat(Object value) {
    try {
      return Float.parseFloat(value.toString());
    }
    catch (Exception e) {
      return new Float(0.0F);
    }
  }

  /**
   * Returns Double value parsed from specified string. If string is null or not
   * a numeric value, zero is returned.
   * 
   * @param value
   *          The string to parse.
   * @return The Double value.
   */
  public static Double parseDouble(String value) {
    try {
      return Double.parseDouble(value);
    }
    catch (Exception e) {
      return new Double(0.0);
    }
  }

  /**
   * Returns Double value parsed from specified object. If object is null or not
   * a numeric value, zero is returned.
   * 
   * @param value
   *          The object to parse.
   * @return The Double value.
   */
  public static Double parseDouble(Object value) {
    try {
      return Double.parseDouble(value.toString());
    }
    catch (Exception e) {
      return new Double(0.0);
    }
  }

  /**
   * Returns an int representing the unscaled value of the specified BigDecmial
   * value.
   * 
   * @param bigDecimalValue
   *          The BigDecimal value to convert.
   * @return The unscaled int value.
   */
  public static int toUnscaledInt(BigDecimal bigDecimalValue) {
    if (bigDecimalValue == null)
      return 0;

    return bigDecimalValue.unscaledValue().intValue();
  }

  /**
   * Returns an long representing the unscaled value of the specified BigDecmial
   * value.
   * 
   * @param bigDecimalValue
   *          The BigDecimal value to convert.
   * @return The unscaled long value.
   */
  public static long toUnscaledLong(BigDecimal bigDecimalValue) {
    if (bigDecimalValue == null)
      return 0L;

    return bigDecimalValue.unscaledValue().longValue();
  }

  /**
   * Rounds the specified number to the specified precision.
   * <p>
   * If the precision is a negative value, values are rounded with the following
   * rule: <code>round(value / 10 ^ |precision|) * 10 ^ |precision|</code>
   * 
   * @param value
   *          The value to be rounded.
   * @param precision
   *          The places of precision to round to.
   * @return The rounded value.
   */
  public static double round(double value, int precision) {
    if (precision < 0) {
      precision = Math.abs(precision);
      return Math.round((value / Math.pow(10, precision))) * (int) Math.pow(10, precision);
    }
    else if (precision > 0) {
      BigDecimal bd = new BigDecimal(value);
      bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
      return bd.doubleValue();
    }
    else {
      return Math.round(value);
    }
  }

  /**
   * Round the specified BigDecimal value to the nearest whole value. Fractional
   * portion >= 0.5 is rounded up. Examples: 1234.56 -> 1235.00; 1234.46 ->
   * 1234.00.
   * 
   * @param value
   *          BigDecimal value to round.
   * @return The rounded BigDecimal value.
   */
  public static BigDecimal roundUp(BigDecimal value) {
    return round(value, RoundingMode.HALF_UP);
  }

  /**
   * Round the specified BigDecimal value down towards zero. Fractional portion
   * is zeroed. Example: 1234.56 -> 1234.00; 1234.46 -> 1234.00.
   * 
   * @param value
   *          BigDecimal value to round.
   * @return The rounded BigDecimal value.
   */
  public static BigDecimal roundDown(BigDecimal value) {
    return round(value, RoundingMode.DOWN);
  }

  /**
   * Round the specified BigDecimal value using the specified rounding mode.
   * 
   * @param value
   *          BigDecimal value to round.
   * @param mode
   *          The rounding mode to use.
   * @return The rounded BigDecimal value.
   * @see java.math.RoundingMode
   */
  public static BigDecimal round(BigDecimal value, RoundingMode mode) {
    BigDecimal roundedValue;

    if (value.precision() > value.scale()) {
      roundedValue = value.round(new MathContext(value.precision() - value.scale(), mode));
    }
    else {
      BigDecimal value2 = value.add(BigDecimal.ONE);
      roundedValue = value2.round(new MathContext(1, mode));
      roundedValue = roundedValue.subtract(BigDecimal.ONE);
    }

    BigDecimal scaledValue = roundedValue.multiply(BigDecimal.TEN.pow(value.scale()));
    BigDecimal finalValue = BigDecimal.valueOf(scaledValue.longValue(), value.scale());
    return finalValue;
  }

  // TODO: Commented out functionality until determined that it's needed.
  /**
   * Round up the specified BigDecimal value by the specified factor of 10.
   * 
   * This uses RoundingMode.UP
   * 
   * Example: 1234.56 -> (factor=2) -> 1200.00; 1234.56 -> (factor=-1) ->
   * 1234.60.
   * 
   * @param value
   *          BigDecimal value to round.
   * @param factor
   *          Factor of 10 to round to.
   * @return The rounded BigDecimal value.
   */
  public static BigDecimal roundWholePartUp(BigDecimal value, int factor) {
    return roundWholePart(value, factor, RoundingMode.HALF_UP);
  }

  /**
   * Round the specified BigDecimal value by the specified factor of 10, using
   * the specified rounding mode.
   * 
   * Examples: 1234.56 -> (factor=2, mode=HALF_UP) -> 1200.00 1234.56 ->
   * (factor=-1, mode=HALF_UP) -> 1234.60.
   * 
   * @param value
   *          BigDecimal value to round.
   * @param factor
   *          Factor of 10 to round to.
   * @param mode
   *          The rounding mode to use.
   * @return The rounded BigDecimal value.
   * @see java.math.RoundingMode
   */
  private static BigDecimal roundWholePart(BigDecimal value, int factor, RoundingMode mode) {
    BigDecimal roundedValue;
    if ((value.precision() - (factor + value.scale()) < 0))
      return BigDecimal.valueOf(0, value.scale());
    else if ((value.precision() - (factor + value.scale()) == 0)) {
      BigDecimal value2 = value.add(BigDecimal.ONE.scaleByPowerOfTen(factor));
      roundedValue = value2.round(new MathContext(1, mode));
      roundedValue = roundedValue.subtract(BigDecimal.ONE.scaleByPowerOfTen(factor));
    }
    else {
      roundedValue = value.round(new MathContext(value.precision() - (factor + value.scale()), mode));
    }
    BigDecimal scaledValue = roundedValue.multiply(BigDecimal.TEN.pow(value.scale()));
    BigDecimal finalValue = BigDecimal.valueOf(scaledValue.longValue(), value.scale());
    return finalValue;
  }

  public static double longAmountToDouble(Long amount) {
    String value = Long.toString(amount / 100);
    if (amount < 0L)
      amount = amount * -1;
    Long cents = amount % 100;

    if (cents != 0L)
      value = value + "." + StringUtil.fixedPadLeft(StringUtil.toString(cents), 2, '0');
    else
      value = value + ".00";

    return new Double(value);
  }

  public static String longAmountToDoubleStringFormat(Long amount) {
    String value = Long.toString(amount / 100);
    if (amount < 0L)
      amount = amount * -1;
    Long cents = amount % 100;

    if (cents != 0L)
      value = value + "." + StringUtil.fixedPadLeft(StringUtil.toString(cents), 2, '0');
    else
      value = value + ".00";
    return value;
  }

  /**
   * To check if the Numeric Long object is zero.
   * 
   * @return True if the numeric Long object is zero, otherwise false.
   */
  public static boolean isZero(Long n) {
    if (n == null)
      return true;
    if (n == 0)
      return true;
    return false;
  }

  public static boolean isNotZero(Long n) {
    return !isZero(n);
  }

  /**
   * To check if the Numeric Integer object is zero.
   * 
   * @return True if the numeric Integer object is zero, otherwise false.
   */
  public static boolean isZero(Integer n) {
    if (n == null)
      return true;
    if (n == 0)
      return true;
    return false;
  }

  public static boolean isNotZero(Integer n) {
    return !isZero(n);
  }

  /**
   * To check if the Numeric Integer object is zero.
   * 
   * @return True if the numeric Integer object is zero, otherwise false.
   */
  public static boolean isZero(BigDecimal n) {
    if (n == null)
      return true;
    if (n.equals(BigDecimal.ZERO))
      return true;
    if (n.unscaledValue().longValue() == 0L)
      return true;
    return false;
  }

  public static boolean isNotZero(BigDecimal n) {
    return !isZero(n);
  }

  /**
   * Truncates the specified Integer value to the size of the field in the
   * specified entity type.
   * 
   * @param value
   *          The Integer value to truncate.
   * @param type
   *          The data entity class the value is being truncated for.
   * @param fieldName
   *          The entity field name the value is being truncated for.
   * @return The truncated value. If the original value size is less then or
   *         equal to the size of the field, it is returned as is.
   */
  @SuppressWarnings("rawtypes")
  public static Integer truncateToFieldSize(Integer value, Class type, String fieldName) {
    String truncVal = StringUtil.truncateToFieldSize(StringUtil.toString(value), type, fieldName);
    return parseInt(truncVal);
  }

  /**
   * Truncates the specified Long value to the size of the field in the
   * specified entity type.
   * 
   * @param value
   *          The Long value to truncate.
   * @param type
   *          The data entity class the value is being truncated for.
   * @param fieldName
   *          The entity field name the value is being truncated for.
   * @return The truncated value. If the original value size is less then or
   *         equal to the size of the field, it is returned as is.
   */
  @SuppressWarnings("rawtypes")
  public static Long truncateToFieldSize(Long value, Class type, String fieldName) {
    String truncVal = StringUtil.truncateToFieldSize(StringUtil.toString(value), type, fieldName);
    return parseLong(truncVal);
  }

  /**
   * Truncates the specified BigDecimal value to the size of the field in the
   * specified entity type. The value is converted to its unscaled value before
   * truncating.
   * 
   * @param value
   *          The BigDecimal value to truncate.
   * @param type
   *          The data entity class the value is being truncated for.
   * @param fieldName
   *          The entity field name the value is being truncated for.
   * @return The truncated value. If the original value size is less then or
   *         equal to the size of the field, it is returned as is.
   */
  @SuppressWarnings("rawtypes")
  public static BigDecimal truncateToFieldSize(BigDecimal value, Class type, String fieldName) {
    if (value == null)
      return BigDecimal.ZERO;
    int scale = value.scale();
    Long truncVal = truncateToFieldSize(toUnscaledLong(value), type, fieldName);
    return BigDecimal.valueOf(truncVal, scale);
  }

  public static int parseZip(String zip) {
    try {
      if (zip.length() < 5)
        zip = StringUtil.lpad(zip, 5, '0');
      if (zip.length() < 9)
        zip = StringUtil.rpad(zip, 9, '0');
      long z = Long.parseLong(zip);
      int zipCode = (int) z / 1000000;
      return zipCode;
    }
    catch (Exception e) {
      return Integer.valueOf(0);
    }
  }

  public static long parsePhoneNumber(String phoneNumberAlpha) {
    try {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < phoneNumberAlpha.length(); i++) {
        char a = phoneNumberAlpha.charAt(i);
        if (a >= '0' && a <= '9')
          sb.append(a);
      }
      String s = sb.toString();
      if (StringUtil.isEmpty(s))
        return Long.valueOf(0L);
      return parseLong(s);
    }
    catch (Exception e) {
      return Long.valueOf(0L);
    }

  }

  /**
   * Takes a String (i.e. from an input field) and removes all display-added
   * commas, returning a 'raw' number String. (ex. "1,207.03" becomes "1207.03")
   * 
   * @param input
   *          The String to remove commas from
   * @return result A String with the same numeric representation, minus commas.
   */
  public static String stripCommas(String input) {
    String[] inputArr = input.split(",");
    StringBuilder resultBuilder = new StringBuilder();
    for (String s : inputArr) {
      resultBuilder.append(s);
    }
    String result = resultBuilder.toString();
    return result;
  }

  /**
   * Takes a String (i.e. from an input field) and removes all display-added
   * colons, returning a 'raw' String representing hours and minutes in a
   * 4-character String. (ex. "12:00" becomes "1200")
   * 
   * @param input
   *          The String to remove colons from
   * @return
   */
  public static String timeToString(String input) {
    String[] inputArr = input.split(":");
    StringBuilder resultBuilder = new StringBuilder();
    for (String s : inputArr) {
      resultBuilder.append(s);
    }
    String result = resultBuilder.toString();
    return result;
  }

  /**
   * Returns remaining divided by gross as BigDecimal with a scale of two.
   * 
   * @param gross
   * @param remaining
   * @return
   */
  public static BigDecimal getAbsoluteVariance(Long gross, Long remaining) {
    if (0 != remaining) {
      return (new BigDecimal(remaining.longValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).divide(
          new BigDecimal(gross.longValue()), 6, BigDecimal.ROUND_HALF_UP).abs();
    }
    else {
      return BigDecimal.ZERO;
    }
  }

  public static int compareBigDecimal(BigDecimal bd1, BigDecimal bd2) {
    // null and null are equal
    if (bd1 == null && bd2 == null)
      return 0;

    // null is less than a non null value
    if (bd1 == null && bd2 != null)
      return -1;

    // null is less than a non null value
    if (bd1 != null && bd2 == null)
      return -1;

    return bd1.compareTo(bd2);
  }

  public static int compareLong(Long l1, Long l2) {
    // null and null are equal
    if (l1 == null && l2 == null)
      return 0;

    // null is less than a non null value
    if (l1 == null && l2 != null)
      return -1;

    // null is less than a non null value
    if (l1 != null && l2 == null)
      return -1;

    return l1.compareTo(l2);
  }

  public static int compareInteger(Integer i1, Integer i2) {
    // null and null are equal
    if (i1 == null && i2 == null)
      return 0;

    // null is less than a non null value
    if (i1 == null && i2 != null)
      return -1;

    // null is less than a non null value
    if (i1 != null && i2 == null)
      return -1;

    return i1.compareTo(i2);
  }

  /**
   * Returns BigDecimal value parsed from specified string. If string is null or
   * not a numeric value, zero is returned.
   * 
   * @param value
   *          The string to parse.
   * @return The Long value.
   */
  public static BigDecimal parseBigDecimal(String value) {
    try {
      return new BigDecimal(value);
    }
    catch (Exception e) {
      return BigDecimal.ZERO;
    }
  }

  public static Boolean hasValue(Integer num) {
    return num != null && num > 0;
  }
}
