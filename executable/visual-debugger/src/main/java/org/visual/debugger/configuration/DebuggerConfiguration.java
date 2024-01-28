package org.visual.debugger.configuration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DebuggerConfiguration {
  private boolean autoRefreshStyleSheets;

  private boolean automaticScenegraphStructureRefreshing = true;
  private boolean collapseContainerControls = false;
  private boolean collapseControls = true;
  private boolean ignoreMouseTransparentNodes = true;
  private boolean registerShortcuts = true;
  private boolean showBaseline = false;
  private boolean showBounds = true;
  private boolean showCSSProperties = false;
  private boolean showDefaultProperties = true;
  private boolean showFilteredNodesInTree = true;
  private boolean showInvisibleNodes = false;
  private boolean showNodesIdInTree = false;
  private boolean showSearchBar = true;
  private double splitPaneDividerPosition = 0.3692602040816326;
  private double stageHeight = 800.0;
  private double stageWidth = 800.0;
}
