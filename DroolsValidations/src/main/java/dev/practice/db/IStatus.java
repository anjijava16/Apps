package dev.practice.db;

import java.io.Serializable;

/**
 * 
 * @author Hills.Reddy
 */
public interface IStatus extends Serializable {

  public final static int INFO    = 0;
  public final static int ERROR   = 1;
  public final static int WARNING = 2;

  int getType();

  String getCode();

  String getMessage();

  String getSource();

}
