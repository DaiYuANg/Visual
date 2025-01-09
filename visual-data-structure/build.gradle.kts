group = "org.visual.data.structure"

apply<ModulePlugin>()

dependencies {
  testImplementation(libs.data.faker)
  implementation(libs.jackson.annotations)
  implementation(libs.jackson.databind)
  implementation(libs.jgrapht)
  compileOnly(libs.immutables.value)
  annotationProcessor(libs.immutables.value)
  implementation(libs.eclipse.collections.api)
  implementation(libs.eclipse.collections)
}