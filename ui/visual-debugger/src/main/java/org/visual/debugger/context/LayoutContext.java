package org.visual.debugger.context;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

public enum LayoutContext {
  INSTANCE;
  private static final SimpleBooleanProperty collapseSplitPane = new SimpleBooleanProperty(false);

  public static boolean isCollapseSplitPane() {
    return collapseSplitPane.get();
  }

  public static void setCollapseSplitPane(boolean value) {
    collapseSplitPane.set(value);
  }

  public static void toggleCollapse() {
    collapseSplitPane.set(!collapseSplitPane.get());
  }

  public static void addCollapseListener(ChangeListener<Boolean> listener) {
    collapseSplitPane.addListener(listener);
  }
}
