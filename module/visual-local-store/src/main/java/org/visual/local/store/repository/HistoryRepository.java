package org.visual.local.store.repository;

import org.visual.local.store.entity.History;
import org.visual.local.store.entity.query.QHistory;

public class HistoryRepository {
  public History findLatestHistory() {
    return new QHistory()
        .select(QHistory.alias().path)
        .orderBy()
        .select(QHistory.alias().createAt)
        .setMaxRows(1)
        .findOne();
  }
}
