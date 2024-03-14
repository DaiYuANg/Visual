package org.visual.local.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.nio.file.Path;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.visual.local.store.base.BaseEntity;
import org.visual.local.store.converter.PathConverter;

@Getter
@Setter
@Entity
@Table
public class Project extends BaseEntity {

  @Column private String name;

  @Column
  @Convert(converter = PathConverter.class)
  private Path path;

  @Column private Date latestEditAt;
}
