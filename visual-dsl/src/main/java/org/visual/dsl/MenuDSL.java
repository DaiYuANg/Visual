package org.visual.dsl;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class MenuDSL {
  public Menu create(String name, @NotNull Consumer<MenuBuilder> menuBuilderConsumer) {
    val menuBuilder = new MenuBuilder(name);
    menuBuilderConsumer.accept(menuBuilder);
    return menuBuilder.build();
  }

  public class MenuBuilder {
    private final Menu menu;

    private final Set<MenuItem> items = new HashSet<>();

    public MenuBuilder(String name) {
      this.menu = new Menu(name);
    }

    public MenuBuilder setTitle(String title) {
      menu.setText(title);
      return this;
    }

    public MenuBuilder addItem(String title) {
      val item = new MenuItem(title);
      items.add(item);
      return this;
    }

    public MenuBuilder addItem(String title, Node icon) {
      val item = new MenuItem(title, icon);
      items.add(item);
      return this;
    }

    public MenuBuilder addItem(String title, Node icon, EventHandler<ActionEvent> eventHandler) {
      val item = new MenuItem(title, icon);
      item.setOnAction(eventHandler);
      items.add(item);
      return this;
    }

    public MenuBuilder addItem(String title, EventHandler<ActionEvent> action) {
      val item = new MenuItem(title);
      item.setOnAction(action);
      items.add(item);
      return this;
    }

    private Menu build() {
      menu.getItems().addAll(items);
      return menu;
    }
  }
}
