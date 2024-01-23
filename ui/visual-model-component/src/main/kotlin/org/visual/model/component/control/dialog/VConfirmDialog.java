package org.visual.model.component.control.dialog;


import java.util.Arrays;
import org.visual.model.component.manager.internal_i18n.InternalI18n;

public class VConfirmDialog extends VDialog<VConfirmDialog.Result> {
    public enum Result {
        YES,
        NO,
    }

    public VConfirmDialog() {
        setButtons(Arrays.asList(
            new VDialogButton<>(InternalI18n.get().confirmationYesButton(), Result.YES),
            new VDialogButton<>(InternalI18n.get().confirmationNoButton(), Result.NO)
        ));
    }
}
