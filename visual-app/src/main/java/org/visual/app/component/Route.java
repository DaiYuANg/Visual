package org.visual.app.component;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import lombok.EqualsAndHashCode;

@DefaultProperty("content")
@EqualsAndHashCode
public class Route {
  private final StringProperty path = new SimpleStringProperty();

  private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

  public String getPath() {
    return path.get();
  }

  public void setPath(String path) {
    this.path.set(path);
  }

  public Node getContent() {
    return content.get();
  }

  public void setContent(Node content) {
    this.content.set(content);
  }
}
