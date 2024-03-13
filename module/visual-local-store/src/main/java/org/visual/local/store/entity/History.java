package org.visual.local.store.entity;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
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

  @WhenCreated private Date createAt;

  @WhenModified private Date updateAt;
}
