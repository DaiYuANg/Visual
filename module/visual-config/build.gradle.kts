apply<KotlinSetting>()
group = "org.visual.config"

dependencies {
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
  implementation(libs.gestaltYaml)
  implementation(libs.gestaltJSON)
  implementation(libs.gestaltKotlin)
  implementation(libs.directories)
  implementation(libs.guava)
  implementation(libs.gestaltGuice) {
    exclude(group = "com.google.guava", module = "guava")
  }
}