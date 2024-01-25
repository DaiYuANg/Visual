plugins { `kotlin-project` }

group = "org.visual.model.shared"

dependencies {
  api(libs.apacheCommonLang3)
  api(libs.jgrapht)
  api(libs.fastutil)
  api(libs.directories)
  api(libs.jacksonCore)
  api(libs.oshi)
}
