package org.visual.debugger.component

import com.sun.tools.attach.VirtualMachineDescriptor
import javafx.scene.control.ListCell

class JVMProcessCell : ListCell<VirtualMachineDescriptor>() {
  override fun updateItem(
      p0: VirtualMachineDescriptor?,
      p1: Boolean,
  ) {
    text = p0?.id()
    id = p0?.id()
  }
}
