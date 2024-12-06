group = "org.visual.config"

apply<ModulePlugin>()

dependencies {
  implementation(libs.gestalt.toml)
  implementation(libs.gestalt.yaml)
  implementation(libs.gestalt)
}