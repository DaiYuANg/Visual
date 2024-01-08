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
    val kotlinVersion:String by settings
    id("com.gradle.enterprise") version "3.13.4"
    id("org.danilopianini.gradle-pre-commit-git-hooks") version gradlePreCommitGitGooksVersion
    id("org.javamodularity.moduleplugin") version modulepluginVersion
    id("org.openjfx.javafxplugin") version javafxPluginVersion
    id("org.beryx.jlink") version jlinkVersion
    id("io.freefair.lombok") version lombokPluginVersion
    id("com.diffplug.spotless") version spotlessPluginVersion
    id("com.gorylenko.gradle-git-properties") version gitPropertiesVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.lombok") version kotlinVersion
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
  id("com.gradle.enterprise")
  id("org.danilopianini.gradle-pre-commit-git-hooks")
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
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

//include(":vml-gui:vml-IDE")
//
//include(":vml-language:vml-parser")
//
//include(":vml-language:vml-cmd")
//
//include(":vml-gui:vml-components")
//
//findProject(":vml-gui:vm-components")?.name = "vm-components"
//
//include(":libs:theme-detector")
//
//include(":libs:shared")
//
//include("vml-codegen")
//
//include(":docs")
//
//include("vml-language:vml-lsp")
//
//findProject(":vml-language:vml-lsp")?.name = "vml-lsp"
//
//include("vml-server")
include("visual-model-editor")
