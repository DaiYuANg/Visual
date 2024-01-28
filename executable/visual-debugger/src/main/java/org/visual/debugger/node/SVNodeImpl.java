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
package org.visual.debugger.node;

import java.io.Serial;
import java.io.Serializable;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

abstract class SVNodeImpl implements SVNode, Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    boolean invalidForFilter;
    boolean showID;
    boolean expanded;
    protected String nodeClass;
    protected String nodeClassName;

    protected SVNodeImpl() {

    }

    protected SVNodeImpl(final String nodeClass, final String nodeClassName) {
        this.nodeClass = nodeClass;
        this.nodeClassName = nodeClassName;
    }

    @Override
    public final void setInvalidForFilter(final boolean invalid) {
        this.invalidForFilter = invalid;
    }

    @Override
    public final boolean isInvalidForFilter() {
        return invalidForFilter;
    }

    @Override
    public final void setShowId(final boolean showID) {
        this.showID = showID;
    }

    @Override
    public boolean isExpanded() {
        return this.expanded;
    }

    @Override
    public final void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public Ikon getIcon() {
        return FontAwesomeSolid.BOX;
    }

    @Override
    public final String getNodeClass() {
        return nodeClass;
    }

    @Override
    public final String getNodeClassName() {
        return nodeClassName;
    }

}
