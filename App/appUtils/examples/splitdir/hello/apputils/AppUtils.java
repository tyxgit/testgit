package examples.splitdir.hello.apputils;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;
import examples.common.thirdparty.GenericResourceLoader;
import weblogic.utils.Debug;


/**
 * AppUtils is a utility class that can be used by all modules in the ear
 *
 * @author Copyright (c) 2011, Oracle and/or its affiliates. All Rights Reserved.
 */
public class AppUtils {
  
  private final static String APPLICATION_PROPERTIES_NAME = "applicationresource.properties";
  private final static String EJB_JNDI_NAME_PROPERTIES_NAME = "hello.ejb.jndi.name";
  
  private static Properties appProperties;
  private static boolean debug = true;

  private static AppUtils appUtils = null;

  /* static method to get AppUtils */
  public static AppUtils getAppUtils() {
    if (appUtils == null) {
      throw new AssertionError("getAppUtils() called prior to appUtil's being initialized");
    }
    return appUtils;
  }
  
  /* protect our constructor */
  private AppUtils() {
    try {
      if (debug) {
        Debug.say("loading appProperties");
      }
      appProperties = GenericResourceLoader.getProperties("/"+APPLICATION_PROPERTIES_NAME);
      if (debug) {
        Debug.say("appProperties loaded : " + appProperties);
      }
    } catch (IOException ioe) {
      throw new IllegalStateException(throwable2StackTrace(ioe));
    } 
  }
    
  /**
   * Typically the property related methods in this class they would be static's
   * however to demonstrate a common pattern where a set of resources
   * need to be loaded at application deployment, and reloaded when an app
   * is redeployed we use this approach.  This method will be called by
   * our applicaiton lifecycle listener, no one else should call this.
   */
  public static void initAppUtils() {
    if (debug) {
      Debug.say("AppUtils initAppUtils");
    }
    appUtils = new AppUtils();
  }
  
  public Properties getProps() {
    return appProperties;
  }
  
  public String getProperty(String propertyName) {
    return appProperties.getProperty(propertyName);
  } 

  public String getEJBJNDIName() {
    return getProperty(EJB_JNDI_NAME_PROPERTIES_NAME);
  }

  public static String throwable2StackTrace(Throwable th) {
    if(th == null) th = new Throwable("[Null exception passed, creating stack trace for offending caller]");
    ByteArrayOutputStream ostr = new ByteArrayOutputStream();
    th.printStackTrace(new PrintStream(ostr));
    return ostr.toString();
  }
}
