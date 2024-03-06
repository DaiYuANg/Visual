plugins {
  `kotlin-dsl`
}

repositories {
  mavenLocal()
  mavenCentral()
  gradlePluginPortal()
  google()
}

dependencies {
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
  implementation(libs.spotlessPlugin)
  implementation(libs.javaFxPlugin)
  implementation(libs.manifestPlugin)
  implementation(libs.lombokPlugin)
  implementation(libs.plantumlPlugin)
  implementation(libs.dokkaPlugin)
  implementation(libs.semverPlugin)
  implementation(libs.versionCheckPlugin)
  implementation(libs.dependencycheckPlugin)
  implementation(libs.jreleaserPlugin)
  implementation(libs.jmhPlugin)
  implementation(libs.spotbugsPlugin)
  implementation(libs.graalvmPlugin)
  implementation(libs.apacheCommonLang3)
}