package org.visual.local.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.visual.local.store.base.BaseEntity;

@Table(name = "history")
@Entity
public class History extends BaseEntity {

  private String path;
}
