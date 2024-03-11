package org.visual.i18n;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.visual.i18n.api.I18nService;
import org.visual.i18n.service.I18nServiceImpl;

@Slf4j
public class I18nModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(I18nService.class).to(I18nServiceImpl.class);
  }
}
