package org.visual.model.i18n.core;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class I18NUtilTest {

    @Test
    void getDefaultLocale() {
        val i18nValue = I18nUtil.getDefaultLocale().get(I18nKeys.CLICK);

        assertEquals(i18nValue, "点击");
    }
}