package org.visual.debugger.component;

import com.sun.tools.attach.VirtualMachineDescriptor;
import javafx.scene.control.ListView;

public class VirtualMachineDescriptorListView extends ListView<VirtualMachineDescriptor> {

  public VirtualMachineDescriptorListView() {
    setCellFactory(new VirtualMachineDescriptorCellFactory());
  }
}
