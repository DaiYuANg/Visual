package org.visual.local.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class History {

  @Id private long id;

  private Date createAt;

  private Date updateAt;
}
