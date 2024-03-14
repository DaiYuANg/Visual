package org.visual.local.store.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {
  @Id @Column private long id;

  @Column @CreationTimestamp private Date createAt;

  @Column @UpdateTimestamp private Date updateAt;
}
