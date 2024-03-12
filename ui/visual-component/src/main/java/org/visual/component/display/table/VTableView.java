package org.visual.component.display.table;

import java.util.*;
import java.util.stream.IntStream;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.component.control.scroll.NodeWithVScrollPane;
import org.visual.component.control.scroll.VScrollPane;
import org.visual.component.util.ObserveUtil;
import org.visual.component.util.SizeUtil;

public class VTableView<S> implements NodeWithVScrollPane {
  private final Pane root = new Pane();
  private final VBox rootVBox = new VBox();
  final HBox columnPane = new HBox();
  private final Pane fixColumnWidthColum =
      new Pane() {
        {
          setBackground(VTableColumn.BG);
          setPrefWidth(0);
        }
      };
  private final VScrollPane scrollPane = new VScrollPane();
  private final HBox dataPane = new HBox();
  private final Label emptyTableLabel =
      new Label() {
        {
          setAlignment(Pos.CENTER);
          setTextFill(Color.GRAY);
        }
      };

  private final VTableSharedData<S> shared = new VTableSharedData<>(this);

  @Getter
  private final ObservableList<VTableColumn<S, ?>> columns = FXCollections.observableArrayList();

  private final ArrayList<VTableColumn<S, ?>> lastColumns = new ArrayList<>();
  final ObservableList<VTableRow<S>> items = FXCollections.observableArrayList();
  private final VTableRowListDelegate<S> itemsDelegate = new VTableRowListDelegate<>(items, shared);
  private VTableRow<S> selectedRow = null;

