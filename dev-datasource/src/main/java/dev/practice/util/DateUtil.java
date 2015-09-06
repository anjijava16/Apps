package dev.practice.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Date Utils.
 * 
 * @author: HillsReddy
 */
public final class DateUtil {

  private static final Log               log                            = LogFactory.getLog(DateUtil.class);

  //private static Calendar               calendar                  = Calendar.getInstance();

  // Epoch date used by Powerhouse - December 31, 1899.
  private static final Calendar          POWERHOUSE_EPOCH_DATE          = new GregorianCalendar(1899, 11, 31);

  private static final long              MILLISECONDS_PER_DAY           = 1000 * 3600 * 24;

  private static final DateTimeFormatter YYYYMMDD_FORMATTER             = DateTimeFormat.forPattern("yyyyMMdd");
  private static final DateTimeFormatter YYYYMMDDHHMI_FORMATTER         = DateTimeFormat.forPattern("yyyyMMddHHmm");
  private static final DateTimeFormatter YYYYMMDDHHMISS_FORMATTER       = DateTimeFormat.forPattern("yyyyMMddHHmmss");
  private static final DateTimeFormatter YYYYMMDDHHMMSSSSS_FORMATTER    = DateTimeFormat
                                                                            .forPattern("yyyyMMddHHmmssSSS");
  private static final DateTimeFormatter YYYY_MM_DD_FORMATTER           = DateTimeFormat.forPattern("yyyy-MM-dd");
  private static final DateTimeFormatter MMDD_FORMATTER                 = DateTimeFormat.forPattern("MMdd");
  private static final DateTimeFormatter MMDD_FORMATTER2                = DateTimeFormat.forPattern("MM/dd");
  private static final DateTimeFormatter HH24MI_FORMATTER               = DateTimeFormat.forPattern("HHmm");
  private static final DateTimeFormatter HH12MMSS_FORMATTER             = DateTimeFormat.forPattern("hhmmss");
  private static final DateTimeFormatter HH24MMSS_FORMATTER             = DateTimeFormat.forPattern("HHmmss");
  private static final DateTimeFormatter HH24MMssSS_FORMATTER           = DateTimeFormat.forPattern("HHmmssSS");
  private static final DateTimeFormatter MMDDYY2_FORMATTER              = DateTimeFormat.forPattern("MM/dd/yy");
  private static final DateTimeFormatter MMDDYYYY_FORMATTER             = DateTimeFormat.forPattern("MM/dd/yyyy");
  private static final DateTimeFormatter MMDDYYYY_HH24MM_FORMATTER      = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm");
  private static final DateTimeFormatter DAY_OF_WEEK                    = DateTimeFormat.forPattern("E");
  private static final DateTimeFormatter MONTH_NAME                     = DateTimeFormat.forPattern("MMM");
  private static final DateTimeFormatter FULL_MONTH_NAME                = DateTimeFormat.forPattern("MMMM");
  private static final DateTimeFormatter MMDDYYYY_HH24MMSS_FORMATTER    = DateTimeFormat
                                                                            .forPattern("MM/dd/yyyy HH:mm:ss");
  private static final DateTimeFormatter MMDDYY_FORMATTER               = DateTimeFormat.forPattern("MMddyy");
  private static final DateTimeFormatter MMDDYYYY2_FORMATTER            = DateTimeFormat.forPattern("MMddyyyy");
  private static final DateTimeFormatter MMDDYYHHmm_FORMATTER           = DateTimeFormat.forPattern("MMddyyHHmm");
  private static final DateTimeFormatter MMDDYYHHmmss_FORMATTER         = DateTimeFormat.forPattern("MMddyyHHmmss");
  private static final DateTimeFormatter MMDDYYYYHHmm_FORMATTER         = DateTimeFormat.forPattern("MMddyyyyHHmm");
  private static final DateTimeFormatter MMddyyhhmmssa_FORMATTER        = DateTimeFormat.forPattern("MMddyyhhmmssa");
  private static final DateTimeFormatter MMDDYYYY_HH24MMSSSSS_FORMATTER = DateTimeFormat
                                                                            .forPattern("MM/dd/yyyy HH:mm:ss.SSS");

  /**
   * Returns the current date and time.
   * 
   * @return The current date and time.
   */
  public static Date now() {
    //check if running on 3tier
    /*if (SystemUtil.is3Tier()) {
      //registry logonId is null, it means the code is server side 
      if (StringUtil.isEmpty(RegistryManager.getInstance().getLogonId()))
        return new Date();
      //client is requesting for date
      return SystemUtil.getRemoteDate();
    }
    else {*/
    //it is 2tier, client is hosting the server.
    return new Date();
    //}
  }

  /**
   * Returns the day of the month from the specified date. If the date is null,
   * zero is returned.
   * 
   * @param date
   *          The date to use.
   * @return The day of the month.
   */
  public static int day(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * Returns the month from the specified date. If the date is null, zero is
   * returned.
   * 
   * @param date
   *          The date to use.
   * @return The month.
   */
  public static int month(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) + 1; // Add 1 because month is zero-based.
  }

  /**
   * Returns the year from the specified date. If the date is null, zero is
   * returned.
   * 
   * @param date
   *          The date to use.
   * @return The year.
   */
  public static int year(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
  }

  /**
   * Returns the hour of the day (24-hour) from the specified date. If the date
   * is null, zero is returned.
   * 
   * @param date
   *          The date to use.
   * @return The hour of the day.
   */
  public static int hour(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  /**
   * Returns the minute of the hour from the specified date. If the date is
   * null, zero is returned.
   * 
   * @param date
   *          The date to use.
   * @return The minute of the hour.
   */
  public static int minute(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.MINUTE);
  }

  /**
   * Returns the second of the minute from the specified date. If the date is
   * null, zero is returned.
   * 
   * @param date
   *          The date to use.
   * @return The second of the minute.
   */
  public static int second(Date date) {
    Calendar calendar = Calendar.getInstance();
    if (date == null)
      return 0;
    calendar.setTime(date);
    return calendar.get(Calendar.SECOND);
  }

