package dev.practice.db.connection;

import java.io.Serializable;
import java.util.List;

import dev.practice.db.IExceptionBag;
import dev.practice.db.IFilter;

/**
 *
 * @author KondalReddy
 */
public interface IPersistor {

  /**
   * To persist the object to the database and run the validations.
   * 
   * @param object      The object to be persisted in the database.
   * @param validators  The validations to run.
   * @param bag         The Exception place holder for errors and warnings.
   * @return            Returns persisted object with updated metadata E.g. Datetime persisted.
   */
  public abstract Object persist(Serializable object, IExceptionBag bag);

  //  /**
  //   * To run the validations without persisting the object.
  //   * 
  //   * @param object      The object to be persisted in the database.
  //   * @param validators  The validations to run.
  //   * @param bag         The Exception place holder for errors and warnings.
  //   * @return            Returns exception place holder for errors and warnings.
  //   */
  //  public abstract IExceptionBag validate(Serializable object, IExceptionBag bag);

  /**
   * To return a single inited object or row from the database.  
   * 
   * @param <T>    The object type to return.
   * @param type   The class type to find. 
   * @param object The primary key to use for database query.
   * @return       An inited object type <T>.
   */
  public abstract <T> T find(Class<T> type, Serializable object);

  /**
   * To run a native read only query to the database.
   * Note: The method is intended to run on Client and Server side program.
   * If the program is intended for Server side only, use 
   * <code>createNativeQuery(String)</code> instead. 
   * 
   * @param nativeSql    the query to be run.
   * @param filter       where condition of the sql.
   * @param type         the return type of List element, if null, 
   *                     the return type will be based on column type.
   * @return             lists of records from the database.
   */
  public abstract <T> List<T> createNativeQuery(String nativeSql, IFilter filter, Class<T> type);

  /**
   * To run a read only query (select) to the database.
   * 
   * @param sql    the query to be run.
   * @param filter where condition of the sql.
   * @return lists of records from the database.
   */
  public abstract <T> List<T> createQuery(String sql, IFilter filter);

  /**
   * To run a read only JPQL query that will return the number of rows.
   * 
   * @param sql     the query counter to be run e.g. select count(t) from Country t 
   * @param filter  where condition of the sql, otherwise supply null if no filter is needed.
   * @return        the number of rows. 
   */
  public abstract Long countQuery(final String sql, final IFilter filter);

  /**
   * To run a read only Native query that will return the number of rows.
   * 
   * @param sql     the query counter to be run e.g. select count(*) from country 
   * @param filter  where condition of the sql, otherwise supply null if no filter is needed.
   * @return        the number of rows. 
   */
  public abstract Long countNativeQuery(final String sql, final IFilter filter);

  /**
   * To run a read only named query (select) to the database.
   * 
   * @param sql    the name of the query from annotated entity.
   * @param filter where condition of the sql.
   * @return lists of records from the database.
   */
  public abstract <T> List<T> createNamedQuery(String name, IFilter filter);

  /**
   * To run a DML query to the database.
   * 
   * @param sql the DML query to be run.
   * @param filter where condition of the sql.  
   * @return Returns the number of records affected.
   */
  public abstract Integer updateQuery(String sql, IFilter filter);

  /**
   * To remove an object from the database.
   * Note: use this sparingly because it is an expensive operation, 
   * use batch delete instead using native query.
   * 
   * @param type   object type to be removed.
   * @param object primary key of the object to be removed.
   */
  public abstract void remove(Class<?> type, Serializable object);

  /**
   * To close the persistor or entity manager.
   */
  public abstract void close();

  /**
   * To begin the transaction.
   */
  public abstract void begin();

  /**
   * To rollback the initiated transaction.
   */
  public abstract void rollback();

  /**
   * To commit the initiated transaction to database.
   */
  public abstract void commit();

  /**
   * Method to check if the transaction is active.
   * 
   * @return true if the transaction is active.
   */
  public abstract boolean isActive();

  /**
   * Clear the persistence context, 
   * causing all managed entities to become detached. 
   * Changes made to entities that have not been flushed 
   * to the database will not be persisted. 
   */
  public abstract void clear();

}