pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        google()
        maven { setUrl("https://jitpack.io") }
    }

    plugins {
        id("com.gradle.enterprise") version "3.13.4"
//    id("org.danilopianini.gradle-pre-commit-git-hooks") version gradlePreCommitGitGooksVersion
//    id("org.javamodularity.moduleplugin") version modulepluginVersion
//    id("org.openjfx.javafxplugin") version javafxPluginVersion
//    id("org.beryx.jlink") version jlinkVersion
//    id("io.freefair.lombok") version lombokPluginVersion
//    id("com.diffplug.spotless") version spotlessPluginVersion
//    id("com.gorylenko.gradle-git-properties") version gitPropertiesVersion
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("com.gradle.enterprise")
//  id("org.danilopianini.gradle-pre-commit-git-hooks")
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

rootProject.name = "Visual"
include("executable:visual-designer")
include("executable:visual-database")
include("executable:visual-debugger")

include("module:visual-jdbc")
include("module:visual-shared")
include("module:visual-git")

include("libs:jfa")
include("serialize:visual-serialize-json")
include("libs:event")
include("libs:fonts")

include("ui:visual-component-annotation")
include("ui:visual-component")
include("ui:visual-text-editor")
include("ui:visual-graph-editor")
include("ui:visual-i18n")
include("ui:visual-collaborative")

include("serialize:visual-serialize-plantuml")
include("serialize:visual-serialize-api")
include("website")
include("executable:visual-frame")
findProject(":executable:visual-frame")?.name = "visual-frame"
