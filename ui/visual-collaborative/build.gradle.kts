plugins { `kotlin-project` }

group = "org.visual.collaborative.server"

dependencies {
  implementation("io.vertx:vertx-core:4.5.1")
  implementation("io.vertx:vertx-junit5:4.5.1")
  implementation("io.vertx:vertx-hazelcast:4.5.1")
  implementation(projects.module.visualShared)
}
