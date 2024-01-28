group = "org.visual.model.serialize.plantuml"

dependencies {
  implementation("net.sourceforge.plantuml:plantuml-mit:1.2023.13")
  //  implementation(projects.serialize.visualModelSerializeApi)
  implementation(libs.autoService)
  annotationProcessor(libs.autoService)
}
