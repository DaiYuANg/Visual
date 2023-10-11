package org.visual.model.components.controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class SpacedListCell<T> extends ListCell<T> implements Initializable {
	public double spacing;

	public SpacedListCell(double spacing) {
		this.spacing = spacing;
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		System.err.println(123);
		super.updateItem(item, empty);

		if (item == null || empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.toString());
			setGraphic(null);
		}

		setPadding(new javafx.geometry.Insets(spacing));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
