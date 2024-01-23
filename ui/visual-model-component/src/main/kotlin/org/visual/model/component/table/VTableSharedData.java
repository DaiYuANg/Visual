package org.visual.model.component.table;

public class VTableSharedData<S> {
    public final VTableView<S> tableView;
    public long rowAdder = 0;

    public VTableSharedData(VTableView<S> tableView) {
        this.tableView = tableView;
    }
}
