package org.visual.collections.table;

import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

@Slf4j
public class ObservableTable<R, C, V> implements Observable, Table<R, C, V> {

  private final ObservableMap<R, ObservableMap<C, V>> internal = FXCollections.emptyObservableMap();

  @Override
  @SuppressWarnings("unchecked")
  public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
    return Optional.ofNullable(internal.get((R) rowKey))
        .map(map -> map.containsKey((C) columnKey))
        .isPresent();
  }

  @Override
  public boolean containsRow(@Nullable Object rowKey) {
    return false;
  }

  @Override
  public boolean containsColumn(@Nullable Object columnKey) {
    return false;
  }

  @Override
  public boolean containsValue(@Nullable Object value) {
    return false;
  }

  @Nullable @Override
  public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public void clear() {}

  @Nullable @Override
  public V put(R rowKey, C columnKey, V value) {
    return null;
  }

  @Override
  public void putAll(Table<? extends R, ? extends C, ? extends V> table) {}

  @Nullable @Override
  public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
    return null;
  }

  @Override
  public Map<C, V> row(R rowKey) {
    return null;
  }

  @Override
  public Map<R, V> column(C columnKey) {
    return null;
  }

  @Override
  public Set<Cell<R, C, V>> cellSet() {
    return null;
  }

  @Override
  public Set<R> rowKeySet() {
    return null;
  }

  @Override
  public Set<C> columnKeySet() {
    return null;
  }

  @Override
  public Collection<V> values() {
    return null;
  }

  @Override
  public Map<R, Map<C, V>> rowMap() {
    return null;
  }

  @Override
  public Map<C, Map<R, V>> columnMap() {
    return null;
  }

  @Override
  public void addListener(InvalidationListener listener) {}

  @Override
  public void removeListener(InvalidationListener listener) {}

  @Override
  public Subscription subscribe(Runnable invalidationSubscriber) {
    return Observable.super.subscribe(invalidationSubscriber);
  }
}
