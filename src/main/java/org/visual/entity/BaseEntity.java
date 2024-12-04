package org.visual.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import static org.visual.constant.FieldNaming.*;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  @CreationTimestamp
  @Column(name = CREATE_AT)
  private Date createAt;

  @UpdateTimestamp
  @Column(name = UPDATE_AT)
  private Date updateAt;

  @Column(name = SORT)
  private Long sort;
}
