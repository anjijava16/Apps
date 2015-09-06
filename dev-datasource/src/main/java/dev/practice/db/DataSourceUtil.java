package dev.practice.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

import jndi.naming.provider.MemoryContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.practice.db.connection.IOHelper;

/**
 * Utility for Database data source
 * 
 * Note: This is not a thread safe class.
 * load this upon startup of the application.
 * 
 * @author KondalReddy
 */
public final class DataSourceUtil {

  private final static Log     log                   = LogFactory.getLog(DataSourceUtil.class);
  private static final String  DATABASE_TYPE_KEY     = "dev.database.type";

  private static MemoryContext ctx                   = null;

  private final static String  DRIVER_CLASSNAME      = "dev.db.driverClassName";
  private final static String  INITIAL_SIZE          = "dev.db.initialSize";
  private final static String  MAX_ACTIVE            = "dev.db.maxActive";
  private final static String  MAX_IDLE              = "dev.db.maxIdle";
  private final static String  MIN_IDLE              = "dev.db.minIdle";
  private final static String  MAX_WAIT              = "dev.db.maxWait";
  private final static String  URL                   = "dev.db.url";
  private final static String  USERNAME              = "dev.db.username";
  private final static String  PASSWORD              = "dev.db.password";
  private final static String  EVICTION_DELAY        = "dev.db.timeBetweenEvictionRunsMillis";
  private final static String  EVICTION_TIME_DELAY   = "dev.db.minEvictableIdleTimeMillis";
  private final static String  CONNECTION_PROPERTIES = "dev.db.connectionProperties";

  private static Properties    config                = new Properties();
  private static DataSource    ds                    = null;

  private static String        dbUser                = null;
  private static String        dbUrl                 = null;

  static {
    InputStream is = null;
    try {
      is = DataSourceUtil.class.getClassLoader().getResourceAsStream("db.properties");
      config.load(is);
    }
    catch (IOException e) {
      log.error("error loading db.properties from class loader", e);
    }
    finally {
      IOHelper.close(is);
    }
  }

  public static DataSource lookupDatasource() {
    try {
      Hashtable<String, Object> env = new Hashtable<String, Object>();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "jndi.naming.provider.MemoryContextFactory");

      if (ctx == null && ds == null) {
        //check if config file loads correctly
        if (config == null)
          throw new RuntimeException("error loading db.properties from class loader");

        Reference ref = new Reference("javax.sql.DataSource", "org.apache.commons.dbcp.BasicDataSourceFactory", null);
        ref.add(new StringRefAddr("driverClassName", config.getProperty(DRIVER_CLASSNAME)));
        dbUrl = config.getProperty(URL);
        dbUser = config.getProperty(USERNAME);
        ref.add(new StringRefAddr("url", dbUrl));
        ref.add(new StringRefAddr("username", dbUser));
        ref.add(new StringRefAddr("password", config.getProperty(PASSWORD)));
        ref.add(new StringRefAddr("initialSize", config.getProperty(INITIAL_SIZE)));
        ref.add(new StringRefAddr("maxActive", config.getProperty(MAX_ACTIVE)));
        ref.add(new StringRefAddr("maxIdle", config.getProperty(MAX_IDLE)));
        ref.add(new StringRefAddr("minIdle", config.getProperty(MIN_IDLE)));
        ref.add(new StringRefAddr("maxWait", config.getProperty(MAX_WAIT)));
        ref.add(new StringRefAddr("timeBetweenEvictionRunsMillis", config.getProperty(EVICTION_DELAY)));
        ref.add(new StringRefAddr("minEvictableIdleTimeMillis", config.getProperty(EVICTION_TIME_DELAY)));

        if (!isRMS())
          ref.add(new StringRefAddr("validationQuery", "select 1 from dual"));

        ref.add(new StringRefAddr("removeAbandonedTimeout", "300"));
        ref.add(new StringRefAddr("removeAbandoned", "true"));
        ref.add(new StringRefAddr("logAbandoned", "true"));
        ref.add(new StringRefAddr("accessToUnderlyingConnectionAllowed", "true"));
        ref.add(new StringRefAddr("poolPreparedStatements", "false"));

        String cp = config.getProperty(CONNECTION_PROPERTIES);
        if (cp == null) {
          ref.add(new StringRefAddr("connectionProperties", "fixedString=true;"));
        }
        else {
          ref.add(new StringRefAddr("connectionProperties", cp));
        }

        log.debug(config.toString());

        ctx = new MemoryContext(env);
        ctx.bind("devDS", ref);
        ds = (DataSource) ctx.lookup("devDS");
      }
    }
    catch (NamingException e) {
      log.error("error on datasource lookup", e);
    }
    return ds;
  }

  public static String getDbUser() {
    return dbUser;
  }

  public static String getDbUrl() {
    return dbUrl;
  }

  public static boolean isRMS() {
    return "RMS".equals(getDatabaseType());
  }

  public static String getDatabaseType() {
    return getSystemProperty(DATABASE_TYPE_KEY, "ORACLE");
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
