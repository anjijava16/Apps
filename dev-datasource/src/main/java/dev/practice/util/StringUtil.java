package dev.practice.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import dev.practice.db.DatabaseUtil;

//import org.apache.commons.lang.WordUtils;

/**
 * String Utils.
 * 
 * @author: KondalReddy
 */
public final class StringUtil {

  public static final String LINE_SEPARATOR        = System.getProperty("line.separator");
  public static final String FILE_SEPARATOR        = System.getProperty("file.separator");
  public static final String EMAIL_ADDRESS_PATTERN = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$";

  /**
   * Returns whether the specified string is "empty". Empty is defined as null
   * condition, empty string, or a non-empty string consisting of only spaces.
   * 
   * @param string
   *          The string to evaluate.
   * @return Indication of whether the string is "empty".
   */
  public static boolean isEmpty(String string) {
    if (string == null)
      return true;
    if (string.trim().length() == 0)
      return true;

    return false;
  }

  public static boolean isNotEmpty(String string) {
    return !isEmpty(string);
  }

  /**
   * Returns whether the specified search string is contained within the source
   * string.
   * 
   * @param source
   *          The string to check to see if it contains the search string.
   * @param search
   *          The search string.
   * @return Indication of whether the search string is contained within the
   *         source string.
   */
  public static boolean contains(String source, String search) {
    if (source == null || search == null)
      return false;
    else
      return source.contains(search);
  }

