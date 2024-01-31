package org.visual.debugger.component

import com.sun.tools.attach.VirtualMachineDescriptor
import javafx.scene.control.ListView

class VirtualMachineDescriptorListView : ListView<VirtualMachineDescriptor>() {

  init {
    cellFactory = VirtualMachineDescriptorCellFactory()
  }
}
