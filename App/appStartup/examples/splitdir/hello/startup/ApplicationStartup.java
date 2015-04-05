package examples.splitdir.hello.startup;

import examples.splitdir.hello.apputils.AppUtils;
import weblogic.application.ApplicationLifecycleEvent;
import weblogic.application.ApplicationLifecycleListener;
import weblogic.utils.Debug;

/**
 * This is an example of a ApplicationLifecycleListener, which 
 * reacts to application lifecycle events and is an app scoped
 * replacement for startup classes
 *
 * @see weblogic.j2ee.AppLifecycleListenerInternal
 *
 * @author Copyright (c) 2011 by BEA Systems, Inc. All Rights Reserved.
 */

public class ApplicationStartup extends ApplicationLifecycleListener {

  private boolean debug = true;
  
  /**
   * This is an application lifecycle events that is fired
   * just before the start() method of an Application, which 
   * corresponds to a deploy or full redeploy
   *
   * @see weblogic.j2ee.AppLifecycleListenerInternal#preStart
   *
   */
  public void preStart(ApplicationLifecycleEvent evt) {
    if(debug) {
      Debug.say("preStart of ApplicationLifecycleListener ApplicationStartup");
    }
    AppUtils.initAppUtils();
  }
}
