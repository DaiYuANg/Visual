//import com.diffplug.gradle.spotless.SpotlessPlugin
import io.freefair.gradle.plugins.lombok.LombokPlugin
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    java
    checkstyle
    jacoco
    idea
    alias(libs.plugins.lombok)
//    id("com.diffplug.spotless")
    id("me.champeau.jmh") version "0.7.1"
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        google()
    }
}

subprojects {
    apply {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply<JavaLibraryPlugin>()
        apply<IdeaPlugin>()
        apply<LombokPlugin>()
//        apply<SpotlessPlugin>()
    }

    dependencies {
        compileOnly(rootProject.libs.jetbrainsAnnotation)
        implementation(rootProject.libs.slf4j)
        implementation(rootProject.libs.logback)
        testImplementation(rootProject.libs.junitBom)
        testImplementation(platform(rootProject.libs.junitBom))
        testImplementation(rootProject.libs.junitJuiter)
        testImplementation(rootProject.libs.junitApi)
        testImplementation(rootProject.libs.junitEngine)
        testImplementation(rootProject.libs.junitPlatformSuite)
        testImplementation(platform(rootProject.libs.testcontainersBom))
        testImplementation(rootProject.libs.testcontainers)
        testImplementation(rootProject.libs.testcontainersJunit)
        testImplementation(rootProject.libs.mockitoCore)
        testImplementation(rootProject.libs.mockitoJunit)
    }
    tasks {
        withType<JavaCompile> {
            options.encoding = Charsets.UTF_8.name()
//            dependsOn(rootProject.tasks.spotlessApply.name)
        }
        withType<Test> { useJUnitPlatform() }
    }

    java {
        sourceCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
        targetCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
    }

//    kotlin {
//        target {
////            jvmToolchain(rootProject.libs.versions.jdk.get().toInt())
//        }
//    }
}

//spotless {
//    format("misc") {
//        target("*.md", ".gitignore", "*.properties")
//        indentWithTabs()
//        endWithNewline()
//        clearSteps()
//    }
//    kotlinGradle {
//        target("*.gradle.kts")
//        ktfmt()
//    }
//    java {
//        target("**/*.java")
//        palantirJavaFormat()
//        removeUnusedImports()
//        importOrder()
//        eclipse()
//        formatAnnotations()
//    }
//    groovy {
//        target("**/*.groovy")
//        importOrder()
//        greclipse()
//    }
//    format("styling") {
//        target("**/*/*.scss")
//        prettier()
//    }
//    antlr4 {
//        target("src/*/antlr4/**/*.g4") // default value, you can change if you want
//        antlr4Formatter() // has its own section below
//        //        licenseHeader '/* (C) $YEAR */' // or licenseHeaderFile
//    }
//}
