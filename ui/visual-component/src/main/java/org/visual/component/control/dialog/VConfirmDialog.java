package org.visual.component.control.dialog;

public class VConfirmDialog extends VDialog<VConfirmDialog.Result> {
  public enum Result {
    YES,
    NO,
  }

  public VConfirmDialog() {
    //    setButtons(
    //        Arrays.asList(
    //            new VDialogButton<>(InternalI18n.get().confirmationYesButton(), Result.YES),
    //            new VDialogButton<>(InternalI18n.get().confirmationNoButton(), Result.NO)));
  }
}
