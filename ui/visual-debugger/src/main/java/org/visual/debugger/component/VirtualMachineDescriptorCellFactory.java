package org.visual.debugger.component;

import com.sun.tools.attach.VirtualMachineDescriptor;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.simpleicons.SimpleIcons;

public class VirtualMachineDescriptorCellFactory
    implements Callback<ListView<VirtualMachineDescriptor>, ListCell<VirtualMachineDescriptor>> {

  private final int maxTextLength = 70;

  @Override
  public ListCell<VirtualMachineDescriptor> call(ListView<VirtualMachineDescriptor> param) {
    return new ListCell<VirtualMachineDescriptor>() {
      @Override
      protected void updateItem(VirtualMachineDescriptor virtualMachineDescriptor, boolean empty) {
        createCell(empty, virtualMachineDescriptor);
        widthProperty()
            .addListener(
                (obs, oldValue, newValue) -> {
                  if (getGraphic() != null) {
                    setPrefWidth(newValue.doubleValue());
                  }
                });
      }
    };
  }

  private void createCell(boolean empty, VirtualMachineDescriptor virtualMachineDescriptor) {
    if (!empty && virtualMachineDescriptor != null) {
      //      setGraphic(buildItem(virtualMachineDescriptor.displayName(),
      // virtualMachineDescriptor.id()));
      //      setTooltip(new Tooltip(virtualMachineDescriptor.displayName()) {{
      //        setShowDelay(Duration.millis(100.0));
      //      }});
    }
  }

  private VBox buildItem(String labelText, String id) {
    VBox root = new VBox();
    root.setAlignment(Pos.CENTER);

    HBox box = new HBox();
    Label label = new Label(labelText);

    StackPane stackPane = new StackPane();
    Label idLabel = new Label(id);
    StackPane.setAlignment(idLabel, Pos.CENTER_RIGHT);
    stackPane.getChildren().add(idLabel);
    HBox.setHgrow(stackPane, Priority.ALWAYS);

    FontIcon font = new FontIcon(SimpleIcons.JAVA);
    box.getChildren().addAll(font, label, stackPane);

    root.getChildren().add(box);
    return root;
  }
}
