package dev.practice.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.practice.db.IFilter;

/**
 * Util for system configuration. 
 * 
 * @author Nick.Laborera
 *
 */
public final class SystemUtil {

  private static final Log    log                               = LogFactory.getLog(SystemUtil.class);

  public static final String  DEFAULT_PERSPECTIVE               = "dev.shipment.perspective";

  public static final String  POPUP_LIMIT_KEY                   = "dev.popup.limit";
  public static final String  DATABASE_INFO_KEY                 = "dev.database.info";

  //root dir of ABI with / at the end.
  private static final String ABI_ROOT_DIR_KEY                  = "dev.abi.root.dir";
  private static final String ABI_CONTROL_FILENAME_KEY          = "dev.abi.control.filename";
  private static final String AUTOLOAD_RULES_KEY                = "dev.autoinit.rules";
  private static final String SERIALIZED_RULES_KEY              = "dev.serialized.rules";
  private static final String METHOD_CALL_RULES_KEY             = "dev.methodcall.rules";
  private static final String LOG_FILENAME_KEY                  = "dev.log.filename";
  private static final String RUN_MODE_KEY                      = "dev.mode";
  private static final String TESTING_MODE_KEY                  = "dev.testing";
  private static final String TESTING_USERNAME_KEY              = "dev.testing.username";
  private static final String DATABASE_TYPE_KEY                 = "dev.database.type";
  private static final String JAVA_IO_TMPDIR_KEY                = "java.io.tmpdir";
  private static final String SERIALIZED_RESPONSE_OBJECTS_KEY   = "dev.serialized.response.objects";
  private static final String SERIALIZED_REQUEST_OBJECTS_KEY    = "dev.serialized.request.objects";
  private static final String DEFAULT_PERSPECTIVE_KEY           = "dev.perspective";
  private static final String OBJECT_CACHED_KEY                 = "dev.object.cached";

  private static final String REMOTE_READ_TIMEOUT               = "dev.remote.read.timeout";
  private static final String REMOTE_CONNECT_TIMEOUT            = "dev.remote.connect.timeout";

  private static final String LOG_FILE_PATH                     = "dev.log.file.path";
  private static final String SYSTEM_ROOT_DIR_KEY               = "dev.system.root.dir";
  private static final String ADMIN_URL                         = "dev.admin.url";
  private static final String SCHEDULER_URL                     = "dev.scheduler.url";
  private static final String SCHEDULER_MODE                    = "dev.scheduler.mode";
  private static final String WELCOME_URL                       = "dev.welcome.url";
  //  private static final String ISF_TRANSACTION_URL          = "isf.transaction.url";
  //  private static final String ISF_UPLOAD_URL               = "isf.upload.url";
  //email stuff
  //  private static final String EMAIL_FROM_ADDRESS           = "email.from.address";
  //  private static final String EMAIL_FOOTER                 = "email.footer";
  //  private static final String EMAIL_SENDER                 = "email.sender";
  //  private static final String EMAIL_BOUNCE_ADDRESS         = "email.bounce.address";
  //  private static final String EMAIL_HOST                   = "email.host";
  //  private static final String EMAIL_TEMPLATE               = "email.template";
  //  private static final String EMAIL_SUBJECT                = "email.subject";

  private static final String LARGE_PREALLOCATION_SIZE_         = "large.preallocation.size";
  private static final String MEDIUM_PREALLOCATION_SIZE_        = "medium.preallocation.size";
  private static final String SMALL_PREALLOCATION_SIZE_         = "small.preallocation.size";

  private static final int    DEFAULT_LARGE_PREALLOCATION_SIZE  = 200;
  private static final int    DEFAULT_MEDIUM_PREALLOCATION_SIZE = 100;
  private static final int    DEFAULT_SMALL_PREALLOCATION_SIZE  = 50;

  public static boolean isAutoinitRules() {
    return getBoolean(AUTOLOAD_RULES_KEY, true);
  }

  public static boolean isObjectCached() {
    return getBoolean(OBJECT_CACHED_KEY, true);
  }

  public static boolean isObjectInCacheList(String name) {
    String[] cacheList = new String[]{"State", "Country", "Port", "TariffClass", "TariffOgaClass", "EntryTypes",
        "TransMode", "Carrier", "Location", "UomFile", "CurrencyRates", "ValidSpi", "Customer", "CustomerAux",
        "CustAddresses", "CustContact", "UserClass", "UserProfile", "DistrictPort", "CustDefaults", "BrokerControl",
        "BrokerControlAux", "SystemParameter"};
    for (int i = 0; i < cacheList.length; i++) {
      if (StringUtil.areEqual(name, cacheList[i]))
        return true;
    }
    return false;
  }

  public static String getAbiDirectory() {
    return getSystemProperty(ABI_ROOT_DIR_KEY, "C:/temp/");
  }

