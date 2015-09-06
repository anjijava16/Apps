package dev.practice.db.connection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.practice.db.EntityResource;
import dev.practice.db.EntityUtil;
import dev.practice.db.ICallback;
import dev.practice.db.IEntity;
import dev.practice.db.IEntityId;
import dev.practice.db.IExceptionBag;
import dev.practice.db.IFilter;
import dev.practice.db.IOptimisticLock;

/**
 * Persistence wrapper that is design to work with JPA, 
 * so that it will work for existing Alliance database.
 * 
 * @author KondalReddy
 */
public class Persistor implements IPersistor {

  private static final Log    log = LogFactory.getLog(Persistor.class);

  private final EntityManager em;
  private List<Object>        callbackList;

  public Persistor() {
    em = EntityResource.getInstance().createEntityManager();
    em.setFlushMode(FlushModeType.COMMIT);
    callbackList = new ArrayList<Object>();
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#persist(java.lang.Object, com.kewill.dev.vo.IValidateField[], com.kewill.dev.vo.IExceptionBag)
   */
  public Object persist(final Serializable object, final IExceptionBag bag) {

    //    //no validation
    //    if (validators == null) {
    return persistOrMerge(object, bag);
    //    }
    //    else {
    //      //run the validation
    //      validate(object, validators, bag);
    //      //no error was found, so persist.
    //      if (!bag.hasErrors()) {
    //        return this.persist(object, bag);
    //      }
    //    }
    //    return object;
  }

  //  public IExceptionBag validate(final Serializable object, final IExceptionBag bag) {
  ////    for (final IValidatable v : validators) {
  ////      v.validate(object, bag);
  ////    }
  ////    return bag;
  //    return null;
  //  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#find(java.lang.Class, java.lang.Object)
   */
  public <T> T find(final Class<T> type, final Serializable object) {
    Object pk = object;
    if (pk == null)
      return null;
    if (pk instanceof IEntityId<?>) {
      pk = ((IEntityId<?>) object).copy();
      //      PojoUtil.nullSafe(object.getClass(), pk, " ");
      EntityUtil.nullSafe(object.getClass(), pk, EntityUtil.MODE_FIND);
    }
    return em.find(type, pk);
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#close()
   */
  public void close() {
    em.close();
    EntityResource.getInstance().close();
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#createQuery(String sql, QueryFilter filter)
   */
  @SuppressWarnings("unchecked")
  public <E> List<E> createQuery(final String sql, final IFilter filter) {
    final Query query = em.createQuery(sql);
    //Integer queryLimit = 0;
    if (filter != null) {
      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        if ("".equals(f.getValue())) {
          query.setParameter(f.getKey(), " ");
        }
        else {
          query.setParameter(f.getKey(), f.getValue());
        }
      }
      if (filter.getMaxResults() != 0)
        query.setMaxResults(filter.getMaxResults());
      if (filter.getStartPosition() >= 0)
        query.setFirstResult(filter.getStartPosition());
    }

    return query.getResultList();
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#createQuery(String sql, QueryFilter filter)
   */
  @SuppressWarnings("unchecked")
  public <E> List<E> createNativeQuery(final String sql, final IFilter filter, final Class<E> type) {
    Query query = null;
    if (type != null) {
      query = em.createNativeQuery(sql, type);
    }
    else {
      query = em.createNativeQuery(sql);
    }

    //Integer queryLimit = 0;
    if (filter != null) {
      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        if ("".equals(f.getValue())) {
          query.setParameter(f.getKey(), " ");
        }
        else {
          query.setParameter(f.getKey(), f.getValue());
        }
      }
    }
    if (filter != null && filter.getMaxResults() != 0)
      query.setMaxResults(filter.getMaxResults());
    if (filter != null && filter.getStartPosition() >= 0)
      query.setFirstResult(filter.getStartPosition());
    return query.getResultList();
  }

  public Long countNativeQuery(final String sql, final IFilter filter) {
    Long count = 0L;
    Query query = em.createNativeQuery(sql);

    if (filter != null) {
      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        if ("".equals(f.getValue())) {
          query.setParameter(f.getKey(), " ");
        }
        else {
          query.setParameter(f.getKey(), f.getValue());
        }
      }
    }

    List<?> list = (List<?>) query.getSingleResult();

    if (null != list && list.size() > 0) {
      count = ((BigDecimal) list.get(0)).longValue();
    }

    return count;
  }

  public Long countQuery(final String sql, final IFilter filter) {
    Query query = em.createQuery(sql);

    if (filter != null) {
      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        if ("".equals(f.getValue())) {
          query.setParameter(f.getKey(), " ");
        }
        else {
          query.setParameter(f.getKey(), f.getValue());
        }
      }
    }

    return (Long) query.getSingleResult();
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#updateQuery(String sql, QueryFilter filter)
   */
  public Integer updateQuery(final String sql, final IFilter filter) {
    final Query query = em.createQuery(sql);
    if (filter != null) {

      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        query.setParameter(f.getKey(), f.getValue());
      }
    }
    return query.executeUpdate();
  }

