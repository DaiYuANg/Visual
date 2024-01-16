/*
 * Scenic View, 
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.model.ui.inspector.event;

import lombok.Getter;
import org.visual.model.ui.inspector.StageID;

import java.io.Serial;

@Getter
public class NodeCountEvent extends FXConnectorEvent {

    @Serial
    private static final long serialVersionUID = 1L;
    private final int nodeCount;

    public NodeCountEvent(final StageID id, final int nodeCount) {
        this(SVEventType.NODE_COUNT, id, nodeCount);
    }

    protected NodeCountEvent(final SVEventType type, final StageID id, final int nodeCount) {
        super(type, id);
        this.nodeCount = nodeCount;
    }
}
