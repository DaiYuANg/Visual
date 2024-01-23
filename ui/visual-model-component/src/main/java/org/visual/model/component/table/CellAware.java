package org.visual.model.component.table;

public interface CellAware<S> {
    void setCell(VTableColumn<S, ?> col, VTableCellPane<S> pane);
}
