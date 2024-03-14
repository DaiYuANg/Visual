pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
    maven { setUrl("https://jitpack.io") }
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
  id("com.gradle.enterprise") version "3.13.4"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.2"
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

gitHooks {
  preCommit {
    from {
      """
      ./gradlew spotbugsMain
      ./gradlew spotbugsTest
      ./gradlew pmdMain
      ./gradlew pmdTest
      ./gradlew spotlessApply && git add .
      """
    }
  }
  commitMsg { conventionalCommits { defaultTypes() } }
  createHooks(true)
}

rootProject.name = "Visual"

include("ui:visual-component")
include("ui:visual-text-editor")
include("ui:visual-graph-editor")
include("ui:visual-i18n")
include("ui:visual-collaborative")
include("ui:visual-debugger")

include("module:visual-jdbc")
include("module:visual-shared")
include("module:visual-git")
include("module:visual-local-store")
include("module:visual-maven-repository")
include("module:visual-config")
include("module:visual-script")
include("module:visual-pdm")

include("serialize:visual-serialize-json")
include("serialize:visual-serialize-plantuml")
include("serialize:visual-serialize-api")

include("website")