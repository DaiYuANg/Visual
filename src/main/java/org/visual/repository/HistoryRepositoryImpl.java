package org.visual.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.api.HistoryRepository;
import org.visual.entity.History;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<History> queryHistory() {
    return new ArrayList<>();
  }
}
