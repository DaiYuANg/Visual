package org.visual.local.store.base;

import io.ebean.annotation.DbDefault;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

  @Id @GeneratedValue private long id;

  @WhenCreated
  @Column
  @DbDefault("NOW()")
  private Date createAt;

  @WhenModified
  @Column
  @DbDefault("NOW()")
  private Date deleteAt;
}
