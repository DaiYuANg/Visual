package org.visual.collaborative.server

import io.vertx.core.Vertx
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import org.visual.shared.util.NetUtil.randomPort

class InternalServer {

  private val mgr by lazy { HazelcastClusterManager() }

  private val vertx by lazy {
    Vertx.builder()
        .withClusterManager(mgr)
        .buildClustered()
        .toCompletionStage()
        .toCompletableFuture()
        .join()
  }

  private val socketServer by lazy { vertx.createNetServer() }

  @JvmOverloads
  fun start(int: Int = randomPort()) {
    socketServer.connectHandler { System.err.println(it.indicatedServerName()) }
    socketServer.listen(int).toCompletionStage().toCompletableFuture().join()
  }
}
