package org.visual.model.component.theme.impl;


import org.jetbrains.annotations.NotNull;
import org.visual.model.component.font.FontManager;
import org.visual.model.component.font.FontSettings;
import org.visual.model.component.font.FontUsage;
import org.visual.model.component.theme.ThemeFontProvider;

public class DarkThemeFontProvider extends ThemeFontProvider {
    protected void defaultFont(@NotNull FontSettings settings) {
        settings.setFamily(FontManager.FONT_NAME_NotoSansSCRegular);
    }

    @Override
    protected void windowTitle(FontSettings settings) {
        defaultFont(settings);
        settings.setSize(15);
    }

    @Override
    protected void tableCellText(FontSettings settings) {
        defaultFont(settings);
        settings.setSize(12);
    }

    @Override
    protected void _default(FontUsage usage, FontSettings settings) {
        defaultFont(settings);
        settings.setSize(16);
    }
}
