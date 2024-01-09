package org.visual.model.ui.bar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import lombok.val;

public class SystemMenuBar extends MenuBar {

    {
        this.setUseSystemMenuBar(true);
        val menu = new Menu("test");
        val it = new MenuItem();
        it.setText("test");
        menu.getItems().add(it);
        getMenus().add(menu);
    }
}