  /**
   * Returns indication of whether specified string is a member of the specified
   * set.
   * 
   * @param string
   *          String to determine if member of set.
   * @param set
   *          Set of Strings to compare against.
   * @return Indication of whether specified string is a member of the specified
   *         set.
   */
  public static boolean inSet(String string, String[] set) {
    if (string == null || set == null || (set != null && set.length == 0)) {
      return false;
    }

    for (String item : set) {
      if (string.equals(item)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns the index (zero-based) of the search string in the target string.
   * If either string is null, or the search string is larger than the target,
   * -1 is returned.
   * 
   * @param targetStr
   *          The target string to locate the index of the search string in.
   * @param searchStr
   *          The search string to locate in the target string.
   * @return The first position of the search string in the target string.
   */
  public static int indexOf(String targetStr, String searchStr) {
    if (targetStr == null || searchStr == null || searchStr.length() > targetStr.length()) {
      return -1;
    }
    else {
      return targetStr.indexOf(searchStr);
    }
  }

  /**
   * Returns whether the specified source string starts with the search string.
   * 
   * @param source
   *          The string to check to see if it starts with the search string.
   * @param search
   *          The search string.
   * @return Indication of whether the source string starts with the search
   *         string.
   */
  public static boolean startsWith(String source, String search) {
    if (source == null || search == null)
      return false;

    return source.startsWith(search);
  }

  /**
   * Returns whether the specified source string ends with the search string.
   * 
   * @param source
   *          The string to check to see if it ends with the search string.
   * @param search
   *          The search string.
   * @return Indication of whether the source string ends with the search
   *         string.
   */
  public static boolean endsWith(String source, String search) {
    if (source == null || search == null)
      return false;

    return source.endsWith(search);
  }

  /**
   * Returns length of specified string. If string is null, zero is returned.
   * 
   * @param string
   *          The string to get the length of.
   * @return The length of the string.
   */
  public static int length(String string) {
    if (string == null)
      return 0;

    return string.length();
  }

  /**
   * Returns substring of specified length starting at specified index.
   * 
   * @param string
   *          String to get substring from.
   * @param start
   *          Starting index (zero-based).
   * @param length
   *          Length of substring to be returned.
   * @return Resulting substring.
   */
  public static String substring(String string, int start, int length) {
    if (string == null)
      return "";

    if (length <= 0 || (start < 0 || start > string.length() - 1))
      return "";

    return string.substring(start, Math.min(start + length, string.length()));
  }

  /**
   * 
   * This uses the true String.substring()
   * 
   * Returns a new string that is a substring of this string. 
   * The substring begins at the specified beginIndex and extends 
   * to the character at index endIndex - 1. Thus the length of the 
   * substring is endIndex-beginIndex. 
   * @param string
   *          String to get substring from.
   * @param start
   *          Starting index (zero-based).
   * @param length
   *          Length of substring to be returned.
   * @return Resulting substring.
   */
  public static String substringByPosition(String string, int start, int end) {
    if (string == null)
      return "";

    if (end <= 0 || (start < 0 || start > string.length() - 1 || start > end))
      return "";

    return string.substring(start, end);
  }

  /**
   * Left padding of a string with space (does not truncate).
   * 
   * @param input
   *          string to be pad.
   * @param length
   *          pad the string to this size.
   * @return the padded string.
   */
  public static String lpad(String input, int length) {
    return lpad(input, length, ' ');
  }

  /**
   * Left padding of a string (does not truncate).
   * 
   * @param input
   *          string to be pad.
   * @param length
   *          pad the string to this size.
   * @param pad
   *          character to use for padding
   * @return the padded string.
   */
  public static String lpad(String input, int length, char pad) {
    if (input == null)
      return "";

    if (input.length() >= length)
      return input;

    StringBuilder sb = new StringBuilder(input);

    while (sb.length() < length) {
      sb.insert(0, pad);
    }

    return sb.toString();
  }

  /**
   * Right padding of a string with space (does not truncate).
   * 
   * @param input
   *          string to be pad.
   * @param length
   *          pad the string to this size.
   * @return the padded string.
   */
  public static String rpad(String input, int length) {
    return rpad(input, length, ' ');
  }

  /**
   * Left padding of a string (does not truncate).
   * 
   * @param input
   *          string to be pad.
   * @param length
   *          pad the string to this size.
   * @param pad
   *          character to use for padding
   * @return the padded string.
   */
  public static String rpad(String input, int length, char pad) {
    if (input == null)
      return "";

    if (input.length() >= length)
      return input;

    StringBuilder sb = new StringBuilder(input);

    while (sb.length() < length) {
      sb.append(pad);
    }

    return sb.toString();
  }

  /**
   * Pads string left to fixed size, and truncates if too long.
   * 
   * @param input
   *          string to be padded or truncated
   * @param length
   *          lenth to pad to
   * @return the string of size length
   */
  public static String fixedPadLeft(String input, int length) {
    if (input != null && input.length() > length)
      input = input.substring(0, length);

    if (input == null)
      return lpad("", length);

    return lpad(input, length);
  }

  /**
   * Pads string left to fixed size, and truncates if too long.
   * 
   * @param input
   *          string to be padded or truncated
   * @param length
   *          lenth to pad to
   * @param pad
   *          char to pad with
   * @return the string of size length
   */
  public static String fixedPadLeft(String input, int length, char pad) {
    if (input != null && input.length() > length)
      input = input.substring(0, length);

    if (input == null)
      return lpad("", length, pad);

    return lpad(input, length, pad);
  }

  /**
   * Pads string right to fixed size, and truncates if too long.
   * 
   * @param input
   *          string to be padded or truncated
   * @param length
   *          lenth to pad to
   * @return the string of size length
   */
  public static String fixedPadRight(String input, int length) {
    if (input != null && input.length() > length)
      input = input.substring(0, length);

    if (input == null)
      return rpad("", length);

    return rpad(input, length);
  }

  /**
   * Pads string right to fixed size, and truncates if too long.
   * 
   * @param input
   *          string to be padded or truncated
   * @param length
   *          lenth to pad to
   * @param pad
   *          char to pad with
   * @return the string of size length
   */
  public static String fixedPadRight(String input, int length, char pad) {
    if (input != null && input.length() > length)
      input = input.substring(0, length);

    if (input == null)
      return rpad("", length, pad);

    return rpad(input, length, pad);
  }

  /**
   * Checks if a specified String value conforms to the specified pattern.
   * 
   * @param value
   *          The value to be checked.
   * @param pattern
   *          A pattern following java.util.regex.Pattern.
   * @return true if the value matches the pattern, false otherwise.
   * @see java.util.regex.Pattern
   */
  public static final boolean matchPattern(String value, String pattern) {
    if (pattern == null || value == null)
      return false;

    return Pattern.matches(pattern, value);
  }

  /**
   * Checks if a specified Character value conforms to the specified pattern.
   * 
   * @param value
   *          The value to be checked.
   * @param pattern
   *          A pattern following java.util.regex.Pattern.
   * @return true if the value matches the pattern, false otherwise.
   * @see java.util.regex.Pattern
   */
  public static final boolean matchPattern(Character value, String pattern) {
    if (pattern == null || value == null)
      return false;

    return Pattern.matches(pattern, value.toString());
  }

  /**
   * Returns the String representation of the specified value. If the value is
   * null, empty string is returned.
   * 
   * @param value
   *          An object.
   * @return The String representation of the value.
   */
  public static String toString(Object value) {
    if (value == null)
      return "";

    try {
      return value.toString();
    }
    catch (Exception e) {
      return "";
    }
  }

  /**
   * Returns the trimmed String of the specified value. If the value is null,
   * empty string is returned.
   * 
   * @param value
   *          A String.
   * @return The String representation of the value.
   */
  public static String trim(String value) {
    if (value == null) {
      return "";
    }
    else {
      return value.trim();
    }
  }

  /**
   * Truncates the specified String value to the size of the field in the
   * specified entity type.
   * 
   * @param value
   *          The String value to truncate.
   * @param type
   *          The data entity class.
   * @param fieldName
   *          The field name.
   * @return The truncated value. If the original value size is less then or
   *         equal to the size of the field, it is returned as is.
   */
  @SuppressWarnings("rawtypes")
  public static String truncateToFieldSize(String value, Class type, String fieldName) {
    return substring(value, 0, DatabaseUtil.getColumnLength(type, fieldName));
  }

  /**
   * Returns the String representation of the specified value. If the value is
   * null, empty string is returned.
   * 
   * @param value
   *          The Integer value.
   * @return The String representation of the value.
   */
  public static String toString(Integer value) {
    if (value == null)
      return "";

    return Integer.toString(value);
  }

  /**
   * Converts the specified Integer value to String and left pads with zeros to
   * the specified length.
   * 
   * @param value
   *          The Integer value to convert.
   * @param length
   *          The requested String length.
   * @return The converted String.
   */
  public static String toStringFixed(Integer value, int length) {
    return StringUtil.fixedPadLeft(StringUtil.toString(value), length, '0');
  }

  /**
   * Returns the String representation of the specified value. If the value is
   * null, empty string is returned.
   * 
   * @param value
   *          The Long value.
   * @return The String representation of the value.
   */
  public static String toString(Long value) {
    if (value == null)
      return "";

    return Long.toString(value);
  }

  /**
   * Converts the specified Long value to String and left pads with zeros to the
   * specified length.
   * 
   * @param value
   *          The Long value to convert.
   * @param length
   *          The requested String length.
   * @return The converted String.
   */
  public static String toStringFixed(Long value, int length) {
    return StringUtil.fixedPadLeft(StringUtil.toString(value), length, '0');
  }

  /**
   * Returns the String representation of the specified value. If the value is
   * null, empty string is returned.
   * 
   * @param value
   *          The Float value.
   * @return The String representation of the value.
   */
  public static String toString(Float value) {
    if (value == null)
      return "";

    return Float.toString(value);
  }

  /**
   * Returns the String representation of the specified value. If the value is
   * null, empty string is returned.
   * 
   * @param value
   *          The Double value.
   * @return The String representation of the value.
   */
  public static String toString(Double value) {
    if (value == null)
      return "";

    return Double.toString(value);
  }

  /**
   * Returns a String representing the unscaled value of the specified
   * BigDecmial value.
   * 
   * @param bigDecimalValue
   *          The BigDecimal value to convert.
   * @return The unscaled value as a String.
   */
  public static String toUnscaledString(BigDecimal bigDecimalValue) {
    if (bigDecimalValue == null)
      return "";

    return bigDecimalValue.unscaledValue().toString();
  }

  /**
   * Returns a String representing the unscaled value of the specified
   * BigDecmial value and left pads with zeros to the specified length.
   * 
   * @param bigDecimalValue
   *          The BigDecimal value to convert.
   * @param length
   *          The requested String length.
   * @return The unscaled value as a String.
   */
  public static String toUnscaledString(BigDecimal bigDecimalValue, int length) {
    return StringUtil.fixedPadLeft(toUnscaledString(bigDecimalValue), length, '0');
  }

  /**
   * Returns the specified string in upper case. If the value is null, empty
   * string is returned.
   * 
   * @param value
   *          The string value to convert.
   * @return The string value in upper case.
   */
  public static String toUpperCase(String value) {
    if (value == null)
      return "";

    return value.toUpperCase();
  }

  /**
   * Returns the specified string in lower case. If the value is null, empty
   * string is returned.
   * 
   * @param value
   *          The string value to convert.
   * @return The string value in lower case.
   */
  public static String toLowerCase(String value) {
    if (value == null)
      return "";

    return value.toLowerCase();
  }

  /**
   * Returns null if the string is empty. Useful when setting string values on
   * POJOs that are to be persisted.
   * 
   * @param value
   * @return The string value or null.
   */
  public static String emptyToNull(String value) {
    if (isEmpty(value))
      return null;

    return value;
  }

  /**
   * Returns a formatted TariffNo from a normal TariffNo.
   */
  public static String formatTariffNo(String tariffNo) {
    if (null != tariffNo && tariffNo.length() >= 7) {
      StringBuilder sb = new StringBuilder(tariffNo);
      sb.insert(4, '.');
      sb.insert(7, '.');
      return sb.toString();
    }
    else if (tariffNo != null && tariffNo.length() == 6) {
      StringBuilder sb = new StringBuilder(tariffNo);
      sb.insert(4, '.');
      sb.append(".");
      return sb.toString();
    }
    else if (tariffNo != null && tariffNo.length() == 5) {
      StringBuilder sb = new StringBuilder(tariffNo);
      sb.insert(4, '.');
      return sb.toString();
    }
    else if (tariffNo != null && tariffNo.length() == 4) {
      StringBuilder sb = new StringBuilder(tariffNo);
      sb.append(".");
      return sb.toString();
    }

    return "";
  }

  public static String formatEntryNo(Integer entryNo) {
    if (entryNo != null) {
      if (entryNo >= 10) {
        StringBuilder sb = new StringBuilder(entryNo.toString());
        int length = sb.length();
        sb.insert(length - 1, "-");
        return sb.toString();
      }
      else {
        return entryNo.toString();
      }
    }
    else
      return "";
  }

  public static String formatEntryNo(String entryNo) {
    if (entryNo != null && length(entryNo) >= 2) {
      StringBuilder sb = new StringBuilder(entryNo);
      int length = sb.length();
      sb.insert(length - 1, "-");
      return sb.toString();
    }
    else if (length(entryNo) == 1) {
      return entryNo;
    }

    return "";
  }

  /**
   * Converts the specified long value to string representation of a money
   * value. It is implied that the last two digits of the long value are implied
   * to be cents.
   * 
   * @param value
   * @return
   */
  public static String formatMoney(Long value) {
    return formatMoney(value.toString());
  }

  /**
   * Converts the specified integer value to string representation of a money
   * value. It is implied that the last two digits of the integer value are
   * implied to be cents.
   * 
   * @param value
   * @return
   */
  public static String formatMoney(Integer value) {
    return formatMoney(value.toString());
  }

  /**
   * Converts from BigDecimal to String.
   * 
   * @param fromObject
   * @return
   */
  public static String formatMoney(BigDecimal fromObject) {
    if (fromObject == null) {
      return "0.00";
    }
    //Not an error... calling formatMoney(String) on a BigDecimal will only apply double formatting!
    /*ERROR*/return fromObject.toString();
    //return formatMoney(fromObject.toString());
  }

  /**
   * Converts the specified string value to string representation of a money
   * value. It is implied that the last two digits of the string value are
   * implied to be cents.
   * 
   * @param value
   * @return
   */
  public static String formatMoney(String value) {
    if (StringUtil.isEmpty(value))
      return value;
    String s = value.toString().trim();
    boolean isNegative = (s.charAt(0) == '-') ? true : false;
    int dollarPos = (isNegative) ? 1 : 0;
    StringBuilder sb = new StringBuilder();

    if (s.equals("0") || s.equals("00") || isEmpty(s))
      s = "000";
    else if (s.length() == 1)
      s = "00" + s;
    else if (s.length() == 2)
      s = "0" + s;

    int centsPos = s.length() - 2;
    sb.append('.');
    sb.append(s.substring(centsPos));

    for (int pos = centsPos - 1, count = 1; pos >= dollarPos; pos--, count++) {
      char c = s.charAt(pos);
      sb.insert(0, c);

      if (count == 3) {
        sb.insert(0, ',');
        count = 0;
      }
    }

    if (sb.charAt(0) == ',')
      sb.deleteCharAt(0);

    if (isNegative)
      sb.insert(0, '-');

    return sb.toString();
  }

  /**
   * Converts the specified long value to string representation of a whole
   * numeric value.
   * 
   * @param value
   * @return
   */
  public static String formatNumber(Long value) {
    return formatNumber(value.toString());
  }

  /**
   * Converts the specified integer value to string representation of a whole
   * numeric value.
   * 
   * @param value
   * @return
   */
  public static String formatNumber(Integer value) {
    return formatNumber(value.toString());
  }

  /**
   * Converts the specified string value to string representation of a whole
   * numeric value.
   * 
   * @param value
   * @return
   */
  public static String formatNumber(String value) {
    String s = value.toString().trim();

    if (0 < s.length()) {
      boolean isNegative = (s.charAt(0) == '-') ? true : false;
      StringBuilder sb = new StringBuilder();

      for (int pos = s.length() - 1, count = 1; pos >= 0; pos--, count++) {
        char c = s.charAt(pos);
        sb.insert(0, c);

        if (count == 3) {
          sb.insert(0, ',');
          count = 0;
        }
      }

      if (sb.charAt(0) == ',')
        sb.deleteCharAt(0);

      if (isNegative)
        sb.insert(0, '-');

      return sb.toString();
    }
    else {
      return s;
    }
  }

  public static String formatPhoneNumber(String input) {
    StringBuilder result = new StringBuilder();
    //possible non-supported formats?
    if (input.length() < 7)
      result.append(input);
    else if (input.length() == 7) {
      result.append(input.substring(0, 3));
      result.append("-");
      result.append(input.substring(3));
    }
    else if (input.length() > 7 && input.length() < 10)
      result.append(input);
    //typical area code format
    else if (input.length() == 10) {
      result.append("(");
      result.append(input.substring(0, 3));
      result.append(") ");
      result.append(input.substring(3, 6));
      result.append("-");
      result.append(input.substring(6));
    }
    //area code + extension?
    else if (input.length() > 10) {
      result.append("(");
      result.append(input.substring(0, 3));
      result.append(") ");
      result.append(input.substring(3, 6));
      result.append("-");
      result.append(input.substring(6, 10));
      result.append(" x");
      result.append(input.substring(10));
    }
    return result.toString();
  }

  public static String formatPhoneNoRightToLeft(String input) {
    if (input == null)
      return null;
    input = input.replaceAll("[-()]", "");
    StringBuilder result = new StringBuilder();
    if (input.length() <= 4)
      result.append(input);
    else if (input.length() > 4 && input.length() <= 7) {
      result.append(input.substring(input.length() - 4));
      result.insert(0, "-");
      result.insert(0, input.substring(0, input.length() - 4));
    }
    else if (input.length() > 7 && input.length() <= 10) {
      result.append(input.substring(input.length() - 4));
      result.insert(0, "-");
      result.insert(0, input.substring(input.length() - 7, input.length() - 4));
      result.insert(0, "-");
      result.insert(0, input.substring(0, input.length() - 7));
    }
    else if (input.length() > 10 && input.length() <= 15) {
      result.append(input.substring(input.length() - 4));
      result.insert(0, "-");
      result.insert(0, input.substring(input.length() - 7, input.length() - 4));
      result.insert(0, "-");
      result.insert(0, input.substring(input.length() - 10, input.length() - 7));
      result.insert(0, "-");
      result.insert(0, input.substring(0, input.length() - 10));
    }
    return result.toString();
  }

  /**
   * Formats the specified time value in HH:MM format.
   * 
   * @param value
   *          Integer representation of time.
   * @return Formatted time value.
   */
  public static String formatTime(Integer value) {
    return formatTime(toString(value));
  }

  /**
   * Formats the specified time value in HH:MM format.
   * 
   * @param value
   *          Long representation of time.
   * @return Formatted time value.
   */
  public static String formatTime(Long value) {
    return formatTime(toString(value));
  }

  /**
   * Formats the specified time value in HH:MM format.
   * 
   * @param value
   *          String representation of time.
   * @return Formatted time value.
   */
  public static String formatTime(String value) {
    String paddedValue = fixedPadLeft(value, 4, '0');
    StringBuilder sb = new StringBuilder();
    sb.append(substring(paddedValue, 0, 2));
    sb.append(':');
    sb.append(substring(paddedValue, 2, 2));
    return sb.toString();
  }

  /**
   * Formats the specified time value in HH:MM:SS format.
   * 
   * @param value
   *          Integer representation of time.
   * @return Formatted time value.
   */
  public static String formatTime6(Integer value) {
    return formatTime6(toString(value));
  }

  /**
   * Formats the specified time value in HH:MM:SS format.
   * 
   * @param value
   *          Long representation of time.
   * @return Formatted time value.
   */
  public static String formatTime6(Long value) {
    return formatTime6(toString(value));
  }

  /**
   * Formats the specified time value in HH:MM:SS format.
   * 
   * @param value
   *          String representation of time.
   * @return Formatted time value.
   */
  public static String formatTime6(String value) {
    String paddedValue = fixedPadLeft(value, 6, '0');
    StringBuilder sb = new StringBuilder();
    sb.append(substring(paddedValue, 0, 2));
    sb.append(':');
    sb.append(substring(paddedValue, 2, 2));
    sb.append(':');
    sb.append(substring(paddedValue, 4, 2));
    return sb.toString();
  }

  /**
   * Returns date in MMDDYY or MMDDYYYY formats in date format with slashes.
   * (E.g. 07/04/76 or 07/04/1776)
   * 
   * @param value
   *          Date String to format.
   * @return Formatted date.
   */
  public static String formatDate(String value) {
    String result = "";

    if (value == null) {
      return "";
    }

    if (length(value) == 6) {
      result = substring(value, 0, 2) + "/" + substring(value, 2, 2) + "/" + substring(value, 4, 2);
    }
    else if (length(value) == 8) {
      result = substring(value, 0, 2) + "/" + substring(value, 2, 2) + "/" + substring(value, 4, 4);
    }

    return result;
  }

  public static String formatDate1(String value) {
    String result = "";

    if (value == null) {
      return "";
    }

    if (length(value) == 6) {
      result = substring(value, 0, 2) + "/" + substring(value, 2, 2) + "/" + substring(value, 4, 2);
    }
    else if (length(value) == 8) {
      result = substring(value, 0, 4) + "/" + substring(value, 2, 2) + "/" + substring(value, 4, 2);
    }
    return result;
  }

  public static String trimForwardSlash(String value) {
    StringBuilder result = new StringBuilder();

    if (value == null)
      return "";

    String[] arr = value.split("/");
    for (String s : arr) {
      result.append(s);
    }

    return result.toString();
  }

  public static String trimDash(String value) {
    StringBuilder result = new StringBuilder();

    if (value == null)
      return "";

    String[] arr = value.split("-");
    for (String s : arr) {
      result.append(s);
    }

    return result.toString();
  }

  public static String ltrim(String value, char ch) {
    int i = 0;

    while (i < value.length()) {
      if (value.charAt(i) != ch)
        break;
      i++;
    }

    if (i == value.length() || i == 0)
      return value;

    return value.substring(i);
  }

  /**
   * Similar functionality to ltrim(String value, char ch) but returns
   * an empty string if i = length of input value.
   * @return
   */
  public static String ltrimAlt(String value, char ch) {
    int i = 0;

    while (i < value.length()) {
      if (value.charAt(i) != ch)
        break;
      i++;
    }

    if (i == 0)
      return value;

    if (i == value.length())
      return "";

    return value.substring(i);
  }

  public static String convertSqlTableToJavaName(String tableName) {
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(tableName.toLowerCase(), "_");
    String token = null;

    while (st.hasMoreTokens()) {
      token = st.nextToken();
      sb.append(Character.toUpperCase(token.charAt(0)));
      sb.append(token.substring(1));
    }

    return sb.toString();
  }

  public static String convertSqlColumnToJavaName(String columnName) {
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(columnName.toLowerCase(), "_");
    String token = null;
    boolean firstTokens = true;
    while (st.hasMoreTokens()) {
      token = st.nextToken();
      if (!firstTokens) {
        sb.append(Character.toUpperCase(token.charAt(0)));
        sb.append(token.substring(1));
      }
      else
        sb.append(token);

      firstTokens = false;
    }
    return sb.toString();
  }

  /**
   * Returns a blank-filled String of the specified length.
   * 
   * @param length
   *          Length to generate.
   * @return The generated string.
   */
  public static String generateBlank(int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++) {
      result.append(' ');
    }

    return result.toString();
  }

  /**
   * Returns whether the two specified strings are equal in value.
   * 
   * @param str1
   *          First string to check.
   * @param str2
   *          Second string to check.
   * @return Indication of whether or not the strings are equal.
   */
  public static boolean areEqual(String str1, String str2) {
    if (isEmpty(str1) && isEmpty(str2))
      return true;

    if (str1 == null)
      return false;

    if (str2 == null)
      return false;

    if (str1.equals(str2))
      return true;

    return false;
  }

  public static boolean areEqualsIgnoreCase(String str1, String str2) {
    if (isEmpty(str1) && isEmpty(str2))
      return true;

    if (str1 == null)
      return false;

    if (str2 == null)
      return false;

    if (str1.equalsIgnoreCase(str2))
      return true;

    return false;
  }

  /**
   * Returns whether the two specified strings are equal in value.
   * null, empty, non-empty string consisting of only spaces and "N" are equal
   * 
   * @param str1
   *          First string to check.
   * @param str2
   *          Second string to check.
   * @return Indication of whether or not the strings are equal.
   */
  public static boolean areBlankNEqual(String str1, String str2) {
    if ((isEmpty(str1) && isEmpty(str2)) || (isEmpty(str1) && "N".equalsIgnoreCase(str2))
        || (isEmpty(str2) && "N".equalsIgnoreCase(str1)))
      return true;

    if (str1 == null)
      return false;

    if (str2 == null)
      return false;

    if (str1.equals(str2))
      return true;

    return false;
  }

  /**
   * Returns whether the two specified strings are not equal in value.
   * 
   * @param str1
   *          First string to check.
   * @param str2
   *          Second string to check.
   * @return Indication of whether or not the strings are not equal.
   */
  public static boolean areNotEqual(String str1, String str2) {
    return !areEqual(str1, str2);
  }

  /**
   * Returns whether specified string contains only numeric characters.
   * 
   * @param value
   *          String to check.
   * @return Indication of whether or not string contains only numeric
   *         characters.
   */
  public static boolean isNumeric(String value) {
    if (value == null)
      return false;

    return Pattern.matches("[0-9]+", value);
  }

  public static boolean isBigDecimal(String value) {
    if (value == null)
      return false;

    return Pattern.matches("[0-9]+[.]{0,1}[0-9]*", value);
  }

  /**
   * Returns whether specified string contains only alphabetic characters.
   * 
   * @param value
   *          String to check.
   * @return Indication of whether or not string contains only alphabetic
   *         characters.
   */
  public static boolean isAlpha(String value) {
    if (value == null)
      return false;

    return Pattern.matches("[a-zA-Z]+", value);
  }

  /**
   * Returns whether specified string contains only alpha-numeric characters.
   * 
   * @param value
   *          String to check.
   * @return Indication of whether or not string contains only alpha-numeric
   *          characters.
   */
  public static boolean isAlphaNumeric(String value) {
    if (value == null)
      return false;

    return Pattern.matches("[a-zA-Z0-9]+", value);
  }

  /**
   * This method will try to convert the string to int. If not successful it
   * will return -1.
   * 
   * @param value
   *          the String to convert.
   * @return Integer Value of the string or -1 if the string is not valid
   *         number.
   */
  public static int toInt(String value) {
    try {
      return Integer.parseInt(value);
    }
    catch (Exception e) {
    }
    return -1;
  }

  public static String rightTrim(String value) {
    if (value != null) {
      StringBuilder sb = new StringBuilder();
      boolean skip = true;
      for (int i = value.length() - 1; i >= 0; i--) {
        if (value.charAt(i) == ' ')
          if (skip == true)
            continue;
        sb.append(value.charAt(i));
        skip = false;
      }
      sb.reverse();
      value = sb.toString();
    }
    else {
      value = "";
    }
    return value;
  }

  public static String calculateMid(String name, String address1, String address2, String city, String country) {
    String firmNamePart = getFirmNamePart(name, country);
    String streetPart = getStreetPart(address1, address2);
    String cityPart = getCityPart(city);

    StringBuilder builtMid = new StringBuilder();
    builtMid.append(country);
    builtMid.append(firmNamePart);
    builtMid.append(streetPart);
    builtMid.append(cityPart);

    return builtMid.toString();
  }

  private static String getFirmNamePart(String name, String country) {
    String value = name;

    if (StringUtil.isEmpty(value)) {
      return "";
    }

    value = stripIllegalNameChars(value);
    value = groupInitials(value);
    value = stripSingleInitials(value);
    value = removeArticleWords(value);
    value = removeSpecialWords(value, country);

    String[] tokens = value.split(" ");
    int limit = tokens.length >= 2 ? 2 : tokens.length;
    StringBuilder result = new StringBuilder();

    // Loop to return the first 3 characters of the first 2 words of the firm name.
    for (int i = 0; i < limit; i++) {
      result.append(StringUtil.substring(tokens[i], 0, 3));
    }

    return result.toString();
  }

  private static String stripIllegalNameChars(String value) {
    if (StringUtil.isEmpty(value)) {
      return "";
    }

    // Split value into tokens. Any character that is not a letter,
    // digit, hyphen (-) or apostrophe (') is a delimiter.
    String[] tokens = value.split("[^a-zA-Z0-9-']");

    List<String> list = new ArrayList<String>();
    String current, prior, next;

    for (int i = 0; i < tokens.length; i++) {
      if (StringUtil.isEmpty(tokens[i])) {
        continue; // Skip blank tokens.
      }

      // Get the current, prior and next words.
      current = tokens[i];
      prior = getPriorToken(tokens, i);
      next = getNextToken(tokens, i);

      // If the current word doesn't contain a hyphen (-) and the next word (if there is one)
      // also does not contain a hyphen, add the current word to the list.
      if (!StringUtil.contains(current, "-")
          && (StringUtil.isEmpty(next) || (!StringUtil.isEmpty(next) && !StringUtil.startsWith(next, "-")))) {
        current = current.replace("'", ""); // Remove any apostrophes (') in string.
        list.add(current);
      }
      else {
        StringBuilder word = new StringBuilder();

        // If the current word contains a hyphen, the preceding and following
        // words need to be concatenated without the hyphen.
        if (StringUtil.contains(current, "-")) {
          // Strip any hyphens inside the current and next words.
          current = stripInternalChars(current, '-');
          next = next != null ? stripInternalChars(next, '-') : null;

          // Current word is just a hyphen; concatenate the prior and next words.
          if ("-".equals(current)) {
            word.append(prior != null ? prior : "");
            word.append(next != null ? next : "");
            i++;
          }
          // Current word starts with a hyphen; concatenate the prior and the current
          // (without the hyphen).
          else if (StringUtil.startsWith(current, "-")) {
            word.append(prior != null ? prior : "");
            word.append(StringUtil.substring(current, 1, current.length() - 1));
          }
          // Current word ends with a hyphen; concatenate the current and the next
          // (without the hyphen).
          else if (StringUtil.endsWith(current, "-")) {
            word.append(StringUtil.substring(current, 0, current.length() - 1));
            word.append(next != null ? next : "");
            i++;
          }
          // Just add the word as is.
          else {
            word.append(current);
          }
        }
        else {
          continue;
        }

        // Add the constructed word to the list. Remove any apostrophes.
        list.add(word.toString().replace("'", ""));
      }
    }

    return convertListToString(list);
  }

  private static String removeArticleWords(String value) {
    if (StringUtil.isEmpty(value)) {
      return "";
    }

    String[] tokens = value.split(" ");
    List<String> list = new ArrayList<String>();

    for (int i = 0; i < tokens.length; i++) {
      String token = StringUtil.toUpperCase(tokens[i]);

      if (!("A".equals(token) || "AN".equals(token) || "AND".equals(token) || "OF".equals(token) || "THE".equals(token))) {
        list.add(token);
      }
    }

    return convertListToString(list);
  }

  private static String removeSpecialWords(String value, String country) {

    if (StringUtil.isEmpty(value)) {
      return "";
    }

    Map<String, List<String>> specialPrefixWords = new HashMap<String, List<String>>();
    List<String> wordsAdd;

    wordsAdd = new ArrayList<String>();
    wordsAdd.add("PT");
    // Add words for Indonesia (ID).
    specialPrefixWords.put("ID", wordsAdd);

    wordsAdd = new ArrayList<String>();
    wordsAdd.add("JSC");
    wordsAdd.add("OAO");
    wordsAdd.add("OOO");
    wordsAdd.add("ZAO");
    // Add words for Russia (RU).
    specialPrefixWords.put("RU", wordsAdd);

    wordsAdd = new ArrayList<String>();
    wordsAdd.add("Fabrica de Artigos de Vestuario");
    // Add words for Macau (MO).
    specialPrefixWords.put("MO", wordsAdd);

    String result = value;

    if (!StringUtil.isEmpty(country)) {
      // Attempt to get list of special words or phrases to be removed.
      List<String> words = specialPrefixWords.get(country);

      if (words != null) {
        // Loop through list to see if any of the special words are
        // found at the start of the firm name.
        for (String item : words) {
          String itemUpper = StringUtil.toUpperCase(item);

          // If the word/phrase is found, remove it.
          if (StringUtil.startsWith(value, itemUpper)) {
            result = value.replace(itemUpper, "");
            break;
          }
        }
      }
    }

    return result.trim();
  }

  private static String groupInitials(String value) {
    if (StringUtil.isEmpty(value)) {
      return "";
    }

    String[] tokens = value.split(" ");
    List<String> list = new ArrayList<String>();
    String current, next;
    boolean groupStarted = false;
    StringBuilder word = null;

    for (int i = 0; i < tokens.length; i++) {
      // Get the current and next words.
      current = tokens[i];
      next = getNextToken(tokens, i);

      // If current word an initial, start a word to group initials together.
      if (isInitial(current)) {
        if (!groupStarted) {
          word = new StringBuilder();
          groupStarted = true;
        }

        word.append(current);

        // If the next word is not an initial, add the group to the list and reset.
        if (!isInitial(next)) {
          list.add(word.toString());
          groupStarted = false;
        }
      }
      // Otherwise, just add the current word to the list.
      else {
        list.add(current);
      }
    }

    return convertListToString(list);
  }

  private static String stripSingleInitials(String value) {
    if (StringUtil.isEmpty(value)) {
      return "";
    }

    String[] tokens = value.split(" ");
    List<String> list = new ArrayList<String>();

    for (int i = 0; i < tokens.length; i++) {
      // Add token to list if not an initial.
      if (!isInitial(tokens[i])) {
        list.add(tokens[i]);
      }
    }

    return convertListToString(list);
  }

  private static boolean isInitial(String value) {
    return StringUtil.isAlpha(value) && StringUtil.length(value) == 1;
  }

  private static String getStreetPart(String address1, String address2) {
    String value = "";
    if (isEmpty(address2))
      value = address1;
    else
      value = address1 + " " + address2;

    if (StringUtil.isEmpty(value)) {
      return "";
    }

    value = stripIllegalStreetChars(value);

    String[] tokens = value.split(" ");
    int compare = 0;

    // Loop to find the largest numeric value in the street address.
    for (int i = 0; i < tokens.length; i++) {
      if (!StringUtil.isEmpty(tokens[i])) {
        // int intValue = NumberUtil.parseInt(tokens[i]);
        // compare = intValue > compare ? intValue : compare;
      }
    }

    //bugfix # 6903
    //if nothing was found, return an empty string
    if (compare == 0)
      return "";

    // Return the first 4 characters of whatever was found.
    return StringUtil.substring(StringUtil.toString(compare), 0, 4);
  }

  private static String stripIllegalStreetChars(String value) {
    if (StringUtil.isEmpty(value)) {
      return "";
    }

    // Split value into tokens. Any character that is not a digit,
    // hyphen (-) or comma (,) is a delimiter.
    String[] tokens = value.split("[^0-9-,]");
    List<String> list = new ArrayList<String>();

    for (int i = 0; i < tokens.length; i++) {
      // Add tokens to list. Strip out any hyphens or commas.
      list.add(tokens[i].replace("-", "").replace(",", ""));
    }

    return convertListToString(list);
  }

  private static String getCityPart(String city) {
    String value = city;

    if (StringUtil.isEmpty(value)) {
      return "";
    }

    value = stripIllegalCityChars(value);
    value = removeArticleWords(value);

    String[] tokens = value.split(" ");
    StringBuilder result = new StringBuilder();
    boolean done = false;
    int i = 0;

    // Loop to construct a 3 character value from the city name.
    // If the first word has less than 3 characters, letters from the next word are appended.
    while (!done && i < tokens.length) {
      result.append(StringUtil.substring(tokens[i], 0, 3));

      if (StringUtil.length(result.toString()) >= 3) {
        done = true;
      }

      i++;
    }

    // Return the first 3 characters of the end result.
    return StringUtil.substring(result.toString(), 0, 3);
  }

  private static String stripIllegalCityChars(String value) {
    StringBuilder result = new StringBuilder();

    if (!StringUtil.isEmpty(value)) {
      // Split value into tokens. Any character that is not a letter is a delimiter.
      String[] tokens = value.split("[^a-zA-Z]");

      for (int i = 0; i < tokens.length; i++) {
        result.append(tokens[i]);

        if (i < tokens.length - 1) {
          result.append(" ");
        }
      }
    }

    return result.toString();
  }

  private static String getPriorToken(String[] array, int index) {
    String result = null;

    // Loop backward from the index position to locate the prior non-empty token.
    for (int i = index - 1; i >= 0; i--) {
      if (array[i].length() > 0) {
        result = array[i];
        break;
      }
    }

    return result;
  }

  private static String getNextToken(String[] array, int index) {
    String result = null;

    // Loop forward from the index position to locate the next non-empty token.
    for (int i = index + 1; i < array.length; i++) {
      if (array[i].length() > 0) {
        result = array[i];
        break;
      }
    }

    return result;
  }

  private static String stripInternalChars(String value, char item) {
    StringBuilder result = new StringBuilder();

    // Loop to remove any internal instances of the specified character from the string.
    // Instances of the character at the front or end of the string are preserved.
    for (int i = 0; i < value.length(); i++) {
      if (value.charAt(i) != item) {
        result.append(value.charAt(i));
      }
      else if (value.charAt(i) == item && (i == 0 || i == value.length() - 1)) {
        result.append(value.charAt(i));
      }
    }

    return result.toString();
  }

  private static String convertListToString(List<String> list) {
    StringBuilder result = new StringBuilder();

    // Loop to create a space delimited string of the list items.
    for (String item : list) {
      result.append(item + " ");
    }

    return result.toString().trim();
  }

  /**
   * @param country
   * @return
   */
  public static Boolean isCanadianProvince(String country) {
    if ("XA".equals(country) || "XB".equals(country) || "XC".equals(country) || "XM".equals(country)
        || "XN".equals(country) || "XO".equals(country) || "XP".equals(country) || "XQ".equals(country)
        || "XS".equals(country) || "XT".equals(country) || "XV".equals(country) || "XW".equals(country)
        || "XY".equals(country))
      return true;
    return false;
  }

  /**
   * A method to determine the number of special characters in a number formatting string pattern.
   * This value can then be appended to the field's text limit.
   * @param input
   * @return
   */
  public static Integer numberOfSpecials(String input) {
    Integer result = 0;
    for (char c : input.toCharArray()) {
      if (c == ',' || c == '.' || c == '-')
        result++;
    }
    return result;
  }

  public static int compareString(String s1, String s2) {
    // null and null are equal
    if (s1 == null && s2 == null)
      return 0;

    // null is less than a non null value
    if (s1 == null && s2 != null)
      return -1;

    // null is less than a non null value
    if (s1 != null && s2 == null)
      return -1;

    return s1.compareTo(s2);

  }

  /**
   * Split the string by a supplied length.
   * 
   * @param text
   * @param length
   * @return
   */
  public static String[] split(String text, int length) {
    ArrayList<String> list = new ArrayList<String>();
    for (int i = 1; i <= text.length(); i += length) {
      if ((i + length) <= text.length()) {
        list.add(text.substring(i - 1, (i + length) - 1));
      }
      else {
        list.add(text.substring(i - 1));
      }
    }
    return list.toArray(new String[list.size()]);
  }

  public static String formatC4(String fromObject) {
    String value = null;
    if (fromObject != null) {
      value = fromObject.toString();
      if (value.length() != 14) {
        return "";
      }
      else {
        value = value.substring(0, 4) + "-" + value.substring(4, 8) + "-" + value.substring(8, 11) + "-"
            + value.substring(11);
      }

    }
    return value;
  }

  /**
   * Replaces each substring of this string that matches the given regular expression with the given replacement.
   * @param str
   * @param original the regular expression to which this string is to be matched
   * @param replacement
   * @return String
   */
  public static String replace(String str, String original, String replacement) {
    if (str == null || original == null || replacement == null)
      return str;
    return str.replaceAll(original, replacement);
  }

  public static String formatDecimal(String format, BigDecimal value) {
    if (value == null)
      return "";

    DecimalFormat formatter = null;
    String valueStr = null;
    try {
      formatter = new DecimalFormat(format);
      valueStr = formatter.format(value);
    }
    catch (Exception e) {
      valueStr = value.toString();
    }
    return valueStr == null ? "" : valueStr;
  }

  public static String convertBytesToString(byte[] byteArray) {
    String text;
    if (byteArray != null) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < byteArray.length; i++) {
        byte b = byteArray[i];
        if (b == 0)//null character
          b = 32;//space character
        sb.append((char) b);
      }
      text = sb.toString();
    }
    else
      text = "";
    return text;
  }

