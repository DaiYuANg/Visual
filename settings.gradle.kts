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
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.7"
  id("com.gradle.develocity") version "3.17.5"
}

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}

develocity {
  buildScan {
    termsOfUseAgree.set("true")
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
  commitMsg {
    conventionalCommits {
      defaultTypes()
      types("format")
    }
  }
  createHooks(true)
}

rootProject.name = "Visual"

include("website")
include("visual-maven")
include("visual-store")
include("visual-editor")
include("visual-i18n")