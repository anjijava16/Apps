package dev.practice.db;

/**
 * Entity primary key.
 * 
 * @author Hills.Reddy
 *
 */
public interface IEntityId<P> {

  /**
   * An indicator if the Entity has been change or not
   * 
   * @return True, if the Entity has been change otherwise false. 
   */
  Boolean isChanged();

  //void initChanged(Boolean changed);

  P copy();

}