  public static String getLogFilePath() {
    return getSystemProperty(LOG_FILE_PATH, "/kcustoms/logs/");
  }

  public static String getSystemRootDir() {
    return getSystemProperty(SYSTEM_ROOT_DIR_KEY, "/");
  }

  public static String getDefaultPerspective() {
    return getSystemProperty(DEFAULT_PERSPECTIVE_KEY, DEFAULT_PERSPECTIVE);
  }

  public static String getDatabaseType() {
    return getSystemProperty(DATABASE_TYPE_KEY, "ORACLE");
  }

  /*public static String getPrimaryURL() {
    return getSystemProperty(RemotePersistor.PRIMARY_URL, "http://localhost:8181/dev/MainController");
  }*/

  public static String getAdminURL() {
    return getSystemProperty(ADMIN_URL, "http://localhost:8181/dev/admin");
  }

  public static String getSchedulerURL() {
    return getSystemProperty(SCHEDULER_URL, "http://localhost:8181/dev/scheduler");
  }

  public static String getSchedulerModel() {
    return getSystemProperty(SCHEDULER_MODE, "info");
  }

  public static String getWelcomeURL() {
    return getSystemProperty(WELCOME_URL, null);
  }

  //  public static String getIsfTransactionURL() {
  //    return getSystemProperty(ISF_TRANSACTION_URL, "http://10.10.96.154:8080/kc/request?wsdl");
  //  }

  //  public static String getIsfUploadURL() {
  //    return getSystemProperty(ISF_UPLOAD_URL, "http://10.10.96.154:8080/kc/upload?wsdl");
  //  }

  public static String getAbiControlFilename() {
    return getSystemProperty(ABI_CONTROL_FILENAME_KEY, "ABICTL");
  }

  public static String getLogFilename() {
    return getSystemProperty(LOG_FILENAME_KEY, "customs.log");
  }

  public static boolean is3Tier() {
    String s = getSystemProperty(RUN_MODE_KEY, "2tier");
    if ("3tier".equals(s.trim()))
      return true;
    return false;
  }

  /**
   * Check to see if the application is in testing mode.
   * 
   * @return true if the application is in testing mode.
   */
  public static boolean isTesting() {
    String s = getSystemProperty(TESTING_MODE_KEY, "false");
    if (s.equals("true"))
      return true;
    return false;
  }

  /**
   * The default username for testing mode.
   * 
   * @return the default username for testing mode.
   */
  public static String getTestingUsername() {
    return getSystemProperty(TESTING_USERNAME_KEY, "NICK");
  }

  /**
   * Allow naked method calls on Rules.
   * 
   * @return the default username for testing mode.
   */
  public static Boolean getAllowNakeMethodCall() {
    return getBoolean(METHOD_CALL_RULES_KEY, true);
  }

  /**
   * Allow naked method calls on Rules.
   * 
   * @return the default username for testing mode.
   */
  public static Boolean isAllowNakeMethodCall() {
    return getBoolean(METHOD_CALL_RULES_KEY, true);
  }

  /**
   * Client read timeout to server.
   * The default value is 30 minutes 
   * 
   * @return timeout in seconds
   */
  public static int getRemoteReadTimeout() {
    return getInteger(REMOTE_READ_TIMEOUT, 1800) * 1000;
  }

  /**
   * Client connect timeout to server.
   * The default value is 30 minutes 
   * 
   * @return timeout in seconds
   */
  public static int getRemoteConnectTimeout() {
    return getInteger(REMOTE_CONNECT_TIMEOUT, 1800) * 1000;
  }

  /*public static String getRemoteInfo() {
    String site = "";
    Map<String, String> info = ControllerDataManager.getRemoteInfo();
    if (info != null)        
      site = "Site is " + info.get(RemotePersistor.DATABASE_SCHEMA) + "@" + info.get(RemotePersistor.CONTEXT_PATH);
    return site;
  }

  public static String getDatabaseInfo() {
    String s = "";
    Map<String, String> info = ControllerDataManager.getRemoteInfo();
    if (info != null)        
      s = info.get(RemotePersistor.DATABASE_INFO);
    if (s == null)
      s = "NO_CONNECTION";
    return s;
  }

  public static String getDatabaseSchema() {
    String s = null;
    Map<String, String> info = ControllerDataManager.getRemoteInfo();
    if (info != null)        
      s = info.get(RemotePersistor.DATABASE_SCHEMA);
    return s;
  }
  */
  //  public static String getInterconnectURL() {
  //    String s = null;
  //    Map<String, String> info = ControllerDataManager.getRemoteInfo();
  //    if (info != null)        
  //      s = info.get(RemotePersistor.INTERCONNECT_URL);
  //    return s;
  //  }

