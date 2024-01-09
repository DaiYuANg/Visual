import io.freefair.gradle.plugins.lombok.LombokPlugin
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import io.gitlab.plunts.gradle.plantuml.plugin.PlantUmlPlugin
import java.nio.charset.StandardCharsets

plugins {
    `java-library`
    checkstyle
    jacoco
    idea
    alias(libs.plugins.gitVersion)
    alias(libs.plugins.lombok)
//    id("com.diffplug.spotless")
    alias(libs.plugins.plantuml)
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
val plantUMLSuffix = "puml"
subprojects {
    apply {
        apply<JavaLibraryPlugin>()
        apply<IdeaPlugin>()
        apply<LombokPlugin>()
        apply<PlantUmlPlugin>()
    }

    dependencies {
        implementation(rootProject.libs.jetbrainsAnnotation)
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

    group = "org." + project.name.replace("-",".")
//    val versionDetails: groovy.lang.Closure<VersionDetails> by extra
//    val details = versionDetails()
//    version = details.lastTag
    System.err.println(group)
    tasks.compileJava {
        options.encoding = StandardCharsets.UTF_8.name()
    }

    tasks.test {
        useJUnitPlatform()
    }

    java {
//        modularity.inferModulePath.set(true)
        sourceCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
        targetCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
    }

    classDiagrams {
        val glob = "org.${project.name.replace("-", ".")}.**"
        val internal = "internal_class_diagram"
        val full = "full_class_diagram"
        @Suppress("UNCHECKED_CAST")
        diagram(
            internal,
            closureOf<ClassDiagramsExtension.ClassDiagram> {
                include(packages().withNameLike(glob))
                writeTo(file(project.layout.buildDirectory.file("$internal.${project.name}.$plantUMLSuffix")))
            }
                    as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
        )

        @Suppress("UNCHECKED_CAST")
        diagram(
            full,
            closureOf<ClassDiagramsExtension.ClassDiagram> {
                include(packages().withNameLike(glob))
                include(packages().recursive())
                writeTo(file(project.layout.buildDirectory.file("$full.${project.name}.$plantUMLSuffix")))
            }
                    as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
        )
    }
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
