plugins {
}

group = "org.visual.collaborative"

dependencies {
  implementation(libs.vertxCore)
  testImplementation(libs.vertxJunit5)
  implementation(libs.vertxHazelcast)
  implementation(projects.module.visualShared)
  implementation(libs.mutiny)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
  implementation(libs.mutinyVertx)
}