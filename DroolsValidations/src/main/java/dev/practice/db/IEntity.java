package dev.practice.db;

/**
 * All entities implement this interface.
 * 
 * @author Hills.Reddy
 *
 */
public interface IEntity<P> {

  /**
   * An indicator if the Entity has been change or not
   * 
   * @return True, if the Entity has been change otherwise false. 
   */
  Boolean isChanged();

  /**
   * An indicator if the primary key of an Entity has been change or not
   * 
   * @return True, if the primary of an Entity has been change otherwise false. 
   */
  Boolean isIdChanged();

  /**
   * The method will the original key queried from database. 
   * Otherwise, returns null if the object is newly inited.
   * 
   * This is immutable primary key or not intended to be updated.
   * 
   * @return Original primary key. 
   */
  Object getOriginalId();

  /**
   * Mutable primary key to provide a backward compatibility for Alliance.
   * 
   * @return primary key. 
   */
  P getId();

  /**
   * Sets the primary key of this entity.
   * 
   * @param id the primary key for this entity.
   */
  void setId(P id);

  /**
   * A clone implementation without the burden of checked exception and casting.
   *
   * @return A clone of this entity.
   */
  IEntity<P> copy();

  /**
   * Transfer the metadata of this entity to another entity
   * 
   * @param entity to transfer the metadata flags.
   */
  void initMetadata(Object entity);

  void preInit();

  void postInit();

  void postLoadInit();

}
