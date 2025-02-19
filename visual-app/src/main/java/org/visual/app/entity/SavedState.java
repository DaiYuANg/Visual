package org.visual.app.entity;

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
public class SavedState extends BaseEntity {

  private String path;
}
