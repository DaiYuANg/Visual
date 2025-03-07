module org.visual.i18n {
  requires static org.jetbrains.annotations;
  requires static lombok;
  requires org.slf4j;
  requires org.eclipse.collections.api;
  requires org.eclipse.collections.impl;
  requires io.avaje.inject;

  provides io.avaje.inject.spi.InjectExtension with org.visual.i18n.I18nModule;
}