group = "org.visual.model.serialize.plantuml"

dependencies {
  implementation(projects.serialize.visualSerializeApi)
  implementation(libs.autoService)
  implementation(libs.plantuml)
  annotationProcessor(libs.autoService)
}
