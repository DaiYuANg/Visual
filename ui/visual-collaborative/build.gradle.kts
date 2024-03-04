plugins {
}

group = "org.visual.collaborative.server"

dependencies {
  implementation(libs.vertxCore)
  testImplementation(libs.vertxJunit5)
  implementation(libs.vertxHazelcast)
  implementation(libs.vertxKotlin)
  implementation(projects.module.visualShared)
}
