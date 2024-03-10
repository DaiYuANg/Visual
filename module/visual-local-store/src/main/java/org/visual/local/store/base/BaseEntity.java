package org.visual.local.store.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
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
public class BaseEntity {

  @Id @GeneratedValue private long id;

  @CreationTimestamp @Column private Date createAt;

  @UpdateTimestamp @Column private Date deleteAt;
}
