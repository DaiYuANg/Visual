/*
 * Scenic View, 
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler, Matthieu Brouillard 
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
package org.visual.model.debugger.event;


import java.io.Serial;
import lombok.Getter;
import org.visual.model.debugger.controller.StageID;
import org.visual.model.debugger.node.SVNode;

@Getter
public class EvCSSFXEvent extends FXConnectorEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final SVNode origin;
    private final String uri;
    private final String source;
    
    public EvCSSFXEvent(SVEventType type, StageID id, SVNode origin, String uri, String source) {
        super(type, id);
        this.origin = origin;
        this.uri = uri;
        this.source = source;
    }
}
