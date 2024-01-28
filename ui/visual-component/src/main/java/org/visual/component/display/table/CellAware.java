package org.visual.component.display.table;

public interface CellAware<S> {
  void setCell(VTableColumn<S, ?> col, VTableCellPane<S> pane);
}
