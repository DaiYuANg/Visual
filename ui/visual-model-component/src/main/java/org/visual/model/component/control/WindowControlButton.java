package org.visual.model.component.control;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.visual.model.component.container.VStage;
import org.visual.model.component.container.VStageInitParams;
import org.visual.model.component.control.button.AbstractFusionButton;

public abstract class WindowControlButton extends AbstractFusionButton {
    public static final int WIDTH = 64;
    public static final int HEIGHT = VStage.TITLE_BAR_HEIGHT;

    protected final VStage stage;

    protected final ImageView imageView = new ImageView();
    private final Image normalImage;
    private final Image hoverImage;

    WindowControlButton(VStage stage, VStageInitParams initParams) {
        super();
        this.stage = stage;

        init(initParams);

        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);

        cornerRadii = getCornerRadii();
        normalImage = getNormalImage();
        hoverImage = getHoverImage();

        imageView.setFitWidth(12);
        imageView.setFitHeight(12);
        imageView.setLayoutX((WIDTH - 12) / 2d);
        imageView.setLayoutY((HEIGHT - 12) / 2d);
        imageView.setImage(normalImage);

        getChildren().add(imageView);
    }

    protected void init(VStageInitParams initParams) {
    }

    @Override
    protected void onMouseEntered() {
        super.onMouseEntered();
        imageView.setImage(hoverImage);
    }

    @Override
    protected void onMouseExited() {
        super.onMouseExited();
        imageView.setImage(normalImage);
    }

    protected abstract Image getNormalImage();

    protected abstract Image getHoverImage();
}
