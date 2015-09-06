package dev.practice.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import oracle.toplink.essentials.config.TopLinkProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author KondalReddy
 *
 */
public final class EntityResource {
  private static final Log       log      = LogFactory.getLog(EntityResource.class);
  private int                    counter  = 0;
  protected EntityManagerFactory emf;
  private String                 buildId  = "";

  private static EntityResource  INSTANCE = new EntityResource();

  private EntityResource() {
    try {
      if (log.isDebugEnabled())
        log.debug("Loading EMF.");

      DataSource ds = DataSourceUtil.lookupDatasource();

      Map<String, Object> map = new HashMap<String, Object>();
      map.put(TopLinkProperties.NON_JTA_DATASOURCE, ds);

      emf = Persistence.createEntityManagerFactory("devds", map);

      if (log.isDebugEnabled())
        log.debug("EMF Loaded.");
    }
    catch (Exception e) {
      log.error("Error creating entity manager factory.", e);
      throw new PersistenceException(e);
    }
  }

  public static EntityResource getInstance() {
    return INSTANCE;
  }

  public EntityManager createEntityManager() {
    counter++;
    if (log.isTraceEnabled()) {
      log.trace("Current connection count:" + counter);
      if (counter >= 25)
        throw new PersistenceException("Leaking connection.");
    }
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
    }
    catch (Exception e) {
      log.error("Error creating entity manager.");
      throw new PersistenceException(e);
    }
    return em;
  }

  /**
   * Dummy close.
   */
  public void close() {
    counter--;
  }

  /**
   * Close Entity Manager factory and database pool. 
   */
  public void closeEntityManagerFactory() {
    log.debug("Closing EntityManagerFactory.");
    emf.close();
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public String getBuildId() {
    return buildId;
  }
}
