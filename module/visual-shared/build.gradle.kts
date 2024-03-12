plugins {
//  `kotlin-project`
}

group = "org.visual.shared"

dependencies {
  api(libs.apacheCommonLang3)
  api(libs.directories)
  api(libs.jacksonCore)
  api(libs.oshi)
  implementation(libs.guava)
}