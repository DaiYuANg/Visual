package org.visual.debugger.inspector;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ComponentDetails<T> {

  @Setter private T fieldNameComponent = null;
  @Getter @Setter private T classComponent;
  @Setter @Getter private T stylesComponent;

  @Getter private int locationX;
  @Getter private int locationY;

  public void setLocation(int x, int y) {
    this.locationX = x;
    this.locationY = y;
  }

  public Optional<T> getFieldNameComponent() {
    return Optional.ofNullable(fieldNameComponent);
  }
}
