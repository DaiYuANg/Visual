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
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.15"
  id("com.gradle.develocity") version "3.18.2"
}

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}

develocity { buildScan { termsOfUseAgree.set("true") } }

gitHooks {
  preCommit {
    from {
      """
      ./gradlew spotlessApply
      git add .
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

include("document")

include("visual-core")

include("visual-app")

include("visual-database")

include("visual-data-structure")

include("visual-i18n")

include("visual-collaboration-server")

include("visual-editor")