  /**
   * To remove an object or a single row from the database. 
   * Use this method when the object needs to be deleted from the remote client,
   * or the object is not known yet to the context of EntityManager.
   * 
   * Note: use this sparingly because it is an expensive operation, 
   * use batch delete instead using native query.
   * 
   * @param type   object type to be removed.
   * @param object primary key of the object to be removed.
   */
  public void remove(final Class<?> type, final Serializable object) {
    final Object o = find(type, object);
    if (o != null) {
      this.remove(o);
    }
    else {
      if (log.isDebugEnabled()) {
        log.warn("Row deletion doesn't exist for:" + object);
      }
    }
  }

  /**
   * Create a query using EJBQL syntax. 
   * Normally use for simple to moderate sql's.
   * 
   * @param sql An EJBQL syntax query. 
   * @return <code>javax.persistence.Query</code>
   */
  public Query createQuery(final String sql) {
    return em.createQuery(sql);
  }

  /**
   * Create a native query using ANSI SQL96 or later syntax.
   * Normally use for complex query or queries that is specific to RDBMS.
   * 
   * @param sql A native SQL syntax.
   * @return <code>javax.persistence.Query</code>
   */
  public Query createNativeQuery(final String sql) {
    return em.createNativeQuery(sql);
  }

  /**
   * Create a native query using ANSI SQL96 or later syntax.
   * Normally use for complex query or queries that is specific to RDBMS.
   * 
   * @param sql A native SQL syntax.
   * @param type Object type to be return.
   * @return <code>javax.persistence.Query</code>
   */
  public Query createNativeQuery(final String sql, final Class<?> type) {
    return em.createNativeQuery(sql, type);
  }

  /**
   * Clear the persistence context, 
   * causing all managed entities to become detached. 
   * Changes made to entities that have not been flushed 
   * to the database will not be persisted. 
   */
  public void clear() {
    em.clear();
  }

  private Object persistOrMerge(final Object object, final IExceptionBag bag) {
    //this is a hack meant for remotepersistor, because the server doesn't know 
    //if the object was queried by the client.
    //XXX needs to be converted to be container-managed
    Object o = null;
    try {
      final ICallback callback = (ICallback) object;
      if (callback.getDateLoaded() == null && callback.getDatePersisted() == null) {
        persist(object);
        o = object;
      }
      else {
        o = merge(object);
        flush();
      }
    }
    catch (final EntityExistsException e) {
      log.debug("entity exist:" + object);
      if (isActive()) {
        rollback();
        clear();
        begin();
      }
      o = merge(object);
      flush();
    }
    return o;
  }

  /**
   * Must be used on server side code and newly instantiated objects, 
   * or the row is not existing yet in the database. Otherwise, use merge.
   * 
   * @param object The object to be persisted.
   */
  public void persist(final Object object) {
    //((IEntity<?>)object).preInit();
    em.persist(object);
    //callbackList.add(object);
    if (log.isTraceEnabled())
      log.trace("persist:" + object);
  }

  /**
   * Must be used on server side code and objects that the persistor operated on previously.
   * In other words, the object is already existing on EntityManager context (see EntityManager of JPA). 
   * 
   * This method is faster than remove(class, pk).
   * 
   * @param object The object to be removed.
   */
  //expose to the world? Yes!
  public void remove(final Object object) {
    em.remove(object);
    if (log.isTraceEnabled())
      log.trace("remove:" + object);
  }

  public boolean isActive() {
    return em.getTransaction().isActive();
  }

