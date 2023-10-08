package org.visual.model.verticles;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@Singleton
public class I18nVerticle extends AbstractVerticle {

    private final String locale;

    @Inject
    public I18nVerticle(@Named("Locale") String locale){
        log.info("i18n vertx init");
        this.locale = locale;

    }

    @Override
    public void init(Vertx vertx, Context context) {
       val bunle =  ResourceBundle.getBundle(locale);
        System.err.println(bunle);
        val bundle = ResourceBundle.getBundle("bundle", Locale.getDefault());
        bundle.getKeys().asIterator().forEachRemaining(System.err::println);
    }

    @Override
    public void start() throws Exception {
        super.start();
    }
}
