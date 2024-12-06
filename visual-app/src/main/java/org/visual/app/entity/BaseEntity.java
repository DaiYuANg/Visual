package org.visual.app.entity;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static org.visual.app.constant.FieldNaming.*;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends Model {

  @Id
  @GeneratedValue
  private Long id;

  @WhenCreated
  @Column(name = CREATE_AT)
  private Date createAt;

  @WhenModified
  @Column(name = UPDATE_AT)
  private Date updateAt;

  @Column(name = SORT)
  private Long sort;
}