  public void begin() {
    em.getTransaction().begin();
    if (log.isTraceEnabled())
      log.trace("begin transaction");
  }

  public void commit() {
    try {
      prePersistUpdate();
      em.getTransaction().commit();
    }
    //    finally {
    //      try {
    //        postPersistUpdate();
    //      }
    //      finally {
    //        callbackList.clear();
    //        em.clear();
    //      }
    //    }
    finally {
      try {
        em.clear();
      }
      finally {
        try {
          postPersistUpdate();
        }
        finally {
          callbackList.clear();
        }
      }
    }
    if (log.isTraceEnabled())
      log.trace("end transaction");
  }

  public void rollback() {
    try {
      em.getTransaction().rollback();
      postPersistUpdate();
    }
    finally {
      callbackList.clear();
    }
    if (log.isTraceEnabled())
      log.trace("rollback transaction");
  }

  /**
   * This method will merge a detached object to the persistence context.
   * E.g. Updating an existing row in the database.
   * 
   * @param object the detached object to merge.
   * @return return a managed object in this persistence context.
   */
  /*public Object merge(final Object object) {
    ((IEntity<?>) object).preInit();
    final IEntity<?> entity = (IEntity<?>) em.merge(object);
    callbackList.add(object);
    callbackList.add(entity);
    entity.initMetadata(object);
    if (log.isTraceEnabled())
      log.trace("merge:" + object);
    return entity;
  }
  */
  public Object merge(final Object object) {
    if (log.isTraceEnabled())
      log.trace("merge:" + object);
    return em.merge(object);
  }

  /**
   * To lock an entity or row in the database.
   *  
   * Note: Always use LockModeType.WRITE to optimistically lock a database row.
   * 
   * @param object to be lock.
   * @param lockModeType lock the entity on READ or WRITE.
   */
  public void lock(final Object object, final LockModeType lockModeType) {
    //lock the object if it is enhanced to have IOptimisticLock interface 
    if (object instanceof IOptimisticLock) {
      em.lock(object, lockModeType);
    }
    else {
      log.warn("IOptimisticLock is not implemented for " + object.getClass());
    }
  }

  protected EntityManager getEntityManager() {
    return em;
  }

  /**
   * The flush method writes any changes that have been made in the current transaction to the database.
   * The flushed transaction will not be visible to other connection until committed. It requires an active transaction.  
   *  
   * Note: this is useful when calling StrongHold or parallel transaction to the same connection
   *       or persistence context e.g. calling StrongHold program. 
   */
  public void flush() {
    if (log.isTraceEnabled())
      log.trace("flush");
    prePersistUpdate();
    em.flush();
  }

  /**
   * Refresh the managed entity on this persistence context.
   * 
   * @param entity the managed entity to refresh.
   */
  public void refresh(Object entity) {
    em.refresh(entity);
  }

  /**
   * Check if the instance of managed entity belongs to the current persistence context.
   * 
   * @param the entity to check.
   */
  public boolean contains(Object entity) {
    return em.contains(entity);
  }

  /**
   * Create a named query or get the query from annotated entity.
   * 
   * @param name name of the query.
   * @return <code>javax.persistence.Query</code>
   */
  public Query createNamedQuery(String name) {
    return em.createNamedQuery(name);
  }

  /* (non-Javadoc)
   * @see com.kewill.dev.dbi.IPersistor#createNamedQuery(String name, QueryFilter filter)
   */
  @SuppressWarnings("unchecked")
  public <E> List<E> createNamedQuery(final String name, final IFilter filter) {
    final Query query = em.createNamedQuery(name);
    //Integer queryLimit = 0;
    if (filter != null) {
      for (final Map.Entry<String, Object> f : filter.getParameterSet()) {
        if ("".equals(f.getValue())) {
          query.setParameter(f.getKey(), " ");
        }
        else {
          query.setParameter(f.getKey(), f.getValue());
        }
      }

      if (filter.getMaxResults() != 0)
        query.setMaxResults(filter.getMaxResults());
    }
    return query.getResultList();
  }

  private void prePersistUpdate() {
    for (Object o : callbackList) {
      IEntity<?> entity = (IEntity<?>) o;
      entity.preInit();
    }
  }

  private void postPersistUpdate() {
    for (Object o : callbackList) {
      IEntity<?> entity = (IEntity<?>) o;
      entity.postInit();
    }
  }
}
