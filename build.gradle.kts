import com.xenoterracide.gradle.semver.GitMetadataExtension
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import java.nio.charset.StandardCharsets

plugins {
  `java-library`
  alias(libs.plugins.dotenv)
  alias(libs.plugins.versionCheck)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.semver)
  alias(libs.plugins.spotless)
}

val git: GitMetadataExtension = semver.git

extra[BRANCH_KEY] = git.branch

extra[GIT_HASH_KEY] = git.commit

extra[LATEST_TAG_KEY] = git.latestTag

allprojects {
  repositories {
    mavenCentral()
    mavenLocal()
    maven { setUrl("https://jitpack.io") }
    gradlePluginPortal()
    google()
  }
}

subprojects {
  if (project.name != "website") {
    apply<JavaLibraryPlugin>()
    val jdkVersion: String =
      rootProject.libs.versions.jdk
        .get()

    tasks.compileJava {
      options.encoding = StandardCharsets.UTF_8.name()
      options.isFork = true
      options.isDebug = true
      options.isIncremental = true
    }

    tasks.jar { duplicatesStrategy = DuplicatesStrategy.INCLUDE }

    tasks.test {
      useJUnitPlatform()
      maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
      forkEvery = 100
      reports.html.required.set(false)
      reports.junitXml.required.set(false)
    }

    java {
      modularity.inferModulePath.set(true)
      sourceCompatibility = JavaVersion.toVersion(jdkVersion)
      targetCompatibility = JavaVersion.toVersion(jdkVersion)
    }
  }
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
      writeTo(
        file(project.layout.buildDirectory.file("$internal.${project.name}.$PLANTUML_SUFFIX")),
      )
    }
      as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
  )
  @Suppress("UNCHECKED_CAST")
  diagram(
    full,
    closureOf<ClassDiagramsExtension.ClassDiagram> {
      include(packages().withNameLike(glob))
      include(packages().recursive())
      writeTo(file(project.layout.buildDirectory.file("$full.${project.name}.$PLANTUML_SUFFIX")))
    }
      as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
  )
}

spotless {
  java {
    target("*.java")
    importOrder()
    removeUnusedImports()
    indentWithSpaces(2)
    formatAnnotations()
      .addTypeAnnotation("Empty")
      .addTypeAnnotation("NonEmpty")
      .removeTypeAnnotation("Localized")
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktfmt()
    ktlint()
      .setEditorConfigPath(
        "${rootProject.layout.projectDirectory}/.editorconfig",
      )
    indentWithSpaces(IDENT_WIDTH)
  }
}