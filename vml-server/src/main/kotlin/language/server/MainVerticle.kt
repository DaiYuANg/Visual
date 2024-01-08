package language.server

import io.vertx.core.AbstractVerticle
import io.vertx.core.Context
import io.vertx.core.Vertx
import language.server.Slf4j.Companion.log

@Slf4j
class MainVerticle : AbstractVerticle() {
    override fun init(vertx: Vertx, context: Context) {
        log.info("test")
    }
}
