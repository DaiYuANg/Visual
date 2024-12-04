package org.visual.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
public class History extends BaseEntity {

  private String path;
}
