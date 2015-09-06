/**
 * © 2008 by TradePoint Systems LLC
 */
package dev.practice.db;

/**
 * Interface marker for optimistic locking.
 * 
 * @author Hills.Reddy
 *
 */
public interface IOptimisticLock {
  int getVersion();

  void setVersion(int version);
}
