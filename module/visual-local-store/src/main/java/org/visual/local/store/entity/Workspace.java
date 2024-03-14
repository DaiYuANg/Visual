package org.visual.local.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.visual.local.store.base.BaseEntity;

@Getter
@Setter
@Entity
@Table
public class Workspace extends BaseEntity {

  @Column private String name;

  @OneToMany Set<Project> projects;
}
