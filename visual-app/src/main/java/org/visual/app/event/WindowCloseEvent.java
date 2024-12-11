package org.visual.app.event;


import javafx.event.Event;
import javafx.event.EventType;

public class WindowCloseEvent extends Event {
  public static final EventType<WindowCloseEvent> CLOSE = new EventType<>(ANY, "CLOSE");

  public WindowCloseEvent() {
    super(CLOSE);
  }
}