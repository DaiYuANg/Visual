import com.coditory.gradle.manifest.ManifestPlugin
import com.palantir.gradle.gitversion.VersionDetails
import io.freefair.gradle.plugins.lombok.LombokPlugin
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import io.gitlab.plunts.gradle.plantuml.plugin.PlantUmlPlugin
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jreleaser.gradle.plugin.JReleaserPlugin
import java.nio.charset.StandardCharsets

plugins {
    `java-library`
    checkstyle
    jacoco
    idea
    java
    alias(libs.plugins.gitVersion)
    alias(libs.plugins.lombok)
    alias(libs.plugins.plantuml)
    alias(libs.plugins.dokka)
    alias(libs.plugins.jmh)
    alias(libs.plugins.jreleaser)
    alias(libs.plugins.dependencycheck)
    id("de.undercouch.download") version "5.5.0"
    id("com.coditory.manifest") version "0.2.6"
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
val gitVersion: groovy.lang.Closure<String> by extra
val versionDetails: groovy.lang.Closure<VersionDetails> by extra
val details = versionDetails()
val javaCompileArg = listOf("-Xlint:all", "-Xmaxwarns", "-g")
subprojects {
    apply<BasePlugin>()
    if (project.name != "website") {
        apply {
            apply<IdeaPlugin>()
            apply<LombokPlugin>()
            apply<JavaLibraryPlugin>()
            apply<PlantUmlPlugin>()
            apply<FormatterPlugin>()
            apply<DokkaPlugin>()
            apply<JReleaserPlugin>()
            apply<ManifestPlugin>()
        }

        dependencies {
            implementation(rootProject.libs.jetbrainsAnnotation)
            implementation(rootProject.libs.slf4j)
            implementation(rootProject.libs.slf4jJdkPlatform)
            implementation(rootProject.libs.logback)
            implementation(rootProject.libs.guava)
            annotationProcessor(rootProject.libs.lombokMapstructBinding)
            implementation(rootProject.libs.mapstruct)
            annotationProcessor(rootProject.libs.mapstructProcessor)
            testImplementation(rootProject.libs.junitBom)
            testImplementation(platform(rootProject.libs.junitBom))
            testImplementation(rootProject.libs.junitJuiter)
            testImplementation(rootProject.libs.junitApi)
            testImplementation(rootProject.libs.junitEngine)
            testImplementation(rootProject.libs.junitPlatformSuite)
            testImplementation(rootProject.libs.junitPerf)
            testImplementation(platform(rootProject.libs.testcontainersBom))
            testImplementation(rootProject.libs.testcontainers)
            testImplementation(rootProject.libs.testcontainersJunit)
            testImplementation(rootProject.libs.mockitoCore)
            testImplementation(rootProject.libs.mockitoJunit)
        }

        group = "org." + project.name.replace("-", ".")
        tasks.compileJava {
            doFirst {
                println("AnnotationProcessorPath for $name is ${options.annotationProcessorPath?.files}")
            }
//            options.forkOptions.jvmArgs!!.add("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005")
            options.encoding = StandardCharsets.UTF_8.name()
            options.compilerArgs.addAll(javaCompileArg)
            options.isFork = true
            options.isDebug = true
            options.isIncremental = true
        }

        tasks.jar {
            enabled = true
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
//            manifest.attributes["Version"] = version
//            manifest.attributes["Git-Hash"] = details.gitHashFull
//            manifest.attributes["Last-Tag"] = details.lastTag
//            manifest.attributes["Branch"] = details.branchName
        }
        manifest {
            buildAttributes = true
            implementationAttributes = true
            scmAttributes = true
            attributes = mapOf(
                "Version" to version,
                "Git-Hash" to details.gitHashFull,
                "Last-Tag" to details.lastTag,
                "Branch" to details.branchName
            )
        }

        tasks.test {
            useJUnitPlatform()
            maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
            forkEvery = 100
            reports.html.required = false
            reports.junitXml.required = false
        }

        java {
            sourceCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
            targetCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
        }

        classDiagrams {
            val glob = "${project.group}.**"
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
}