  public static String deAccent(String input) {
    if (input == null)
      return "";
    StringBuilder output = new StringBuilder();
    for (int i = 0, n = input.length(); i < n; i++)
      output.append(deAccent(input.charAt(i)));
    return output.toString();
  }

  private static char deAccent(char c) {
    if (c >= 32 && c <= 127)
      return c;

    switch (c) {
      case 'à':
      case 'á':
      case 'â':
      case 'ã':
      case 'ä':
      case 'å':
      case 'æ':
        c = 'a';
        break;
      case 'À':
      case 'Á':
      case 'Â':
      case 'Ä':
      case 'Å':
      case 'Æ':
        c = 'A';
        break;
      case 'è':
      case 'é':
      case 'ê':
      case 'ë':
        c = 'e';
        break;
      case 'È':
      case 'É':
      case 'Ê':
      case 'Ë':
        c = 'E';
        break;
      case 'ì':
      case 'í':
      case 'î':
      case 'ï':
        c = 'i';
        break;
      case 'Ì':
      case 'Í':
      case 'Î':
      case 'Ï':
        c = 'I';
        break;
      case 'ò':
      case 'ó':
      case 'ô':
      case 'õ':
      case 'ö':
      case 'œ':
        c = 'o';
        break;
      case 'Ò':
      case 'Ó':
      case 'Ô':
      case 'Õ':
      case 'Ö':
      case 'Œ':
        c = 'O';
        break;
      case 'ù':
      case 'ú':
      case 'û':
      case 'ü':
        c = 'u';
        break;
      case 'Ù':
      case 'Ú':
      case 'Û':
      case 'Ü':
        c = 'U';
        break;
      case 'ý':
      case 'ÿ':
        c = 'y';
        break;
      case 'Ý':
      case 'Ÿ':
        c = 'Y';
        break;
      case 'ñ':
        c = 'n';
        break;
      case 'Ñ':
        c = 'N';
        break;
      case 'ß':
        c = 's';
        break;
      case 'ð':
        c = 'd';
        break;
      case 'Ð':
        c = 'D';
        break;
      case 'ø':
        c = 'o';
        break;
      case 'Ø':
        c = 'o';
        break;
      case 'ç':
        c = 'c';
        break;
      case 'Ç':
        c = 'C';
        break;
      case '¿':
      case '¡':
        c = ' ';
        break;
      default:
        c = ' ';
    }
    return c;
  }

