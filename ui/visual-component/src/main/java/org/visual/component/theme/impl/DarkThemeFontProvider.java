package org.visual.component.theme.impl;


import org.jetbrains.annotations.NotNull;
import org.visual.component.font.FontManager;
import org.visual.component.font.FontSettings;
import org.visual.component.font.FontUsage;
import org.visual.component.theme.ThemeFontProvider;

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