  /**
   * Returns whether date1 is before date2. The day, month and year are the only
   * values used in the comparison.
   * 
   * @param date1
   *          The first date to check.
   * @param date2
   *          The second date to check.
   * @return True if date1 before date2, false otherwise.
   */
  public static boolean dateBefore(Date date1, Date date2) {
    boolean result = false;
    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);
    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);

    if (d1Year < d2Year || (d1Year == d2Year && d1Month < d2Month)) {
      result = true;
    }
    else if (d1Year > d2Year || (d1Year == d2Year && d1Month > d2Month)) {
      result = false;
    }
    else {
      result = d1Day < d2Day;
    }

    return result;
  }

  /**
   * Returns whether date1 is after date2. The day, month and year are the only
   * values used in the comparison.
   * 
   * @param date1
   *          The first date to check.
   * @param date2
   *          The second date to check.
   * @return True if date1 after date2, false otherwise.
   */
  public static boolean dateAfter(Date date1, Date date2) {
    boolean result = false;
    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);
    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);

    if (d1Year > d2Year || (d1Year == d2Year && d1Month > d2Month)) {
      result = true;
    }
    else if (d1Year < d2Year || (d1Year == d2Year && d1Month < d2Month)) {
      result = false;
    }
    else {
      result = d1Day > d2Day;
    }

    return result;
  }

  /**
   * Returns whether date1 equals date2. The day, month and year are the only
   * values used in the comparison.
   * 
   * @param date1
   *          The first date to check.
   * @param date2
   *          The second date to check.
   * @return True if date1 equals date2.
   */
  public static boolean datesEqual(Date date1, Date date2) {
    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);

    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);

    boolean result = d1Year == d2Year && d1Month == d2Month && d1Day == d2Day;

    return result;
  }

  /**
   * Returns whether date1 equals date2. The date (day, month and year) and time
   * (hour, minute and second) are the values used in the comparison.
   * 
   * @param date1
   *          The first date to check.
   * @param date2
   *          The second date to check.
   * @return True if date1 equals date2.
   */
  public static boolean dateTimesEqual(Date date1, Date date2) {
    int d1Sec = second(date1);
    int d1Min = minute(date1);
    int d1Hour = hour(date1);
    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);

    int d2Sec = second(date2);
    int d2Min = minute(date2);
    int d2Hour = hour(date2);
    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);

    boolean result = d1Year == d2Year && d1Month == d2Month && d1Day == d2Day && d1Hour == d2Hour && d1Min == d2Min
        && d1Sec == d2Sec;

    return result;
  }

  /**
   * Returns the meridian indicator (AM/PM) of the time in the specified date.
   * 
   * @param date
   *          The date to use.
   * @return Meridian indicator (AM/PM).
   */
  public static String meridianIndicator(Date date) {
    String result = null;

    if (hour(date) < 12)
      result = "AM";
    else
      result = "PM";

    return result;
  }

  /**
   * Returns a string time in HHMMSS format from the specified date object with
   * leading zeros preserved. The time is returned in standard 12-hour format.
   * For example: 4:20:16 PM is returned as "042016" rather than "162016". Use
   * <code>meridianIndicator()</code> to determine whether the time is in the AM
   * or PM.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric time in HHMMSS format.
   */
  public static String hh12mmssStringFormat(Date date) {
    return convert(HH12MMSS_FORMATTER, date);
  }

  /**
   * Returns a java.util.Date that represents the specified string date that
   * conforms to the YYYYMMDD format.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date object.
   */
  public static Date yyyyMMddFormat(String date) {
    //    if (date == null || "0".equals(date))
    if (StringUtil.isEmpty(date) || "0".equals(date))
      return null;
    try {
      // TODO
      // Enable this after further testing
      // if ("0".equals(date))
      //   return null;

      return convert(YYYYMMDD_FORMATTER, date);
    }
    catch (Exception e) {
      log.warn("Exception: ", e);
      /* throw new RuntimeException(e.toString()); */
      return null;
    }
  }

  /**
   * Returns a java.util.Date that represents the specified string date that
   * conforms to the MM/DD/YYYY HH:mm format.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date object.
   */
  public static Date fullDateFormat(String date) {
    if (date == null || "0".equals(date))
      return null;
    try {
      return convert(MMDDYYYY_HH24MM_FORMATTER, date);
    }
    catch (Exception e) {
      //log.error("Exception: ", e);
      return null;
    }
  }

  public static Date fullDateTimeFormat(String date) {
    if (date == null || "0".equals(date)
        || (date != null && StringUtil.isEmpty(date.replaceAll("/", "").replaceAll(":", ""))))
      return null;
    try {
      return convert(MMDDYYYY_HH24MMSS_FORMATTER, date);
    }
    catch (Exception e) {
      //log.error("Exception: ", e);
      return null;
    }
  }

  /**
   * Returns a java.util.Date that represents the specified numeric date that
   * conforms to the YYYYMMDD format.
   * 
   * @param date
   *          The numeric date to convert.
   * @return The resulting date object.
   */
  public static Date yyyyMMddFormat(long date) {
    return yyyyMMddFormat(String.valueOf(date));
  }

  /**
   * Returns a string date in YYYYMMDD format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting string date in YYYYMMDD format.
   */
  public static String yyyyMMddStringFormat(Date d) {
    return (d != null) ? StringUtil.lpad(DateUtil.yyyyMMddFormat(d).toString(), 8, '0') : null;
  }

  /**
   * Returns a numeric date in YYYYMMDD format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric date in YYYYMMDD format.
   */
  public static Integer yyyyMMddFormat(Date date) {

    return (date != null) ? Integer.parseInt(convert(YYYYMMDD_FORMATTER, date)) : 0;
  }

  public static final String yyyyMMddHHmmFormat(Date date) {
    return date == null ? "" : convert(YYYYMMDDHHMI_FORMATTER, date);
  }

  public static final String yyyyMMddHHmmssFormat(Date date) {
    return date == null ? "" : convert(YYYYMMDDHHMISS_FORMATTER, date);
  }

  public static final String yyyyMMddHHmmssSSSFormat(Date date) {
    return date == null ? "" : convert(YYYYMMDDHHMMSSSSS_FORMATTER, date);
  }

  /**
   * Returns a numeric date in MMDD format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric date in MMDD format.
   */
  public static Integer mmddIntFormat(Date date) {
    return Integer.parseInt(convert(MMDD_FORMATTER, date));
  }

  public static String mmddStrFormat(String date) {
    return convert(MMDD_FORMATTER, intToDate(Integer.parseInt(date)));
  }

  /**
   * Returns a String date in MM/DD format from the specified String.
   * 
   * @param date
   *          String in YYYYMMDD format
   * @return The resulting String date in MM/DD format.
   */
  public static String MM_ddStrFormat(String date) {
    return convert(MMDD_FORMATTER2, intToDate(Integer.parseInt(date)));
  }

  /**
   * Returns a numeric date in YYYYMMDD format from the specified calendar date.
   * 
   * @param calendar
   *          The calendar to convert the date from.
   * @return The resulting numeric date in YYYYMMDD format.
   */
  public static Integer yyyyMMddFormat(Calendar calendar) {
    return Integer.valueOf(convert(YYYYMMDD_FORMATTER, calendar.getTime()));
  }

  /**
   * Returns a numeric time in HHMM format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric time in HHMM format.
   */
  public static Integer hh24miFormat(Date date) {
    return Integer.parseInt(convert(HH24MI_FORMATTER, date));
  }

  /**
   * Returns a numeric time in HHMMSS format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric time in HHMMSS format.
   */
  public static Integer hh24mmssFormat(Date date) {
    return Integer.parseInt(convert(HH24MMSS_FORMATTER, date));
  }

  /**
   * Returns a numeric time in HHMMSSss format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numeric time in HHMMSS format.
   */
  public static Integer hh24mmssSSFormat(Date date) {
    return (Integer.parseInt(convert(HH24MMssSS_FORMATTER, date))) / 10;
    //    int SS = Integer.parseInt(SSS_FORMATTER.format(date))/10; 
    //    return (Integer.parseInt(HH24MMSS_FORMATTER.format(date)))*100 + SS;
  }

  /**
   * Returns the numberic time in HHMMssss format for the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting numberic time in HHMMssss format.
   */
  //  public static Integer hh24mmssssFormat(Date date){
  //    return Integer.parseInt(HH24MMssss_FORMATTER.format(date));
  //  }
  //  
  /**
   * Returns an Integer date in MMddYY format from the specified numeric date.
   * 
   * @param date
   * @return The resulting date Integer in MMddYY format
   */
  public static String MMddyyFormat(Integer date) {
    String fullDate = MMddyyyyFormat(date);
    fullDate = fullDate.replaceAll("/", "");
    String monthDay = fullDate.substring(0, 4);
    String shortYear = fullDate.substring(6, 8);
    StringBuilder sb = new StringBuilder();
    sb.append(monthDay);
    sb.append(shortYear);
    return sb.toString();
  }

  public static String MMddyyFormat(Date date) {
    return MMddyyFormat(yyyyMMddFormat(date));
  }

  public static String MMddyyyyFormat(Date date) {
    String fullDate = formatDate(date);
    fullDate = fullDate.replaceAll("/", "");
    String monthDay = StringUtil.substring(fullDate, 0, 4);
    String longYear = StringUtil.substring(fullDate, 4, 4);
    StringBuilder sb = new StringBuilder();
    sb.append(monthDay);
    sb.append(longYear);
    return sb.toString();
  }

  /**
   * Returns a String date in MM/dd HH:MM format from the specified string
   * dateTime.
   * 
   * @param String
   *          - string dateTime in mmddyyhhmm format
   * @return The resulting formatted string dateTime in MM/dd HH:MM format
   */
  //KC-1157 : To fix dates not formatted correctly in the Shipment notes from 
  //RR msg properties
  public static String MMdd_HHMMFormat(String dateTime) {
    StringBuilder formattedDateTime = new StringBuilder();

    if (StringUtil.isEmpty(dateTime)) {
      return "";
    }

    dateTime = dateTime.trim();

    if (dateTime.length() < 10 || dateTime.length() > 10)
      return "";

    //build the date
    formattedDateTime.append(dateTime.substring(0, 2));
    formattedDateTime.append("/");
    formattedDateTime.append(dateTime.substring(2, 4));
    //    formattedDateTime.append("/");
    //    formattedDateTime.append(dateTime.substring(4,6));
    formattedDateTime.append(" ");

    //build the time
    formattedDateTime.append(dateTime.substring(6, 8));
    formattedDateTime.append(":");
    formattedDateTime.append(dateTime.substring(8));

    return formattedDateTime.toString();
  }

  public static String MMdd_HHMMFormat(Date date, Integer time) {
    if (date == null)
      return "";

    StringBuilder formattedDateTime = new StringBuilder();
    //build the date
    Integer intDate = yyyyMMddFormat(date);
    formattedDateTime.append(convert(MMDD_FORMATTER2, intToDate(intDate)));

    formattedDateTime.append(StringUtil.generateBlank(1));

    //build the time
    String strTime = StringUtil.fixedPadLeft(time.toString(), 4, '0');
    formattedDateTime.append(StringUtil.substring(strTime, 0, 2));
    formattedDateTime.append(":");
    formattedDateTime.append(StringUtil.substring(strTime, 2, 2));

    return formattedDateTime.toString();
  }

  /**
   * Returns a string date in MM/DD/YYYY format from the specified numeric date.
   * 
   * @param date
   *          The numeric date to convert.
   * @return The resulting date string in MM/DD/YYYY format.
   */
  public static String MMddyyyyFormat(Integer date) {
    String s = date.toString();
    s = StringUtil.lpad(s, 8, '0');
    String year = s.substring(0, 4);
    String month = s.substring(4, 6);
    String day = s.substring(6, 8);
    StringBuilder sb = new StringBuilder();
    sb.append(month);
    sb.append("/");
    sb.append(day);
    sb.append("/");
    sb.append(year);
    return sb.toString();
  }

  /**
   * Returns a string date in MM/DD/YY format from the specified string date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date string in MM/DD/YY format.
   */
  public static String MMddyyFormat(String date) {
    date = StringUtil.lpad(date, 8, '0');
    String month = date.substring(4, 6);
    String day = date.substring(6, 8);
    String year = date.substring(2, 4);
    StringBuilder sb = new StringBuilder();
    sb.append(month);
    sb.append("/");
    sb.append(day);
    sb.append("/");
    sb.append(year);
    return sb.toString();
  }

  /**
   * Returns a string date in MM/DD/YY format from the specified string date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date string in MM/DD/YY format.
   */
  public static Date MMddyyDateFormat(String date) {
    try {
      return convert(MMDDYY_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Returns a string date in MMDDYYYY format from the specified string date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date string in MMDDYYYY format.
   */
  public static Date MMddyyyyDateFormat(String date) {
    try {
      return convert(MMDDYYYY2_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  public static Date MMddyyyyDateFormat2(String date) {
    try {
      return convert(MMDDYYYY2_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      return null;
    }
  }

  /**
   * Returns a string full date in MMDDYYHHmm format from the specified string
   * date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date string in MMDDYYHHmm format.
   */
  public static Date MMddyyHHmmDateFormat(String date) {
    try {
      return convert(MMDDYYHHmm_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Returns a full date in MMDDYYHHmmss format from the specified string date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date in MMDDYYHHmm format.
   */
  public static Date MMddyyHHmmssDateFormat(String date) {
    try {
      return convert(MMDDYYHHmmss_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Returns a string full date in MMDDYYYYHHmm format from the specified string
   * date.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date string in MMDDYYYYHHmm format.
   */
  public static Date MMddyyyyHHmmDateFormat(String date) {
    try {
      return convert(MMDDYYYYHHmm_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Returns a java.util.Date that represents the specified string date that
   * conforms to the YYYY-MM-DD format.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date object.
   */
  public static Date YYYY_MM_DDFormat(String date) {
    try {
      return convert(YYYY_MM_DD_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Returns a java.util.Date that represents the specified numeric date that
   * conforms to the YYYYMMDD format.
   * 
   * @param date
   *          The numeric date to convert.
   * @return The resulting date object.
   */
  public static Date YYYY_MM_DDFormat(long date) {
    return YYYY_MM_DDFormat(String.valueOf(date));
  }

  /**
   * Returns a string date in YYYY-MM-DD format from the specified date object.
   * 
   * @param date
   *          The date object to convert.
   * @return The resulting string date in YYYY-MM-DD format.
   */
  public static String YYYY_MM_DDStringFormat(Date d) {
    String s = DateUtil.yyyyMMddFormat(d).toString();
    s = StringUtil.lpad(s, 8, '0');
    String year = s.substring(0, 4);
    String month = s.substring(4, 6);
    String day = s.substring(6, 8);
    StringBuilder sb = new StringBuilder();
    sb.append(year);
    sb.append("-");
    sb.append(month);
    sb.append("-");
    sb.append(day);
    return sb.toString();
  }

  /**
   * Returns an Integer date in yyyymmdd from 6 digit mmddyy string.
   * 
   * @param String
   *          date in format MMDDYY
   * @return The resulting Integer date in YYYYMMDD format.
   */
  public static Integer YYYYMMDD_From_6(String strDate) {
    int century = 20;
    int year = StringUtil.toInt(StringUtil.substring(strDate, 4, 2));
    if (year > 50)
      century = 19;
    String strC = Integer.toString(century);
    String month = StringUtil.substring(strDate, 0, 2);
    String day = StringUtil.substring(strDate, 2, 2);
    String strY = StringUtil.substring(strDate, 4, 2);
    StringBuilder sb = new StringBuilder();
    sb.append(strC);
    sb.append(strY);
    sb.append(month);
    sb.append(day);
    return StringUtil.toInt(sb.toString());
  }

  /**
   * Returns the number of days that have elapsed since the Powerhouse language
   * epoch start date (December 31, 1899).
   * 
   * @param date
   *          The date to determine the number of days for.
   * @return The number of days since the Powerhouse language epoch start date.
   */
  public static Integer daysFromPHEpoch(Date date) {
    if (date == null)
      return 0;

    Long diff = (date.getTime() - POWERHOUSE_EPOCH_DATE.getTimeInMillis()) / MILLISECONDS_PER_DAY;
    return diff.intValue();
  }

  /**
   * Returns the number of days that have elapsed since the Powerhouse language
   * epoch start date (December 31, 1899).
   * 
   * @param date
   *          The date to determine the number of days for.
   * @return The number of days since the Powerhouse language epoch start date.
   */
  public static Integer daysFromPHEpoch(Integer date) {
    if (date == null)
      return 0;
    return daysFromPHEpoch(intToDate(date));
  }

  /**
   * Returns the "day-of-week" number for the specified date. Sunday = 1 ..
   * Saturday = 7.
   * 
   * @param date
   *          The date to get the "day-of-week" for.
   * @return The "day-of-week" number.
   */
  public static Integer dayOfWeekNumber(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Returns the "day-of-week" number for the specified date. The date must be
   * in YYYYMMDD format. Sunday = 1 .. Saturday = 7.
   * 
   * @param date
   *          The date to get the "day-of-week" for.
   * @return The "day-of-week" number.
   */
  public static Integer dayOfWeekNumber(Integer date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(intToDate(date));
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Returns the "day-of-week" for the specified date.
   * 
   * @param date
   *          The date to get the "day-of-week" for.
   * @return The "day-of-week".
   */
  public static String dayOfWeek(Date date) {
    return convert(DAY_OF_WEEK, date);
  }

  /**
   * Returns the "day-of-week" for the specified date. The date must be in
   * YYYYMMDD format.
   * 
   * @param date
   *          The date to get the "day-of-week" for.
   * @return The "day-of-week".
   */
  public static String dayOfWeek(Integer date) {
    if (intToDate(date) != null)
      return convert(DAY_OF_WEEK, intToDate(date));
    else
      return "";
  }

  /**
   * Returns the month name from the specified month number. January = 1 ..
   * December = 12.
   * 
   * @param month
   *          The month number.
   * @return The month name.
   */
  public static String monthName(Integer month) {
    if (month == null || month == 0) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(now());
    calendar.set(Calendar.MONTH, month - 1);
    return convert(MONTH_NAME, calendar.getTime());
  }

  /**
   * Returns the full month name from the specified month number. January = 1 ..
   * December = 12.
   * 
   * @param month
   *          The month number.
   * @return The full month name.
   */
  public static String fullMonthName(Integer month) {
    if (month == null || month == 0) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(now());
    calendar.set(Calendar.MONTH, month - 1);
    return convert(FULL_MONTH_NAME, calendar.getTime());
  }

  /**
   * Returns a java.util.Date that represents the specified numeric date in
   * YYYYMMDD format.
   * 
   * @param date
   *          The numeric date to convert. Value must be in YYYYMMDD format.
   * @return The converted date object.
   */
  public static Date intToDate(Integer date) {
    if (date == null || date <= 0)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.DAY_OF_MONTH, date % 100);
    // Subtract 1 because month is zero-based.
    calendar.set(Calendar.MONTH, ((date % 10000) / 100) - 1);
    calendar.set(Calendar.YEAR, date / 10000);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents the specified numeric date in
   * YYYYMMDD format, along with a time in HHMM format.
   * 
   * @param date
   *          The numeric date to convert. Value must be in YYYYMMDD format.
   * @param time
   *          The numeric time to convert. Value must be in HHMM format.
   * @return The converted date object.
   */
  public static Date intToDate(Integer date, Integer time) {
    if (date == null || date <= 0)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, date / 10000);
    // Subtract 1 because month is zero-based.
    calendar.set(Calendar.MONTH, ((date % 10000) / 100) - 1);
    calendar.set(Calendar.DAY_OF_MONTH, date % 100);
    calendar.set(Calendar.HOUR_OF_DAY, time / 100);
    calendar.set(Calendar.MINUTE, time % 100);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents the addition of the specified
   * minutes to the specified date. If the number of minutes specified negative,
   * the resulting time will be will be "earlier" than the specified date.
   * 
   * @param date
   *          The date to add minutes to.
   * @param minutes
   *          The number of minutes to add.
   * @return The resulting date object.
   */
  public static Date addMinutesToDate(Date date, Integer minutes) {
    if (date == null)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents to the addition of the specified
   * days to the Powerhouse language epoch start date (December 31, 1899). If
   * the number of days specified is negative, the resulting date will be
   * "earlier" than the specified date.
   * 
   * @param days
   *          The number of days to add.
   * @return The resulting date object.
   */
  public static Date addDaysToPHEpoch(Integer days) {
    if (days == null)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(POWERHOUSE_EPOCH_DATE.getTime());
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents the addition of the specified days
   * to the specified date. If the number of days specified is negative, the
   * resulting date will be "earlier" than the specified date.
   * 
   * @param date
   *          The date to add days to.
   * @param days
   *          The number of days to add.
   * @return The resulting date object.
   */
  public static Date addDaysToDate(Date date, Integer days) {
    if (date == null)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents the addition of the specified
   * months to the specified date. If the number of months specified is
   * negative, the resulting date will be "earlier" than the specified date.
   * 
   * @param date
   *          The date to add months to.
   * @param days
   *          The number of months to add.
   * @return The resulting date object.
   */
  public static Date addMonthsToDate(Date date, Integer month) {
    if (date == null)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, month);
    return calendar.getTime();
  }

  /**
   * Returns a java.util.Date that represents the addition of the specified
   * years to the specified date. If the number of years specified is negative,
   * the resulting date will be "earlier" than the specified date.
   * 
   * @param date
   *          The date to add months to.
   * @param days
   *          The number of months to add.
   * @return The resulting date object.
   */
  public static Date addYearsToDate(Date date, Integer years) {
    if (date == null)
      return null;

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, years);
    return calendar.getTime();
  }

  public static String formatDateFull(Date d) {
    return convert(MMDDYYYY_HH24MM_FORMATTER, d);
  }

  public static String formatDateTimeFull(Date d) {
    return convert(MMDDYYYY_HH24MMSS_FORMATTER, d);
  }

  public static String formatIntDate(int date) {
    if (date <= 1000) {
      return "00/00/0000"; //$NON-NLS-4$
    }
    return convert(MMDDYYYY_FORMATTER, intToDate(date));
  }

  public static String formatDate(Date date) {
    return convert(MMDDYYYY_FORMATTER, date);
  }

  public static String MMDDYYFormat(Date date) {
    String formattedDate = "";

    if (date != null) {
      formattedDate = convert(MMDDYY2_FORMATTER, date);
    }

    return formattedDate;
  }

  public static String formatBigDecimalDate(BigDecimal d) {
    if (null == d) {
      return "";
    }
    if (d.equals(BigDecimal.ZERO)) {
      return "";
    }
    return formatIntDate(d.intValue());
  }

  /**
   * Formats an integer time into a string time with a colon (:)
   * 
   * @param time
   * @return a string with a colon (:) in it.
   */
  public static String formatIntTime(int time) {
    return StringUtil.lpad(Integer.toString(time / 100), 2, '0')
        + ":" + StringUtil.lpad(Integer.toString(time % 100), 2, '0'); //$NON-NLS-1$
  }

  public static String formatBigDecimalTime(BigDecimal t) {
    return formatIntTime(t.intValue());
  }

  public static String formathhmmssTime(Date date) {
    if (date == null)
      return "";
    SimpleDateFormat HHmmss_FORMATTER = new SimpleDateFormat("HH:mm:ss");
    return HHmmss_FORMATTER.format(date);
  }

  /**
   * Returns a java.util.Date that represents the current date adjusted for the
   * user time zone. The time zone is determined from the specified
   * UserClass.TimeZone object.
   * 
   * @param userClassTimeZone
   *          The UserClass TimeZone.
   * @return The adjusted date.
   */
  public static Date getTimezoneAdjustedDate(int userClassTimeZone) {
    return getTimezoneAdjustedDate(userClassTimeZone, now());
  }

  /**
   * Returns a java.util.Date that represents the specified date adjusted for
   * the user time zone. The time zone is determined from the specified
   * UserClass object.
   * 
   * @param userClassTimeZone
   *          The UserClass TimeZone.
   * @param date
   *          The date to adjust.
   * @return The adjusted date.
   */
  public static Date getTimezoneAdjustedDate(int userClassTimeZone, Date date) {
    Calendar calendar = Calendar.getInstance();
    // Set the date.
    calendar.setTime(date);
    // Get the hour of the date.
    int dateHour = calendar.get(Calendar.HOUR_OF_DAY);
    // Get the time zone adjusted hour.
    int adjustedHour = dateHour + userClassTimeZone;

    // If the time zone adjusted hour is >= 24, add a day to the date and adjust
    // the hour accordingly.
    if (adjustedHour >= 24) {
      calendar.add(Calendar.DAY_OF_YEAR, 1);
      calendar.set(Calendar.HOUR_OF_DAY, adjustedHour - 24);
    }
    // If the time zone adjusted hour is < 0, subtract a day from the date and
    // adjust the hour accordingly.
    else if (adjustedHour < 0) {
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      calendar.set(Calendar.HOUR_OF_DAY, adjustedHour + 24);
    }
    // Otherwise, leave the day as is and set the hour to the adjusted hour.
    else {
      calendar.set(Calendar.HOUR_OF_DAY, adjustedHour);
    }

    return calendar.getTime();
  }

  /**
   * Returns an integer time that represents the current time adjusted for the
   * user time zone. The time zone is determined from the specified UserClass
   * object.
   * 
   * @param userClassTimeZone
   *          The UserClass TimeZone.
   * @return The adjusted time represented as an integer in HHMM format.
   */
  public static Integer getTimezoneAdjustedTime(int userClassTimeZone) {
    Calendar calendar = Calendar.getInstance();
    // Set the current date.
    calendar.setTime(now());
    // Get the hour and minute of the date.
    int dateHour = calendar.get(Calendar.HOUR_OF_DAY);
    int dateMin = calendar.get(Calendar.MINUTE);
    // Get the time zone adjusted hour.
    int adjustedHour = dateHour + userClassTimeZone;
    int result = 0;

    if (adjustedHour >= 24) {
      result = (adjustedHour - 24) * 100 + dateMin;
    }
    else if (adjustedHour < 0) {
      result = (adjustedHour + 24) * 100 + dateMin;
    }
    else {
      result = adjustedHour * 100 + dateMin;
    }

    return result;
  }

  /**
   * Returns an integer time that represents the current time.
   * 
   * @return The time represented as an integer in HHMM format.
   */
  public static Integer getCurrentTimeHHMM() {
    Calendar calendar = Calendar.getInstance();
    // Set the current date.
    calendar.setTime(now());
    // Get the hour and minute of the date.
    int dateHour = calendar.get(Calendar.HOUR_OF_DAY);
    int dateMin = calendar.get(Calendar.MINUTE);
    int result = dateHour * 100 + dateMin;
    return result;
  }

  /**
   * Returns a POJO from a list based on the passed integer date with the
   * start/stop range in the POJO
   * 
   * @param resultList
   *          The result list passed in from a database query.
   * @param klass
   *          Class of the POJOs in the resultList (as related to table name).
   * @param fieldDateStart
   *          Name of the field to get the start date from.
   * @param fieldDateEnd
   *          Name of the field to get the end date from.
   * @param actualDate
   *          The actual date to check is between start and end date range.
   * 
   * @return The POJO that whose range matches the date passed in (or null if no
   *         matches).
   */

  public static <T> Object getPojoByDateRange(List<Object> resultList, Class<T> klass, String fieldDateStart,
      String fieldDateEnd, int actualDate) {
    PropertyDescriptor dateStartField = null;
    PropertyDescriptor dateEndField = null;

    if (null != resultList) {
      try {
        BeanInfo beanInfo = Introspector.getBeanInfo(klass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
          if (fieldDateStart.equals(propertyDescriptor.getName())) {
            dateStartField = propertyDescriptor;
          }
          else if (fieldDateEnd.equals(propertyDescriptor.getName())) {
            dateEndField = propertyDescriptor;
          }
        }
      }
      catch (Exception e) {
        log.error("Exception: ", e);
      }

      if (null != dateStartField && null != dateEndField) {
        for (Object pojo : resultList) {
          Method readMethod = null;
          Object start;
          Object end;
          readMethod = dateStartField.getReadMethod();
          start = getValue(readMethod, pojo);
          readMethod = dateEndField.getReadMethod();
          end = getValue(readMethod, pojo);
          if (null != start && null != end && actualDate > (Integer) start && actualDate < (Integer) end) {
            return pojo;
          }
        }
      }
    }
    return null;
  }

  private static Object getValue(Method readMethod, Object pojo) {
    Object value = null;
    try {
      value = readMethod.invoke(pojo);
    }
    catch (IllegalAccessException iae) {
    }
    catch (InvocationTargetException iae) {
    }
    return value;
  }

  /**
   * calcBusinessDueDate will calculate x number of days from the startDate,
   * taking into account weekends and holidays (if any).
   * 
   * @param holidayList
   * @param startDate
   * @param days
   * @return paymentDueDate
   * 
   */
  public static Date calcBusinessDueDate(List<Date> holidayList, Date startDate, int days) {
    boolean isDateBad;
    Calendar cal;
    //null check for startDate
    if (startDate == null)
      startDate = now();

    if (holidayList.size() == 0) {

      cal = Calendar.getInstance();
      cal.setTime(startDate);

      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
        cal.add(Calendar.DAY_OF_YEAR, 2);
        days--;
      }
      else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        cal.add(Calendar.DAY_OF_YEAR, 1);
        days--;
      }
      while (days > 0) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          days++;
        }
        else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          days++;
        }

        cal.add(Calendar.DAY_OF_YEAR, 1);
        days--;
      }
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
        cal.add(Calendar.DAY_OF_YEAR, 2);
      }
      else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        cal.add(Calendar.DAY_OF_YEAR, 1);
      }
    }
    else { //Holidays were found
      cal = Calendar.getInstance();
      cal.setTime(startDate);
      //bugfix # 6214
      final int totalDays = days;
      isDateBad = true;
      while (isDateBad) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
          if (days == totalDays)
            days--;
        }
        else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
          if (days == totalDays)
            days--;
        }
        else if (isTodayAHoliday(holidayList, cal.getTime())) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
          if (days == totalDays)
            days--;
        }
        else {
          isDateBad = false;
        }
      }

      while (days > 0) {
        if (isTodayAHoliday(holidayList, cal.getTime())) {
          if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            days += 2;
          }
          else {
            days++;
          }
        }
        else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          days++;
        }
        else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          days++;
        }

        cal.add(Calendar.DAY_OF_YEAR, 1);
        days--;
      }

      isDateBad = true;
      while (isDateBad) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        else if (isTodayAHoliday(holidayList, cal.getTime())) {
          cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        else {
          isDateBad = false;
        }
      }
    }
    return cal.getTime();
  }

  public static boolean isTodayAHoliday(List<Date> holidayList, Date date) {
    Calendar today = Calendar.getInstance();
    today.setTime(date);
    for (int i = 0; i < holidayList.size(); i++) {
      Calendar holiday = Calendar.getInstance();
      holiday.setTime(holidayList.get(i));
      if (holiday.get(Calendar.YEAR) == today.get(Calendar.YEAR))
        if (holiday.get(Calendar.MONTH) == today.get(Calendar.MONTH))
          if (holiday.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))
            return true;
    }
    return false;
  }

  public static Integer getReverseTime(Date date) {
    Integer time = hh24mmssSSFormat(date) / 10;
    return time - 99999999;
  }

  public static String formatDateFullSeconds(Date d) {
    return convert(MMDDYYYY_HH24MMSS_FORMATTER, d);
  }

  /**
   * Get a date(YYYYMMDD format) that is one day before the parameter
   * date(YYYYMMDD format).
   * 
   * @param date
   *          in YYYYMMDD format.
   * @return one day before the param, in YYYYMMDD format.
   */
  public static Integer getOneDayBefore(Integer date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(intToDate(date));
    calendar.add(Calendar.DATE, -1);
    Date d = calendar.getTime();
    Integer oneDayBefore = yyyyMMddFormat(d);
    return oneDayBefore;
  }

  public static int compareDateTime(Date date1, Date date2) {

    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);
    int d1Hour = hour(date1);
    int d1Minute = minute(date1);

    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);
    int d2Hour = hour(date2);
    int d2Minute = minute(date2);

    if (d1Year == d2Year) {
      if (d1Month == d2Month) {
        if (d1Day == d2Day) {
          if (d1Hour == d2Hour) {
            if (d1Minute == d2Minute)
              return 0;
            else if (d1Minute < d2Minute)
              return -1;
            else
              return 1;
          }
          else if (d1Hour < d2Hour)
            return -1;
          else
            return 1;
        }
        else if (d1Day < d2Day)
          return -1;
        else
          return 1;
      }
      else if (d1Month < d2Month)
        return -1;
      else
        return 1;
    }
    else if (d1Year < d2Year)
      return -1;
    else
      return 1;
  }

  public static int compareDateTimeToSecond(Date date1, Date date2) {

    int d1Month = month(date1);
    int d1Day = day(date1);
    int d1Year = year(date1);
    int d1Hour = hour(date1);
    int d1Minute = minute(date1);
    int d1Second = second(date1);

    int d2Month = month(date2);
    int d2Day = day(date2);
    int d2Year = year(date2);
    int d2Hour = hour(date2);
    int d2Minute = minute(date2);
    int d2Second = second(date2);

    if (d1Year == d2Year) {
      if (d1Month == d2Month) {
        if (d1Day == d2Day) {
          if (d1Hour == d2Hour) {
            if (d1Minute == d2Minute)
              if (d1Second == d2Second)
                return 0;
              else if (d1Second < d2Second)
                return -1;
              else
                return 1;
            else if (d1Minute < d2Minute)
              return -1;
            else
              return 1;
          }
          else if (d1Hour < d2Hour)
            return -1;
          else
            return 1;
        }
        else if (d1Day < d2Day)
          return -1;
        else
          return 1;
      }
      else if (d1Month < d2Month)
        return -1;
      else
        return 1;
    }
    else if (d1Year < d2Year)
      return -1;
    else
      return 1;
  }

  /**
   * Inject the time inforation into the date parameter.
   * 
   * @param date
   * @param time
   *          , with HHMMSS or HHMM format.
   * @return the new date
   */
  public static Date injectTimeIntoDate(Date date, String time) {
    if (date == null)
      return null;
    if (StringUtil.isEmpty(time))
      return date;
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, NumberUtil.parseInt(StringUtil.substring(time, 0, 2)));
    c.set(Calendar.MINUTE, NumberUtil.parseInt(StringUtil.substring(time, 2, 2)));
    c.set(Calendar.SECOND, NumberUtil.parseInt(StringUtil.substring(time, 4, 2)));
    return c.getTime();
  }

  /**
   * Returns a java.util.Date that represents the specified string date that
   * conforms to the MM/DD/YYYY format.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date object.
   */
  public static Date dateFormat(String date) {
    if (date == null || "0".equals(date))
      return null;
    try {
      return convert(MMDDYYYY_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      return null;
    }
  }

  /**
   * Returns a java.util.Date that represents the specified string date that
   * conforms to the MM/DD/YY format.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date object.
   */
  public static Date yyDateFormat(String date) {
    if (date == null || "0".equals(date))
      return null;
    try {
      return convert(MMDDYY2_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      return null;
    }
  }

  /**
   * Returns a date parsed from string data in MMddyyhhmmssa format. The time is
   * expected to be represented in 12-hour format. '<code>a</code>' represents
   * the meridian indicator (AM/PM).<br>
   * <br>
   * For exmaple: "070411034520PM" will be converted into a <code>Date</code>
   * representing July 4, 2011 15:45:20.
   * 
   * @param date
   *          The string date to convert.
   * @return The resulting date.
   */
  public static Date MMddyyhhmmssaDateFormat(String date) {
    try {
      return convert(MMddyyhhmmssa_FORMATTER, date);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
  }

  public static String formatDateTimeFullMilliseconds(Date d) {
    return convert(MMDDYYYY_HH24MMSSSSS_FORMATTER, d);
  }

  /**
   * Sets the date's time based on the values given
   * 
   * @param date
   *          to be used
   * @param hour
   * @param min
   * @param sec
   * @return the Date object with the new time.
   */
  public static Date setTime(Date date, int hour, int min, int sec) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR, hour);
    cal.set(Calendar.MINUTE, min);
    cal.set(Calendar.SECOND, sec);
    return cal.getTime();
  }

  private static String convert(DateTimeFormatter dtf, Date date) {
    String str = null;
    try {
      str = (new DateTime(date)).toString(dtf);
    }
    catch (Exception e) {
      log.error("Exception: ", e);
      throw new RuntimeException(e.toString());
    }
    return str;
  }

  private static Date convert(DateTimeFormatter dtf, String date) {
    // Note that we do not do try / catch because the caller methods already do that
    return dtf.parseDateTime(date).toDate();
  }

  /**
   * Returns a java.util.Date that represents the specified numeric date that
   * conforms to the format passed in.
   * 
   * @param str
   *          The numeric date to convert. format The date time pattern string
   * 
   * @return The resulting date object.
   */
  public static Date parseDate(String str, String format) {
    Date value = null;
    if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(format)) {
      try {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        value = formatter.parseDateTime(str).toDate();
      }
      catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
    return value;
  }

  public static Date parseDateNoException(String str, String format) { // KC-7081
    Date value = null;
    if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(format)) {
      try {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        value = formatter.parseDateTime(str).toDate();
      }
      catch (Exception e) {
        //log.error(e.getMessage(), e);
      }
    }
    return value;
  }

  /**
   * Formats an date into a string date as the format passed in
   * 
   * @param date
   *          date to be formatted format format pattern
   * @return a formatted string date
   */
  public static String formatDate(Date date, String format) {
    String dateStr = "";
    try {
      if (date != null && StringUtil.isNotEmpty(format)) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        dateStr = (new DateTime(date)).toString(formatter);
      }
      else
        dateStr = StringUtil.toString(date);
    }
    catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return dateStr;
  }

  public static String formatDateNoException(Date date, String format) {
    String dateStr = "";
    try {
      if (date != null && StringUtil.isNotEmpty(format)) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        dateStr = (new DateTime(date)).toString(formatter);
      }
      else
        dateStr = StringUtil.toString(date);
    }
    catch (Exception e) {
      //      log.error(e.getMessage(), e);
    }
    return dateStr;
  }

  public static Date getDateByType(String type) {
    return getDateByType(type, false);
  }

  public static Date getDateByType(String type, boolean dateOnly) {
    Date now = null;
    if (dateOnly)
      now = DateUtil.parseDate(DateUtil.formatDate(DateUtil.now(), "MM/dd/yyyy"), "MM/dd/yyyy");//get rid of the hhmmss
    else
      now = DateUtil.now();

    if ("T".equalsIgnoreCase(type)) {
      return now;
    }
    else if ("Y".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.add(Calendar.DATE, -1);
      return cal.getTime();
    }
    else if ("PS".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
      cal.set(Calendar.DATE, 1);
      return cal.getTime();
    }
    else if ("PE".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
      cal.set(Calendar.DATE, 1);
      cal.roll(Calendar.DATE, -1);
      return cal.getTime();
    }
    else if ("NS".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
      cal.set(Calendar.DATE, 1);
      return cal.getTime();
    }
    else if ("NE".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
      cal.set(Calendar.DATE, 1);
      cal.roll(Calendar.DATE, -1);
      return cal.getTime();
    }
    else if ("CS".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.DATE, 1);
      return cal.getTime();
    }
    else if ("CE".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.DATE, 1);
      cal.roll(Calendar.DATE, -1);
      return cal.getTime();
    }
    else if ("WS".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.DAY_OF_WEEK, 1);
      return cal.getTime();
    }
    else if ("WE".equals(type)) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.set(Calendar.DAY_OF_WEEK, 1);
      cal.roll(Calendar.DAY_OF_WEEK, -1);
      return cal.getTime();
    }
    else if ("EA".equals(type)) {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 1900);
      cal.set(Calendar.MONTH, 1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return cal.getTime();
    }
    else if ("LA".equals(type)) {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2099);
      cal.set(Calendar.MONTH, 12);
      cal.set(Calendar.DAY_OF_MONTH, 31);
      return cal.getTime();
    }
    return null;
  }

  public static String formatTime(int time) {
    return StringUtil.lpad(Integer.toString(((time / 100) > 12) ? ((time / 100) % 12) : (time / 100)), 2, '0')
        + ":" + StringUtil.lpad(Integer.toString(time % 100), 2, '0') + " " + StringUtil.lpad((((time / 100) >= 12 || (time / 100) == 24) ? "PM" : "AM"), 2, '0'); //$NON-NLS-1$
  }
}
