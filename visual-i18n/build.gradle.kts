group = "org.visual.api"

apply<ModulePlugin>()

dependencies {
  implementation(libs.eclipse.collections)
  implementation(libs.eclipse.collections.api)

  implementation(libs.avaje.inject)
  annotationProcessor(libs.avaje.inject.generator)
  testImplementation(libs.avaje.inject.test)
}