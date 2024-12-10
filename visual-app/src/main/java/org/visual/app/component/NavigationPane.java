package org.visual.app.component;

import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.MutableStack;

@Slf4j
@SuppressWarnings("unused")
@Getter
public class NavigationPane extends StackPane {

  private final ObservableMap<String, Node> page = FXCollections.observableMap(Object2ObjectMaps.emptyMap());

  private final StringProperty currentPage = new SimpleStringProperty();

  private final MutableStack<Node> backStack = Stacks.mutable.empty();

  private final MutableStack<Node> forwardStack = Stacks.mutable.empty();

  private final StringProperty index = new SimpleStringProperty("/");

  {
    page.addListener((MapChangeListener<String, Node>) change -> {
      log.atInfo().log("change{}", change);
    });
    index.addListener((observable, oldValue, newValue) -> {
      log.atInfo().log(oldValue);
    });
  }

  public NavigationPane() {
    super();
    setup();
  }

  public NavigationPane(Node... children) {
    super(children);
    setup();
  }

  private void setup() {
    val children = getChildren();
    if (CollectionUtils.isNotEmpty(children)) {
      page.put(index.get(), children.getFirst());
    }
  }

  public void to(String route) {

  }

  public void back() {

  }

  public void forward() {

  }

  public void setIndex(String index) {
    this.index.set(index);
  }

  public String getIndex() {
    return this.index.get();
  }
}
