package org.visual.app.lifecycle;

public interface ViewLifecycle {

  /**
   * Calling on {@link javafx.application.Application#init}
   */
  void onInit();

  default void onStop(){};
}
