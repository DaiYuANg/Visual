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
package org.visual.debugger.controller;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class Configuration implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private boolean showBounds;

  private boolean showBaseline;

  private boolean showRuler;

  private int rulerSeparation;

  private String rulerColor = "000000";

  private boolean eventLogEnabled;

  private boolean autoRefreshStyles;

  private boolean ignoreMouseTransparent;

  private boolean collapseControls;

  private boolean collapseContentControls;

  private boolean autoRefreshSceneGraph;

  private boolean visibilityFilteringActive;

  private boolean CSSPropertiesDetail;

  /** I'm not totally sure about this... */
  private boolean componentSelectOnClick;

  private boolean registerShortcuts;
}
