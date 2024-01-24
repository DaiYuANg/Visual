package org.visual.model.debugger.component

import com.sun.tools.attach.VirtualMachineDescriptor
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.Tooltip
import javafx.scene.layout.HBox
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.util.Callback
import javafx.util.Duration
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.simpleicons.SimpleIcons

class VirtualMachineDescriptorCellFactory :
    Callback<ListView<VirtualMachineDescriptor>, ListCell<VirtualMachineDescriptor>> {
    private val maxTextLength = 70

    override fun call(param: ListView<VirtualMachineDescriptor>?): ListCell<VirtualMachineDescriptor> {
        return object : ListCell<VirtualMachineDescriptor>() {
            override fun updateItem(virtualMachineDescriptor: VirtualMachineDescriptor?, empty: Boolean) {
                super.updateItem(virtualMachineDescriptor, empty)
                createCell(empty, virtualMachineDescriptor)
            }
        }
    }

    private fun ListCell<VirtualMachineDescriptor>.createCell(
        empty: Boolean,
        virtualMachineDescriptor: VirtualMachineDescriptor?
    ) {
//        setOnMouseClicked {
//            System.err.println(item)
//        }
        if (!empty && virtualMachineDescriptor != null) {
            graphic = buildItem(virtualMachineDescriptor.displayName(), virtualMachineDescriptor.id())
            tooltip = Tooltip(virtualMachineDescriptor.displayName()).apply {
                showDelay = Duration(100.0)
            }
        }
    }

    private fun buildItem(labelText: String, id: String): VBox {
        val text =
            if (labelText.length > maxTextLength) labelText.substring(0, maxTextLength - 1) + "..." else labelText
        val root = VBox()
        root.alignment = Pos.CENTER
        val box = HBox()
        val label = Label(text)
        val stackPane = StackPane()
        val idLabel = Label(id)
        StackPane.setAlignment(idLabel, Pos.CENTER_RIGHT)
        stackPane.children.add(idLabel)
        setHgrow(stackPane, Priority.ALWAYS)
        val font = FontIcon(SimpleIcons.JAVA)
        box.children.addAll(font, label, stackPane)
        root.children.add(box)
        return root
    }
}