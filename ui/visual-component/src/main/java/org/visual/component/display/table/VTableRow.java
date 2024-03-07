package org.visual.component.display.table;

import static org.visual.component.util.FxUtil.runOnFx;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class VTableRow<S> implements RowInformer {
  private static final Color COLOR_SELECTED = new Color(0, 0x96 / 255d, 0xc9 / 255d, 1);
  private static final Color COLOR_1 = new Color(0xf9 / 255d, 0xf9 / 255d, 0xf9 / 255d, 1);
  private static final Color COLOR_2 = new Color(0xff / 255d, 0xff / 255d, 0xff / 255d, 1);

  private static final Background BG_SELECTED =
      new Background(new BackgroundFill(COLOR_SELECTED, CornerRadii.EMPTY, Insets.EMPTY));
  private static final Background BG_1 =
      new Background(new BackgroundFill(COLOR_1, CornerRadii.EMPTY, Insets.EMPTY));
  private static final Background BG_2 =
      new Background(new BackgroundFill(COLOR_2, CornerRadii.EMPTY, Insets.EMPTY));

  final long rowId;
  final S item;
  final VTableSharedData<S> shared;
  final ObservableList<VTableCellPane<S>> nodes = FXCollections.observableArrayList();
  @Getter private boolean selected = false;

  VTableRow(S item, @NotNull VTableSharedData<S> shared) {
    this.rowId = ++shared.rowAdder;
    this.item = item;
    this.shared = shared;
    if (item instanceof RowInformerAware) {
      ((RowInformerAware) item).setRowInformer(this);
    }

    nodes.addListener(
        (ListChangeListener<VTableCellPane<S>>)
            c -> {
              while (c.next()) {
                var added = c.getAddedSubList();
                var removed = c.getRemoved();
                for (var r : removed) {
                  for (var n : nodes) {
                    n.heightProperty().removeListener(r.heightWatcher);
                  }
                }
                for (var a : added) {
                  for (var n : nodes) {
                    if (a == n) continue;
                    n.heightProperty().addListener(a.heightWatcher);
                    a.heightProperty().addListener(n.heightWatcher);
                  }
                }
              }
            });
  }

  public void add() {
    var columns = shared.tableView.getColumns();
    IntStream.range(0, columns.size())
        .forEach(
            i -> {
              var col = columns.get(i);
              col.vbox.getChildren().add(nodes.get(i));
            });
  }

  public void add(int index) {
    var columns = shared.tableView.getColumns();
    IntStream.range(0, columns.size())
        .forEach(
            i -> {
              var col = columns.get(i);
              col.vbox.getChildren().add(index, nodes.get(i));
            });
  }

  public void remove() {
    var columns = shared.tableView.getColumns();
    IntStream.range(0, columns.size())
        .forEach(
            i -> {
              var col = columns.get(i);
              col.vbox.getChildren().remove(nodes.get(i));
            });
  }

  public void removeCol(int index) {
    nodes.remove(index);
  }

  public void addCol(int index, VTableColumn<S, ?> col) {
    val cell = new VTableCellPane<>(buildNode(col), this, shared);
    nodes.add(index, cell);
    if (item instanceof CellAware) {
      //noinspection unchecked,rawtypes
      ((CellAware) item).setCell(col, cell);
    }
  }

  public void setCols(@NotNull List<VTableColumn<S, ?>> cols) {
    nodes.clear();
    cols.stream().map(this::buildCell).forEach(nodes::add);
  }

  public void updateRowNodeForColumn(VTableColumn<S, ?> col) {
    int rowIndex = shared.tableView.items.indexOf(this);
    int colIndex = shared.tableView.getColumns().indexOf(col);
    var n = nodes.remove(colIndex);
    col.vbox.getChildren().remove(n);
    var cell = buildCell(col);
    nodes.add(colIndex, cell);
    col.vbox.getChildren().add(rowIndex, cell);
  }

  private @NotNull VTableCellPane<S> buildCell(VTableColumn<S, ?> col) {
    var cell = new VTableCellPane<>(buildNode(col), this, shared);
    if (item instanceof CellAware) {
      //noinspection unchecked,rawtypes
      ((CellAware) item).setCell(col, cell);
    }
    col.initCell(cell);
    return cell;
  }

  private Node buildNode(@NotNull VTableColumn<S, ?> col) {
    val v = col.valueRetriever.apply(item);
    if (col.nodeBuilder == null) {
      return v == null ? new Label() : new Label(v.toString());
    } else {
      //noinspection unchecked,rawtypes
      return (Node) ((Function) col.nodeBuilder).apply(v);
    }
  }

  @Override
  public void informRowUpdate() {
    runOnFx(this::informRowUpdate0);
  }

  private void informRowUpdate0() {
    IntStream.range(0, shared.tableView.getColumns().size())
        .forEach(
            i -> {
              val col = shared.tableView.getColumns().get(i);
              val node = buildNode(col);
              val pane = nodes.get(i);
              pane.getChildren().clear();
              if (node == null) {
                return;
              }
              pane.getChildren().add(node);
            });
  }

  public void updateColWidth(int i, double w) {
    val node = nodes.get(i);
    node.setPrefWidth(w);
    node.setMinWidth(w);
    node.setMaxWidth(w);
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    if (selected) {
      setBgColor(-1);
    }
  }

  public void setBgColor() {
    var rowIndex = shared.tableView.items.indexOf(this);
    setBgColor(rowIndex);
  }

  public void setBgColor(int rowNumber) {
    nodes.forEach(
        n -> {
          if (selected) {
            n.setBackground(BG_SELECTED); // force selected color
          } else if (rowNumber % 2 == 0) {
            n.setBackground0(BG_1);
          } else {
            n.setBackground0(BG_2);
          }
        });
  }
}