  public static int indexOfFirstNonWhitespace(String str) { // KC-6140
    int pos = -1;
    if (str != null) {
      int i = 0;
      for (; i < str.length(); i++) {
        pos = i;
        if (!Character.isWhitespace(str.charAt(i)))
          break;
      }
      if (i == str.length())
        pos = -1;
    }
    return pos;
  }

  // </blah>
  // 56789012
  //
  public static List<String> segment(String str, String start, String stop) {
    List<String> list = new ArrayList<String>();
    //int startlen = start.length();
    int stoplen = stop.length();
    while (true) {
      int startpos = str.indexOf(start);
      if (startpos == -1)
        break;
      int stoppos = str.indexOf(stop);
      if (stoppos == -1)
        break;
      String s = str.substring(startpos, stoppos + stoplen);
      list.add(s);
      int newpos = stoppos + stoplen;
      if (newpos == str.length())
        break;
      str = str.substring(newpos);
    }
    return list;
  }

  public static boolean wildCardMatch(String text, String pattern, char wildCardChar, boolean ignoreCase) {

    if (ignoreCase) {
      text = text.toUpperCase();
      pattern = pattern.toUpperCase();
    }

    String[] cards = pattern.split("\\" + wildCardChar);

    for (String card : cards) {
      int idx = text.indexOf(card);

      if (idx == -1)
        return false;

      text = text.substring(idx + card.length());
    }
    return true;
  }

  public static String removeNonNumericChars(String text) {
    String str = text;
    if (text != null)
      str = text.replaceAll("[^\\d]", "");
    return str;
  }

  public static String truncate(String str, int maxSize) {
    str = str.substring(0, Math.min(str.length(), maxSize));
    return str;
  }

  public static String[] splitOnSpace(String str) {
    if (str == null)
      return new String[]{};
    return str.split("[ \t]+");
  }

  public static boolean booleanFromString(String str) {
    boolean response = false;
    if (!StringUtil.isEmpty(str) && str.equals("Y")) {
      response = true;
    }
    return response;
  }

  /**
   * This will return single space for null or empty string
   * 
   * @param str
   * @return
   */
  public static String emptyReturnSpace(String str) {
    if (str == null || str.isEmpty()) {
      return " ";
    }
    else {
      return str;
    }
  }

}