  public VTableView() {
    scrollPane.setContent(emptyTableLabel);
    columns.addListener(colsListener);
    items.addListener(itemsListener);

    root.getChildren().add(rootVBox);
    ObserveUtil.observeWidthHeightWithPreferred(root, rootVBox);

    var columnPane = new HBox();
    rootVBox.getChildren().addAll(columnPane, scrollPane.getNode());

    ChangeListener<? super Number> rootVBoxWidthChange =
        (ob, old, now) -> {
          if (now == null) return;
          scrollPane.getNode().setPrefWidth(now.doubleValue());
          if (rootVBox.getPrefWidth() != 0) {
            updateWidth(rootVBox.getPrefWidth());
          } else {
            updateWidth();
          }
          columnWidthFix();
        };
    rootVBox.widthProperty().addListener(rootVBoxWidthChange);
    rootVBox.prefWidthProperty().addListener(rootVBoxWidthChange);
    this.columnPane.widthProperty().addListener((ob, old, now) -> columnWidthFix());
    scrollPane
        .getNode()
        .widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              emptyTableLabel.setPrefWidth(now.doubleValue());
              columnWidthFix();
            });
    scrollPane
        .getNode()
        .heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              emptyTableLabel.setPrefHeight(now.doubleValue() - 10);
            });
    rootVBox
        .heightProperty()
        .addListener(
            (ob, old, now) ->
                scrollPane.getNode().setPrefHeight(rootVBox.getHeight() - columnPane.getHeight()));
    columnPane
        .heightProperty()
        .addListener(
            (ob, old, now) ->
                scrollPane.getNode().setPrefHeight(rootVBox.getHeight() - columnPane.getHeight()));

    columnPane.getChildren().addAll(this.columnPane, fixColumnWidthColum);

    SizeUtil.makeTopOnlyRoundedClipFor(columnPane, 4);
    SizeUtil.makeBottomOnlyRoundedClipFor(dataPane, 4);
  }

  public Region getNode() {
    return root;
  }

  public List<S> getItems() {
    return itemsDelegate;
  }

  public void setItems(List<S> items) {
    this.items.removeListener(itemsListener);
    this.items.clear();
    items.stream()
        .map(item -> new VTableRow<>(item, shared))
        .forEach(
            row -> {
              row.setCols(columns);
              this.items.add(row);
            });
    this.items.addListener(itemsListener);
    sort();
    updateWidth();
    if (items.isEmpty()) {
      scrollPane.setContent(emptyTableLabel);
    } else {
      scrollPane.setContent(dataPane);
    }
  }

  @SuppressWarnings("FieldCanBeLocal")
  private final ListChangeListener<VTableColumn<S, ?>> colsListener =
      c -> {
        while (c.next()) {
          var added = c.getAddedSubList();
          var removed = c.getRemoved();

          IntStream.iterate(removed.size() - 1, i -> i >= 0, i -> i - 1)
              .mapToObj(removed::get)
              .forEach(
                  col -> {
                    int index = lastColumns.indexOf(col);
                    lastColumns.remove(index);
                    assert index != -1;
                    for (var row : items) {
                      row.removeCol(index);
                    }
                    dataPane.getChildren().remove(index);
                    columnPane.getChildren().remove(index);
                    clearSort(col);
                    col.shared = null;
                  });
          added.forEach(
              col -> {
                var index = columns.indexOf(col);
                lastColumns.add(index, col);
                for (var row : items) {
                  row.addCol(index, col);
                }
                dataPane.getChildren().add(index, col.vbox);
                columnPane.getChildren().add(index, col.columnNode);
                col.shared = shared;
              });
        }
        updateWidth();
      };

  private final ListChangeListener<VTableRow<S>> itemsListener =
      c -> {
        while (c.next()) {
          var added = c.getAddedSubList();
          var removed = c.getRemoved();

          for (var row : removed) {
            row.remove();
          }
          for (var row : added) {
            row.setCols(columns);
            var index = items.indexOf(row);
            row.add(index);
          }
        }
        for (int i = 0; i < items.size(); i++) {
          var row = items.get(i);
          row.setBgColor(i);
        }
        if (items.isEmpty()) {
          scrollPane.setContent(emptyTableLabel);
        } else {
          scrollPane.setContent(dataPane);
          updateWidth();
        }
        var hasSort = false;
        for (var col : columns) {
          if (col.getSortPriority() > 0) {
            hasSort = true;
            break;
          }
        }
        if (hasSort) {
          sort();
        }
      };

  private void columnWidthFix() {
    double columnW = columnPane.getWidth();
    double scrollW = scrollPane.getNode().getWidth();
    if (scrollW > columnW) {
      fixColumnWidthColum.setPrefWidth(scrollW - columnW);
    } else {
      fixColumnWidthColum.setPrefWidth(0);
    }
  }

  void updateWidth() {
    updateWidth(scrollPane.getNode().getWidth());
  }

  private void updateWidth(double width) {
    if (width <= 0) return;
    if (columns.isEmpty()) return;
    var plan = buildUpdateWithPrefWidthPlan(width);
    if (plan != null) {
      updateWidth(plan);
      return;
    }
    plan = buildUpdateAvgConsiderMinMaxPlan(width);
    if (plan != null) {
      updateWidth(plan);
      return;
    }
    plan = buildAvgPlan(width);
    updateWidth(plan);
  }

  private Map<VTableColumn<S, ?>, Double> buildUpdateWithPrefWidthPlan(double width) {
    var ret = new HashMap<VTableColumn<S, ?>, Double>();

    var prefWCols = new ArrayList<VTableColumn<S, ?>>();

    double remain = width;
    int remainCnt = columns.size();
    for (var c : columns) {
      if (c.prefWidth > 0) {
        remain -= c.prefWidth;
        ret.put(c, c.prefWidth);
        prefWCols.add(c);
        --remainCnt;
      }
    }
    if (remain < 0) { // exceeds
      return null;
    }
    if (prefWCols.size() == columns.size()) {
      return ret;
    }

    return buildUpdateAvgConsiderMinMaxPlan(ret, prefWCols, remain, remainCnt);
  }

  private Map<VTableColumn<S, ?>, Double> buildUpdateAvgConsiderMinMaxPlan(double width) {
    var ret = new HashMap<VTableColumn<S, ?>, Double>();
    return buildUpdateAvgConsiderMinMaxPlan(ret, Collections.emptyList(), width, columns.size());
  }

  @SuppressWarnings("UnnecessaryContinue")
  private Map<VTableColumn<S, ?>, Double> buildUpdateAvgConsiderMinMaxPlan(
      HashMap<VTableColumn<S, ?>, Double> ret,
      List<VTableColumn<S, ?>> prefWCols,
      double remain,
      int remainCnt) {

    var exceedsMin = new ArrayList<VTableColumn<S, ?>>();
    var exceedsMax = new ArrayList<VTableColumn<S, ?>>();

    double avg = remain / remainCnt;
    while (true) {
      var exceedsMin0 = new ArrayList<VTableColumn<S, ?>>();
      var exceedsMax0 = new ArrayList<VTableColumn<S, ?>>();
      for (var c : columns) {
        if (prefWCols.contains(c)) continue;
        if (exceedsMin.contains(c)) continue;
        if (exceedsMax.contains(c)) continue;
        if (c.minWidth > 0) {
          if (c.minWidth > avg) {
            if (remain < c.minWidth) {
              return null;
            }
            remain -= c.minWidth;
            --remainCnt;
            exceedsMin0.add(c);
            ret.put(c, c.minWidth);
            continue;
          }
        }
        if (c.maxWidth > 0) {
          if (c.maxWidth < avg) {
            if (remain < c.maxWidth) {
              return null;
            }
            remain -= c.maxWidth;
            --remainCnt;
            exceedsMax0.add(c);
            ret.put(c, c.maxWidth);
            continue;
          }
        }
      }
      exceedsMin.addAll(exceedsMin0);
      exceedsMax.addAll(exceedsMax0);
      if (remainCnt == 0) {
        return ret;
      }
      avg = remain / remainCnt;
      if (exceedsMin0.isEmpty() && exceedsMax0.isEmpty()) {
        for (var c : columns) {
          if (prefWCols.contains(c)) continue;
          if (exceedsMin.contains(c)) continue;
          if (exceedsMax.contains(c)) continue;
          ret.put(c, avg);
        }
        return ret;
      }
    }
  }

  private Map<VTableColumn<S, ?>, Double> buildAvgPlan(double width) {
    var avg = width / columns.size();
    var ret = new HashMap<VTableColumn<S, ?>, Double>();
    for (var c : columns) {
      ret.put(c, avg);
    }
    return ret;
  }

  private void updateWidth(Map<VTableColumn<S, ?>, Double> plan) {
    for (int i = 0; i < columns.size(); i++) {
      var c = columns.get(i);
      var w = plan.get(c);
      assert w != null;
      if (items.isEmpty()) {
        c.columnNode.setPrefWidth(w);
      }
      for (var row : items) {
        row.updateColWidth(i, w);
      }
    }
  }

  void refresh() {
    for (var c : columns) {
      c.vbox.getChildren().clear();
    }
    for (int i = 0; i < items.size(); i++) {
      var row = items.get(i);
      row.add();
      row.setBgColor(i);
    }
  }

  void updateRowNodeForColumn(VTableColumn<S, ?> col) {
    for (var row : items) {
      row.updateRowNodeForColumn(col);
    }
  }

  void selectRow(VTableRow<S> r) {
    if (selectedRow != null) {
      var index = items.indexOf(selectedRow);
      if (index != -1) {
        selectedRow.setSelected(false);
        selectedRow.setBgColor(index);
      }
    }
    r.setSelected(true);
    selectedRow = r;
  }

  public S getSelectedItem() {
    if (selectedRow == null) return null;
    if (!items.contains(selectedRow)) {
      return null;
    }
    return selectedRow.item;
  }

  public void sortBy(VTableColumn<S, ?> c, VTableSortOrder order) {
    if (c.comparator == null) {
      return;
    }
    if (c.getSortPriority() == 0) {
      int p =
          columns.stream()
              .mapToInt(VTableColumn::getSortPriority)
              .filter(col -> col >= 0)
              .max()
              .orElse(0);
      ++p;
      c.setSort(p, order);
    } else {
      c.setSort(c.getSortPriority(), order);
    }
    sort();
  }

  public void clearSort(VTableColumn<S, ?> c) {
    c.resetSortOrder();
    if (c.getSortPriority() == 0) {
      return;
    }
    columns.stream()
        .filter(col -> col != c)
        .filter(col -> col.getSortPriority() >= c.getSortPriority())
        .forEach(VTableColumn::decSortPriority);
    c.resetSortPriority();
    sort();
  }

  void sort() {
    var cols =
        new ArrayList<>(columns)
            .stream()
                .filter(c -> c.getSortPriority() > 0 && c.comparator != null)
                .sorted(Comparator.comparingInt(VTableColumn::getSortPriority))
                .toList();
    var tmp = new ArrayList<>(items);
    tmp.sort(
        (a, b) -> {
          for (var c : cols) {
            var va = c.valueRetriever.apply(a.item);
            var vb = c.valueRetriever.apply(b.item);
            //noinspection unchecked,rawtypes
            var res = ((Comparator) c.comparator).compare(va, vb);
            if (res == 0) {
              continue;
            }
            if (c.getSortOrder() == VTableSortOrder.ASC) {
              return res;
            } else {
              return -res;
            }
          }
          return Long.compare(a.rowId, b.rowId);
        });

    this.items.removeListener(itemsListener);
    this.items.clear();
    this.items.addAll(tmp);
    this.items.addListener(itemsListener);
    refresh();
  }

  @Override
  public VScrollPane getScrollPane() {
    return scrollPane;
  }

  @Override
  public Region getSelfNode() {
    return getNode();
  }
}
