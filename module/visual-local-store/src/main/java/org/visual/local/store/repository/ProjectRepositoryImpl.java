package org.visual.local.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.local.store.api.ProjectRepository;
import org.visual.local.store.entity.Project;
import org.visual.local.store.entity.QProject;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ProjectRepositoryImpl implements ProjectRepository {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<Project> findLatestEdited() {
    val project =
        jpaQueryFactory
            .select(QProject.project)
            .from(QProject.project)
            .orderBy(QProject.project.latestEditAt.desc())
            .fetchFirst();
    return Optional.ofNullable(project);
  }
}
