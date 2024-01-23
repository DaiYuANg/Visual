package org.visual.model.component.theme;


import org.visual.model.component.font.FontProvider;
import org.visual.model.component.font.FontSettings;
import org.visual.model.component.font.FontUsage;
import org.visual.model.component.font.FontUsages;

public abstract class ThemeFontProvider implements FontProvider {
    @Override
    public void apply(FontUsage usage, FontSettings settings) {
        if (usage == FontUsages.windowTitle) {
            windowTitle(settings);
        } else if (usage == FontUsages.tableCellText) {
            tableCellText(settings);
        } else {
            _default(usage, settings);
        }
    }

    protected abstract void windowTitle(FontSettings settings);

    protected abstract void tableCellText(FontSettings settings);

    protected abstract void _default(FontUsage usage, FontSettings settings);
}
