package org.visual.component.control.button;

public class TransparentFusionButton extends FusionButton {
  public TransparentFusionButton() {
    setOnlyAnimateWhenNotClicked(true);
  }

  public TransparentFusionButton(String text) {
    super(text);
    setOnlyAnimateWhenNotClicked(true);
  }
}
