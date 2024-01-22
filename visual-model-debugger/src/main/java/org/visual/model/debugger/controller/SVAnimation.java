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
package org.visual.model.debugger.controller;

import java.io.Serial;
import java.io.Serializable;
import javafx.animation.Animation;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
public final class SVAnimation implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    @Getter
    private final int id;
    @Getter
    private final String toString;
    @Getter
    private final double rate;
    @Getter
    private final double currentRate;
    @Getter
    private final String status;
    private final int cycleCount;
    @Getter
    private final String currentTime;
    @Getter
    private final String cycleDuration;
    @Getter
    private final String totalDuration;

    public SVAnimation(final int id, final @NotNull Animation animation) {
        this.id = id;
        this.toString = animation.toString();
        this.rate = animation.getRate();
        this.currentRate = animation.getCurrentRate();
        this.status = animation.getStatus().toString();
        this.cycleCount = animation.getCycleCount();
        this.cycleDuration = animation.getCycleDuration().toString();
        this.currentTime = ((int) animation.getCurrentTime().toMillis()) + "ms";
        this.totalDuration = ((int) animation.getTotalDuration().toMillis()) + "ms";
    }
}
