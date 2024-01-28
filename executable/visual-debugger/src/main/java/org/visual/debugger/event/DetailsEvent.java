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
package org.visual.debugger.event;

import java.io.Serial;
import java.util.List;
import lombok.Getter;
import org.visual.debugger.controller.StageID;
import org.visual.debugger.details.Detail;
import org.visual.debugger.details.DetailPaneType;


@Getter
public class DetailsEvent extends FXConnectorEvent {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private final DetailPaneType paneType;
    private final String paneName;
    final List<Detail> details;

    public DetailsEvent(
            final SVEventType type,
            final StageID id,
            final DetailPaneType dtype,
            final String paneName,
            final List<Detail> details
    ) {
        super(type, id);
        this.paneType = dtype;
        this.paneName = paneName;
        this.details = details;
    }
}
