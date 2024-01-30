package org.visual.component.container;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.visual.component.algebradata.ColorData;
import org.visual.component.algebradata.DoubleData;
import org.visual.component.animation.AnimationGraph;
import org.visual.component.animation.AnimationGraphBuilder;
import org.visual.component.animation.AnimationNode;
import org.visual.component.animation.Callback;
import org.visual.component.layout.HPadding;
import org.visual.component.layout.VPadding;
import org.visual.component.util.FXUtils;

public class FusionPane {
  public static final int PADDING_V = 10;
  public static final int PADDING_H = 10;

  private final AnimationGraph<DoubleData> contentOpacityAnimation;
  private final AnimationNode<DoubleData> contentOpacityNormalNode;
  private final AnimationNode<DoubleData> contentOpacityHoverNode;

  private final AbstractFusionPane root = buildRootNode();

  private final Pane content = new Pane();

  public FusionPane() {
    this(true);
  }

  public FusionPane(Node... nodes) {
    this(true, nodes);
  }

  public FusionPane(boolean manuallyHandleOuterRegion) {
    this(manuallyHandleOuterRegion, new Node[0]);
  }

  public FusionPane(boolean manuallyHandleOuterRegion, Node... nodes) {
    root.getChildren()
        .add(
            new VBox(
                new VPadding(PADDING_V),
                new HBox(new HPadding(PADDING_H), content, new HPadding(PADDING_H)),
                new VPadding(PADDING_V)));
    if (manuallyHandleOuterRegion) {
      FXUtils.observeWidthHeightWithPreferred(root, content, -PADDING_H * 2, -PADDING_V * 2);
    }
    FXUtils.makeClipFor(content, 4);
    getContentPane().getChildren().addAll(nodes);

    contentOpacityNormalNode =
        new AnimationNode<>("normal", new DoubleData(root.normalContentOpacity()));
    contentOpacityHoverNode =
        new AnimationNode<>("hover", new DoubleData(root.hoverContentOpacity()));

    contentOpacityAnimation =
        AnimationGraphBuilder.simpleTwoNodeGraph(
                contentOpacityNormalNode, contentOpacityHoverNode, 300)
            .setApply((from, to, data) -> content.setOpacity(data.value))
            .build(contentOpacityNormalNode);
  }

  protected AbstractFusionPane buildRootNode() {
    return new FusionPaneImpl();
  }

  public Region getNode() {
    return root;
  }

  public Pane getContentPane() {
    return content;
  }

  protected class FusionPaneImpl extends AbstractFusionPane {
    private final AnimationNode<ColorData> border =
        new AnimationNode<>("solid", new ColorData(hoverBorderColor()));
    private final AnimationNode<ColorData> noBorder =
        new AnimationNode<>("transparent", new ColorData(normalBorderColor()));

    private final AnimationGraph<ColorData> borderAnimation =
        AnimationGraphBuilder.simpleTwoNodeGraph(noBorder, border, 300)
            .setApply(
                (from, to, data) ->
                    setBorder(
                        new Border(
                            new BorderStroke(
                                data.getColor(),
                                BorderStrokeStyle.SOLID,
                                cornerRadii,
                                new BorderWidths(0.5)))))
            .build(noBorder);

    @Override
    protected void onMouseEntered() {
      super.onMouseEntered();
      borderAnimation.play(border);
      contentOpacityAnimation.play(contentOpacityHoverNode);
    }

    @Override
    protected void onMouseExited() {
      super.onMouseExited();
      borderAnimation.play(
          noBorder, Callback.ofIgnoreExceptionFunction(v -> setBorder(Border.EMPTY)));
      contentOpacityAnimation.play(contentOpacityNormalNode);
    }

    @Override
    protected CornerRadii getCornerRadii() {
      return new CornerRadii(8);
    }

    protected Color normalBorderColor() {
      return Color.TRANSPARENT;
    }

    protected Color hoverBorderColor() {
      return Color.TRANSPARENT;
    }
  }
}
