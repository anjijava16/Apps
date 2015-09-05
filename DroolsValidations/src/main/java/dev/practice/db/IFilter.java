package dev.practice.db;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
*
* @author HillsReddy
*/
public interface IFilter extends Serializable {

  //XXX: quick fix for popup.
  //public static final String  POPUP_LIMIT_KEY        = "dev.popup.limit";
  public static final Integer POPUP_LIMIT_VALUE = 200;

  public abstract IFilter setParameter(String var, Object val);

  public abstract IFilter setParameter(String var, Object val, String operation);

  public abstract void remove(String var);

  public abstract void clear();

  public abstract Set<Map.Entry<String, Object>> getParameterSet();

  public abstract String getCondition(String var);

  //permanent solution for limiting the return rows to client.
  public abstract int getMaxResults();

  public abstract void setMaxResults(int maxResults);

  public abstract void setStartPosition(int startPosition);

  public abstract int getStartPosition();
}