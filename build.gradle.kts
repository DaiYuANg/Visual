import com.xenoterracide.gradle.semver.GitMetadataExtension
import java.nio.charset.StandardCharsets

plugins {
  `java-library`
  alias(libs.plugins.dotenv)
  alias(libs.plugins.versionCheck)
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