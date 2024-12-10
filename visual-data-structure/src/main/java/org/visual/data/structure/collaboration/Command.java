package org.visual.data.structure.collaboration;

import org.immutables.value.Value;
import org.visual.data.structure.constant.CollaborationCommand;
import org.visual.data.structure.style.VisualDataStructureStyle;

@Value.Immutable
@VisualDataStructureStyle
public interface Command {
  CollaborationCommand command();
}
