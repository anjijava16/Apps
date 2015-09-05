package dev.practice.db.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * IO Helper.
 * 
 * @author: HillsReddy
 */

public class IOHelper {

  private static final Log log = LogFactory.getLog(IOHelper.class);

  public static void close(Reader reader) {
    try {
      if (reader != null)
        reader.close();
    }
    catch (IOException e) {
      log.error("Could not close the reader.", e);
    }
  }

  public static void close(InputStream stream) {
    try {
      if (stream != null)
        stream.close();
    }
    catch (IOException e) {
      log.error("Could not close the input stream.", e);
    }
  }

  public static void close(OutputStream stream) {
    try {
      if (stream != null)
        stream.close();
    }
    catch (IOException e) {
      log.error("Could not close the output stream.", e);
    }
  }

  public static void close(Writer writer) {
    try {
      if (writer != null)
        writer.close();
    }
    catch (IOException e) {
      log.error("Could not close the output stream.", e);
    }
  }

  public static void close(IPersistor persistor) {
    if (persistor != null) {
      persistor.clear();
      persistor.close();
    }
  }

  /*
  public static void close(VirtualFile file) {
    try {
      if (file != null)
        file.close();
    }
    catch (IOException e) {
      log.error("Could not close the virtual file.",e);
    }
  }*/

  /**
   * Server side rollback
   * 
   * @param persistor
   */
  public static void rollback(IPersistor persistor) {
    //nick: having the isActive check here is a bad idea
    //because we are not catching potential problem when 
    //transaction marker is not present.
    if (persistor != null /*&& persistor.isActive()*/)
      persistor.rollback();
  }

}
