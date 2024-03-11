package org.visual.i18n;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.visual.i18n.core.I18nKeys;
import org.visual.i18n.core.I18nUtil;

class I18NUtilTest {

  @Test
  void getDefaultLocale() {
    val i18nValue = I18nUtil.getDefaultLocale().get(I18nKeys.CLICK);

    //    assertEquals(i18nValue, "点击");
  }
}
