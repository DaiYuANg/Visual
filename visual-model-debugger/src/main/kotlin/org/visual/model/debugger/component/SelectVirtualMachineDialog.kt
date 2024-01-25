package org.visual.model.debugger.component

import com.sun.tools.attach.VirtualMachine
import com.sun.tools.attach.VirtualMachineDescriptor
import javafx.geometry.Insets
import javafx.scene.control.Dialog
import javafx.scene.control.ListView
import javafx.stage.Modality
import org.visual.model.component.util.ScreenUtil
import java.util.function.Consumer

class SelectVirtualMachineDialog(
) : Dialog<VirtualMachineDescriptor>() {

    private val virtualMachineList by lazy {
        ListView<VirtualMachineDescriptor>().apply {
            cellFactory = VirtualMachineDescriptorCellFactory()
            setOnMouseClicked {
                result = this.selectionModel.selectedItem
            }
        }
    }

    init {
//        initModality(Modality.WINDOW_MODAL)
        dialogPane.padding = Insets(0.0, 0.0, 0.0, 0.0)
        isResizable = true
        ScreenUtil.percentOfScreen(0.4).let {
            dialogPane.prefWidth = it.left
            dialogPane.prefHeight = it.right
        }
        setOnShowing {
            virtualMachineList.items.addAll(VirtualMachine.list())
            dialogPane.content = virtualMachineList
        }
    }
}

