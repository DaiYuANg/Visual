
group = "org.visual.config"

dependencies {
  implementation(libs.guice)
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
  implementation(libs.gestaltYaml)
  implementation(libs.gestaltJSON)
  implementation(libs.directories)
  implementation(libs.gestaltGuice) {
    exclude(group = "com.google.guava", module = "guava")
  }
}