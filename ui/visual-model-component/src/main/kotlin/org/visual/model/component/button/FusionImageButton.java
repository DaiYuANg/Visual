package org.visual.model.component.button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

@Getter
public class FusionImageButton extends FusionButton {
    private final ImageView imageView = new ImageView();

    public FusionImageButton() {
        this(null);
    }

    public FusionImageButton(Image image) {
        if (image != null) {
            imageView.setImage(image);
        }
        imageView.setPreserveRatio(true);
        getChildren().add(imageView);

        widthProperty().addListener((ob, old, now) -> {
            if (now == null) return;
            updateImagePosition();
        });
        heightProperty().addListener((ob, old, now) -> {
            if (now == null) return;
            updateImagePosition();
        });
        setDisableAnimation(true);
    }

    private void updateImagePosition() {
        var bounds = imageView.getLayoutBounds();
        imageView.setLayoutX((getWidth() - bounds.getWidth()) / 2);
        imageView.setLayoutY((getHeight() - bounds.getHeight()) / 2);
    }
}
