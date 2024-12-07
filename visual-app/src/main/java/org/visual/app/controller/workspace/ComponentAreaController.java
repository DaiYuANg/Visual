package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.controller.BaseController;
import org.visual.app.model.DatabaseObj;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class ComponentAreaController extends BaseController {
  public TreeView treeView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
// 创建根节点
    TreeItem<DatabaseObj> rootItem = new TreeItem<>(new DatabaseObj("Database Objects", "root"));
    rootItem.setExpanded(true); // 默认展开

    // 创建子节点（例如：表格和字段）
    TreeItem<DatabaseObj> tableItem = new TreeItem<>(new DatabaseObj("Users", "table"));
    TreeItem<DatabaseObj> fieldItem1 = new TreeItem<>(new DatabaseObj("ID", "field"));
    TreeItem<DatabaseObj> fieldItem2 = new TreeItem<>(new DatabaseObj("Name", "field"));
    tableItem.getChildren().addAll(fieldItem1, fieldItem2);

    TreeItem<DatabaseObj> viewItem = new TreeItem<>(new DatabaseObj("Orders", "view"));
    TreeItem<DatabaseObj> fieldItem3 = new TreeItem<>(new DatabaseObj("OrderID", "field"));
    TreeItem<DatabaseObj> fieldItem4 = new TreeItem<>(new DatabaseObj("OrderDate", "field"));
    viewItem.getChildren().addAll(fieldItem3, fieldItem4);

    rootItem.getChildren().addAll(tableItem, viewItem);

    // 将 TreeItem 设置给 TreeView
    treeView.setRoot(rootItem);
    treeView.setShowRoot(false);  // 不显示根节点
  }
}
