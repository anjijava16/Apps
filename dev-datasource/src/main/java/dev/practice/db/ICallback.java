package dev.practice.db;

/**
 * Call back for life cycle of the Entity.
 * 
 * E.g. Loaded from DB, Persisted to DB, and etc.
 * 
 * @author Hills.Reddy
 *
 */
public interface ICallback {

  /**
   *  Returns a long Date (E.g. System.currentTimeMillis()) if the entity is queried from the database.
   * 
   *  @return The date and time when entity queried from the database by the client.
   *          Return null if the pojo is newly inited. 
   */
  public Long getDateLoaded();

  /**
   *  Returns a long Date (E.g. System.currentTimeMillis()) if the entity is persisted to the database.
   *  
   *  @return The date and time when the entity persisted to the database by the client.
   *          Return null if the pojo is not yet persisted by the client. 
   */

  public Long getDatePersisted();

}
