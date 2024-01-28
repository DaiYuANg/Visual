package org.visual.debugger.listener;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.api.StageController;
import org.visual.debugger.node.SVNode;
import org.visual.debugger.view.ScenicViewGui;

@Slf4j
@Builder
public class StageCollapsingListener implements EventHandler<TreeItem.TreeModificationEvent<Object>> {
    private final TreeItem<SVNode> root;
    private final StageController controller;

    private final ScenicViewGui scenicViewGui;

//    public StageCollapsingListener(final TreeItem<SVNode> root, final StageController controller) {
//        this.root = root;
//        this.controller = controller;
//    }

    @Override
    public void handle(final TreeItem.TreeModificationEvent<Object> ev) {
        if (!root.isExpanded() && controller.isOpened()) {
            // Closing controller
            controller.close();
        } else if (root.isExpanded() && !controller.isOpened()) {
            // Opening controller
            scenicViewGui.openStage(controller);
        }
    }
}
