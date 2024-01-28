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
package org.visual.debugger.details;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import lombok.Getter;
import lombok.Setter;
import org.visual.debugger.api.FXConnectorEventDispatcher;
import org.visual.debugger.controller.StageID;
import org.visual.debugger.event.DetailsEvent;
import org.visual.debugger.event.FXConnectorEvent;


public class Detail implements Serializable {

    public static final String EMPTY_DETAIL = "---";

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String STATUS_NOT_SET = "Value can not be changed ";
    public static final String STATUS_NOT_SUPPORTED = STATUS_NOT_SET + "(Not supported yet)";
    public static final String STATUS_BOUND = STATUS_NOT_SET + "(Bound property)";
    public static final String STATUS_EXCEPTION = STATUS_NOT_SET + "an exception has ocurred:";
    public static final String STATUS_READ_ONLY = STATUS_NOT_SET + "(Read-Only property)";

    /**
     * Represents the left-hand side of the two columns in the detail grid
     */
    public enum LabelType {
        NORMAL, LAYOUT_BOUNDS, BOUNDS_PARENT, BASELINE
    }

    /**
     * Represents the right-hand side of the two columns in the detail grid
     */
    public enum ValueType {
        NORMAL, INSETS, CONSTRAINTS, GRID_CONSTRAINTS, COLOR
    }

    ;

    public enum EditionType {
        NONE_BOUND, NONE, TEXT, COMBO, SLIDER, COLOR_PICKER
    }

    @Setter
    @Getter
    private boolean isDefault;
    @Setter
    @Getter
    private String property;
    @Getter
    @Setter
    private String label;
    @Getter
    @Setter
    private String value;
    @Getter
    private String reason;
    @Setter
    @Getter
    private LabelType labelType = LabelType.NORMAL;
    @Setter
    @Getter
    private ValueType valueType = ValueType.NORMAL;
    @Getter
    private EditionType editionType = EditionType.NONE;
    transient WritableValue<String> serializer;

    private final transient FXConnectorEventDispatcher dispatcher;
    @Getter
    private final DetailPaneType detailType;
    @Getter
    private final int detailID;
    private final StageID stageID;
    private final transient List<Detail> details;
    private static final transient DecimalFormat f = new DecimalFormat("0.0#");
    @Setter
    @Getter
    private String detailName;
    @Getter
    private String[] validItems;
    @Getter
    private double maxValue;
    @Getter
    private double minValue;
    @Getter
    private String realValue;
    private boolean hasGridConstraints;
    @Getter
    private final List<GridConstraintsDetail> gridConstraintsDetails = new ArrayList<>();

    public Detail(final FXConnectorEventDispatcher dispatcher, final StageID stageID, final DetailPaneType detailType, final int detailID) {
        this.dispatcher = dispatcher;
        this.stageID = stageID;
        this.detailType = detailType;
        this.detailID = detailID;
        this.details = new ArrayList<>(1);
        details.add(this);

    }

    public final void updated() {
        dispatcher.dispatchEvent(new DetailsEvent(FXConnectorEvent.SVEventType.DETAIL_UPDATED, stageID, detailType, detailName, details));
    }

    public void setSimpleSizeProperty(final DoubleProperty x, final DoubleProperty y) {
        if (x != null) {
            if (x.isBound() && y.isBound()) {
                unavailableEdition(STATUS_BOUND, EditionType.NONE_BOUND);
            } else {
                setSerializer(new SizeSerializer(x, y));
            }
        } else {
            setReason(STATUS_NOT_SUPPORTED);
            setSerializer(null);
        }
    }

    void setSerializer(final WritableValue<String> serializer) {
        setSerializer(serializer, EditionType.NONE);
    }

    void setSerializer(final WritableValue<String> serializer, final EditionType defaultEditionType) {
        this.serializer = serializer;
        this.editionType = defaultEditionType;
        if (serializer != null) {
            realValue = serializer.getValue();
            // Probably this should be an interface...
            if (serializer instanceof SimpleSerializer) {
                final SimpleSerializer.EditionType type = ((SimpleSerializer) serializer).getEditionType();
                switch (type) {
                    case COMBO -> {
                        editionType = EditionType.COMBO;
                        validItems = ((SimpleSerializer) serializer).getValidValues();
                    }
                    case SLIDER -> {
                        editionType = EditionType.SLIDER;
                        maxValue = ((SimpleSerializer) serializer).getMaxValue();
                        minValue = ((SimpleSerializer) serializer).getMinValue();
                    }
                    case COLOR_PICKER -> {
                        valueType = ValueType.COLOR;
                        editionType = EditionType.COLOR_PICKER;
                    }
                    default -> {
                        editionType = EditionType.TEXT;
                    }
                }
            } else {
                editionType = EditionType.TEXT;
            }
        }
    }

    public final void setReason(final String reason) {
        this.reason = reason;
    }

    @SuppressWarnings("rawtypes")
    public void setEnumProperty(final Property property, final Class<? extends Enum> enumClass) {
        setSimpleProperty(property, enumClass);
    }

    public void setSimpleProperty(@SuppressWarnings("rawtypes") final Property property) {
        setSimpleProperty(property, null);
    }

    private void setSimpleProperty(@SuppressWarnings("rawtypes") final Property property, @SuppressWarnings({"rawtypes"}) final Class<? extends Enum> enumClass) {
        if (property != null) {
            if (property.isBound()) {
                unavailableEdition(STATUS_BOUND, EditionType.NONE_BOUND);
            } else {
                final SimpleSerializer s = new SimpleSerializer(property);
                s.setEnumClass(enumClass);
                setSerializer(s);
            }
        } else {
            unavailableEdition(STATUS_NOT_SUPPORTED);
        }
    }

    void unavailableEdition(final String reason) {
        unavailableEdition(reason, EditionType.NONE);
    }

    void unavailableEdition(final String reason, final EditionType defaultEditionType) {
        setReason(reason);
        setSerializer(null, defaultEditionType);
    }

    public void setConstraints(@SuppressWarnings("rawtypes") final ObservableList rowCol) {
        hasGridConstraints = (rowCol != null && !rowCol.isEmpty());
        gridConstraintsDetails.clear();
    }

    public void add(final String text, final int colIndex, final int rowIndex) {
        gridConstraintsDetails.add(new GridConstraintsDetail(text, colIndex, rowIndex));
    }

    public void addSize(final double v, final int rowIndex, final int colIndex) {
        add(v != Region.USE_COMPUTED_SIZE ? f.format(v) : "-", colIndex, rowIndex);
    }

    public void addObject(final Object v, final int rowIndex, final int colIndex) {
        add(v != null ? v.toString() : "-", colIndex, rowIndex);
    }

    public boolean hasGridConstraints() {
        return hasGridConstraints;
    }

    public static boolean isEditionSupported(final EditionType editionType) {
        return editionType != EditionType.NONE && editionType != EditionType.NONE_BOUND;
    }
}
