package org.visual.app.context;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Singleton
@Slf4j
public class TabContext {

  private final List<String> tabs = new ObjectArrayList<String>();

  public void add() {
  }
}