  /*public static Date getRemoteDate() {
    RemotePersistor p = null;
    try {
      p = new RemotePersistor();
      Map<String, Object> map = p.date();
      if (map != null) {
        return (Date) map.get(RemotePersistor.SERVER_DATE);
      }
      else {
        log.warn("Please check network connection. The server did not return a remote date");
        return new Date();
      }
    }
    finally {
      IOHelper.close(p);
    }
  }*/

  /**
   * Return the setting is set to serialized the rules.
   * 
   * @return true if the setting is set to serialized the rules.
   */
  public static boolean isSerializedRules() {
    // KC-4112. Changed default to true.
    //
    // We do this because in
    // an actual deployed /3-tier environment we want the DRLS to
    // be serialized so that the server doesn't create unwanted
    // overhead by constantly recompiling the DRLS every time
    // they are accessed.
    //
    // On the other hand, in our own Eclipse/2-tier development
    // environments we probably want the drls to be recompiled
    // every time. This is done via the config.ini file which is
    // a develop-only configuration file that can be used to
    // override default settings
    return getBoolean(SERIALIZED_RULES_KEY, true);
  }

  /**
   * Return the cap for popup query.
   * 
   * @return max result for popup.
   */
  public static int getLimitOnPopupQuery() {
    return getInteger(POPUP_LIMIT_KEY, IFilter.POPUP_LIMIT_VALUE);
  }

  public static boolean isSerializedResponseObjects() {
    return getBoolean(SERIALIZED_RESPONSE_OBJECTS_KEY, false);
  }

  public static boolean isSerializedRequestObjects() {
    return getBoolean(SERIALIZED_REQUEST_OBJECTS_KEY, false);
  }

  public static String getTempDirectory() {
    return getSystemProperty(JAVA_IO_TMPDIR_KEY, "c:/temp");
  }

  //  //EMAIL 
  //  public static String getEmailFromAddress(){
  //    return getSystemProperty(EMAIL_FROM_ADDRESS, "KewillCustoms@kewill.com");
  //  }
  //  
  //  public static String getEmailFooter(){
  //    return getSystemProperty(EMAIL_FOOTER, "kewill_email_footer.html");
  //  }
  //  
  //  public static String getEmailSender(){
  //    return getSystemProperty(EMAIL_SENDER, "KewillCustoms@kewill.com");
  //  }
  //  
  //  public static String getEmailBounceAddress(){
  //    return getSystemProperty(EMAIL_BOUNCE_ADDRESS, "nicky.laborera@kewill.com");
  //  }
  //  
  //  public static String getEmailHost(){
  //    return getSystemProperty(EMAIL_HOST, "nashexch.KWLCORP.NET");
  //  }
  //  
  //  public static String getEmailSubject(){
  //    return getSystemProperty(EMAIL_SUBJECT, "");
  //  }
  //  
  //  public static String getEmailTemplate() {
  //    return getSystemProperty(EMAIL_TEMPLATE, "");
  //  }

  public static int getLargePreallocationSize() {
    return getInteger(LARGE_PREALLOCATION_SIZE_, DEFAULT_LARGE_PREALLOCATION_SIZE);
  }

  public static int getMediumPreallocationSize() {
    return getInteger(MEDIUM_PREALLOCATION_SIZE_, DEFAULT_MEDIUM_PREALLOCATION_SIZE);
  }

  public static int getSmallPreallocationSize() {
    return getInteger(SMALL_PREALLOCATION_SIZE_, DEFAULT_SMALL_PREALLOCATION_SIZE);
  }

  public static String getHostname() {
    // try environment properties for windows or vms.
    String host = System.getenv("COMPUTERNAME");
    if (host != null)
      return host;
    //for aix or linux
    host = System.getenv("HOSTNAME");
    if (host != null)
      return host;

    try {
      //if the first 2 doesn't work
      host = InetAddress.getLocalHost().getHostName();
      if (host != null)
        return host;
    }
    catch (UnknownHostException e) {
      // ignoring the error
    }

    // undetermined.
    return null;
  }

  private static int getInteger(String key, int defaultValue) {
    try {
      String s = getSystemProperty(key, null);
      if (s != null)
        return Integer.valueOf(s);
    }
    catch (NumberFormatException e) {
      //ignore the error
      log.warn("Conversion error for " + key);
    }
    return defaultValue;
  }

  private static boolean getBoolean(String key, boolean defaultValue) {
    try {
      String s = getSystemProperty(key, null);
      if (s != null)
        return Boolean.valueOf(s);
    }
    catch (Exception e) {
      //ignore the error
      log.warn("Conversion error for " + key);
    }
    return defaultValue;
  }

  private static String getSystemProperty(String p, String defaultValue) {
    String s = System.getProperty(p);
    if (s != null) {
      return s;
    }
    else {
      if (log.isTraceEnabled())
        log.trace("Setup not found for: " + p);
      return defaultValue;
    }
  }

}
