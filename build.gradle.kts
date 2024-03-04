import com.coditory.gradle.manifest.ManifestPlugin
import com.coditory.gradle.manifest.ManifestPluginExtension
import com.palantir.gradle.gitversion.VersionDetails
import io.freefair.gradle.plugins.lombok.LombokPlugin
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import io.gitlab.plunts.gradle.plantuml.plugin.PlantUmlPlugin
import org.jetbrains.dokka.gradle.DokkaPlugin
import java.nio.charset.StandardCharsets

plugins {
  checkstyle
  jacoco
  idea
  application
  java
  alias(libs.plugins.gitVersion)
  alias(libs.plugins.lombok)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.dokka)
  alias(libs.plugins.jmh)
  alias(libs.plugins.jreleaser)
  alias(libs.plugins.versionCheck)
  alias(libs.plugins.dependencycheck)
  alias(libs.plugins.javafx)
  alias(libs.plugins.fatjar)
  alias(libs.plugins.spotless)
  `kotlin-project`
//    alias(libs.plugins.jlink)
  alias(libs.plugins.download)
  alias(libs.plugins.manifest)
  alias(libs.plugins.dotenv)
}
val plantUMLSuffix = "puml"
val gitVersion: groovy.lang.Closure<String> by extra
val versionDetails: groovy.lang.Closure<VersionDetails> by extra
val details = versionDetails()
var javaCompileArg: List<String> = if (env.MODE.value == "debug") {
  listOf()
} else {
  listOf("-g:none", "-02")
}
allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
  project.version = details.gitHash
}

spotless {
  encoding = StandardCharsets.UTF_8
  format("misc") {
    target("*.md", ".gitignore", "gradle/libs.versions.toml")
    indentWithSpaces(identWidth)
    trimTrailingWhitespace()
    targetExclude("**/node_modules/**/*")
    endWithNewline()
  }
  java {
    target("**/src/**/*.java")
    importOrder()
    googleJavaFormat().formatJavadoc(true)
    indentWithSpaces(2)
    removeUnusedImports()
    formatAnnotations()
      .addTypeAnnotation("Empty")
      .addTypeAnnotation("NonEmpty")
      .removeTypeAnnotation("Localized")
  }
  kotlin {
    target("**/src/**/*.kt")
    ktfmt()
    indentWithSpaces(identWidth)
  }
  kotlinGradle {
    target("**/src/**/*.gradle.kts", "./*.gradle.kts")
    ktfmt()
    ktlint()
      .setEditorConfigPath("$projectDir/.editorconfig") // sample unusual placement
    indentWithSpaces(identWidth)
  }
  json {
    targetExclude("**/node_modules/**/*")
    target("**/src/**/*.json")
    jackson()
  }
  yaml {
    targetExclude("**/node_modules/**/*", "pnpm-lock.yaml")
    target("**/src/**/*.yaml") // you have to set the target manually
    prettier() // has its own section below
    jackson()
  }
  sql {
    target("**/src/**/*.sql") // have to set manually
    dbeaver()
  }
  antlr4 {
    target("**/src/main/antlr4/**/*.g4")
    antlr4Formatter()
  }
}

javafx {
  version = libs.versions.javafxVersion.get()
  modules(*javafxModules.toTypedArray())
  configurations = arrayOf(
    "implementation",
    "testImplementation"
  )
}

subprojects {
  if (project.name != "website") {
    apply {
      apply<LombokPlugin>()
      apply<JavaLibraryPlugin>()
      apply<PlantUmlPlugin>()
      apply<DokkaPlugin>()
      apply<ManifestPlugin>()
    }

    project.dependencies {
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
    project.tasks.compileJava {
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

    project.tasks.jar {
      enabled = true
      duplicatesStrategy = DuplicatesStrategy.INCLUDE
      manifest.attributes["Version"] = version
      manifest.attributes["Git-Hash"] = details.gitHashFull
      manifest.attributes["Last-Tag"] = details.lastTag
      manifest.attributes["Branch"] = details.branchName
    }
    project.extensions.configure(ManifestPluginExtension::class.java) {
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

    project.tasks.test {
      useJUnitPlatform()
      maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
      forkEvery = 100
      reports.html.required = false
      reports.junitXml.required = false
    }

    project.java {
      sourceCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
      targetCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
    }

    project.classDiagrams {
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

dependencies {
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.avajeValidaor)
  annotationProcessor(libs.avajeValidaorCodegen)
  implementation(rootProject.libs.avajeInject)
  annotationProcessor(rootProject.libs.avajeInjectGenerator)
  implementation(rootProject.libs.gestaltConfig)
  implementation(rootProject.libs.gestaltToml)
  testImplementation(rootProject.libs.avajeInjectTest)
  implementation(rootProject.libs.picocli)
  annotationProcessor(rootProject.libs.picocliCodegen)
  implementation(projects.ui.visualI18n)
  implementation(projects.module.visualGit)
  implementation(projects.module.visualShared)
  implementation(projects.ui.visualDebugger)
  implementation(projects.ui.visualComponent)
  implementation(projects.ui.visualGraphEditor)
  implementation(libs.picocli)
}
