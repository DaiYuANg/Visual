package org.visual.app.component;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.MutableStack;

import static javafx.collections.FXCollections.observableArrayList;

@Slf4j
@SuppressWarnings("unused")
@Getter
public class NavigationPane extends VBox {

  private final ObservableList<Route> routes = observableArrayList(new ObjectArrayList<>());

  private final StringProperty currentPage = new SimpleStringProperty();

  private final MutableStack<Node> backStack = Stacks.mutable.empty();

  private final MutableStack<Node> forwardStack = Stacks.mutable.empty();

  private final StringProperty index = new SimpleStringProperty("/");

  private final StringProperty current = new SimpleStringProperty();

  private final OnlyOneStackPane stackPane = new OnlyOneStackPane();

  private final NavigationBar navigationBar = new NavigationBar();

  {
    routes.addListener((ListChangeListener<Route>) c -> {
      log.atInfo().log("c:{}", c);
      log.atInfo().log("Routes:{}", routes);
      stackPane.getChildren().clear();
      c.getList().stream()
        .filter(route -> route.getPath().equals(index.get()))
        .findFirst()
        .ifPresent(route -> stackPane.setContent(route.getContent()));
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
    navigationBar.setOnBack(event -> back());
    navigationBar.setOnForward(event -> forward());
    this.getChildren().addAll(navigationBar, new Separator(), stackPane);
  }

  public void to(String to) {
    routes.stream()
      .filter(route -> route.getPath().equals(to))
      .findFirst()
      .ifPresent(route -> {
        backStack.push(stackPane.getContent());
        forwardStack.clear();
        stackPane.setContent(route.getContent());
      });
  }

  public void back() {
    if (backStack.isEmpty()) {
      return;
    }
    forwardStack.push(stackPane.getContent());
    val lastContent = backStack.pop();
    stackPane.setContent(lastContent);
  }

  public void forward() {
    if (forwardStack.isEmpty()) {
      return;
    }
    backStack.push(stackPane.getContent());
    val nextContent = forwardStack.pop();
    stackPane.setContent(nextContent);
  }

  public void setIndex(String index) {
    this.index.set(index);
  }

  public String getIndex() {
    return this.index.get();
  }

  public void setCurrent(String currentPage) {
    current.set(currentPage);
  }

  public String getCurrent() {
    return current.get();
  }

  public void addRoute(Route route) {
    routes.add(route);
  }
}
