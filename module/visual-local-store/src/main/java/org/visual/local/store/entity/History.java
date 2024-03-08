package org.visual.local.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.visual.local.store.base.BaseEntity;

@Table(name = "history")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class History extends BaseEntity {

  private String path;
}
