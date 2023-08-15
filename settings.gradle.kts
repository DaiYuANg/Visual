pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
    maven { setUrl("https://jitpack.io") }
  }

  plugins {
    val gradlePreCommitGitGooksVersion: String by settings
    val modulepluginVersion: String by settings
    val javafxPluginVersion: String by settings
    val jlinkVersion: String by settings
    val lombokPluginVersion: String by settings
    val spotlessPluginVersion: String by settings
    val gitPropertiesVersion: String by settings
    id("com.gradle.enterprise") version "3.13.4"
    id("org.danilopianini.gradle-pre-commit-git-hooks") version gradlePreCommitGitGooksVersion
    id("org.javamodularity.moduleplugin") version modulepluginVersion
    id("org.openjfx.javafxplugin") version javafxPluginVersion
    id("org.beryx.jlink") version jlinkVersion
    id("io.freefair.lombok") version lombokPluginVersion
    id("com.diffplug.spotless") version spotlessPluginVersion
    id("com.gorylenko.gradle-git-properties") version gitPropertiesVersion
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
  id("com.gradle.enterprise")
  id("org.danilopianini.gradle-pre-commit-git-hooks")
}

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

rootProject.name = "VisualModel"

include(":vm-gui:vm-IDE")

include(":vml-compiler:vml-parser")

include(":vml-compiler:vml-cmd")

include("vm-gui:vm-components")

findProject(":vm-gui:vm-components")?.name = "vm-components"
