plugins {
}

group = "org.visual.collaborative.server"

dependencies {
  implementation(libs.vertxCore)
  testImplementation(libs.vertxJunit5)
  implementation(libs.vertxHazelcast)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(projects.module.visualShared)
  implementation(libs.mutiny)
  implementation(libs.mutinyVertx)
}