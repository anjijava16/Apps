package dev.practice.db;

import java.io.Serializable;
import java.util.Map;

/**
*
* @author Hills.Reddy
*/
public interface IExceptionBag extends Serializable {

  public static final String ID = "EXCEPTION_BAG";

  boolean hasErrors();

  boolean hasWarnings();

  Map<String, IStatus> getErrors();

  Map<String, IStatus> getWarnings();

  void add(IStatus status);

  void remove(IStatus status);

  /**
   * Add errors and warnings from source ExceptionBag.
   * @param source the ExceptionBag to add.
   */
  void addExceptionBag(IExceptionBag source);

  /**
   * Clear all errors and warnings.
   */
  void clearAll();
}
