import com.xenoterracide.gradle.semver.GitMetadataExtension
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import java.nio.charset.StandardCharsets

plugins {
  java
  application
  antlr
  alias(libs.plugins.lombok)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.javafx)
  alias(libs.plugins.ebean)
  alias(libs.plugins.versionCheck)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.semver)
  alias(libs.plugins.shadow)
  alias(libs.plugins.spotless)
  alias(libs.plugins.jlink)
  alias(libs.plugins.graalvm)
}

val git: GitMetadataExtension = semver.git

allprojects {
  repositories {
    mavenCentral()
    mavenLocal()
    maven { setUrl("https://jitpack.io") }
    gradlePluginPortal()
    google()
  }
}

val mainClassPath = "org.visual.VisualApplication"
val mainModule = "org.visual"

application {
  mainClass.set(mainClassPath)
  mainModule.set("org.visual")
}

dependencies {
  implementation(libs.fastutil)
  implementation(libs.mutiny)
  implementation(libs.mutiny.vertx)
  implementation(libs.vertx.web)
  implementation(libs.mutiny.vertx.web.client)
  implementation(libs.vertx.core)
  implementation(libs.mutiny.vertx.web)
  implementation(libs.vertx.web.client)
  testImplementation(libs.vertx.junit5)
  testImplementation(libs.dataFaker)
  implementation(libs.jgrapht)
  implementation(libs.eclipse.collections)
  implementation(libs.eclipse.collections.api)
  implementation(libs.atlantafx)
  implementation(libs.controlfx)
  implementation(libs.guava)
  implementation(libs.vavr)
  implementation(libs.devicons)
  implementation("com.github.Dansoftowner:jSystemThemeDetector:3.9.1") {
    exclude(group = "net.java.dev.jna", module = "jna")
  }
  implementation(libs.oshi)
  implementation(libs.caffine)

  implementation(libs.ikonliJavafx)
  implementation(libs.materialIcons)
  implementation(libs.fluentuiIcon)
  implementation(libs.fontawesome5)

  implementation(libs.logback)

  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)

  implementation(libs.picocli)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.recordBuilderCore)
  annotationProcessor(libs.recordBuilderProcess)

  testImplementation(libs.javafxUnitTest)

  testImplementation(libs.avajeInjectTest)

  runtimeOnly(libs.h2)
  implementation(libs.ebean.api)
  implementation(libs.ebean.core)
  testImplementation(libs.ebean.test)
  implementation(libs.ebean.annotation)
  implementation(libs.ebean.query.bean)
  implementation(libs.ebean.data.source)
  implementation(libs.ebean.platform.h2)
  implementation(libs.ebean.migration)
  annotationProcessor(libs.ebean.query.bean.generator)

  implementation(libs.jgit)
  implementation(libs.jgit.lfs)
  implementation(libs.jgit.ssh)
  implementation(libs.jgit.http)
  testImplementation(libs.jgit.junit)

  compileOnly(libs.jetbrainsAnnotation)
  implementation(libs.apacheCommonIO)
  implementation(libs.directories)
  implementation(libs.vavr)
  implementation(libs.gestalt.toml)
  implementation(libs.gestalt.yaml)
  implementation(libs.gestalt)
  implementation(libs.slf4j)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.slf4jJulBridage)
  implementation(libs.avaje.validaor)
  implementation(libs.avaje.validaor.constraints)
  annotationProcessor(libs.avaje.validaor.codegen)
  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.processor)

  testImplementation(enforcedPlatform(libs.junitBom))
  testImplementation(libs.junitApi)
  testImplementation(libs.junitJuiter)
  testImplementation(libs.junitPerf)
  testImplementation(libs.junitEngine)
  testImplementation(libs.mockitoCore)
  testImplementation(libs.mockitoJunit)

  implementation(libs.apacheCommonLang3)
  implementation(libs.apacheCommonPool)

  implementation(libs.jackson.core)
  implementation(libs.jackson.data.type.guava)
  implementation(libs.jackson.data.type.jsonP)
  implementation(libs.jackson.data.type.xml)
  implementation(libs.jackson.data.type.eclipse.collection)
  implementation(libs.jackson.data.type.pcollection)
  implementation(libs.jackson.databind)
  implementation(libs.jackson.annotations)
  implementation(libs.eclipse.parsson)

  implementation(libs.jsh)

  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mySQL)
  implementation(libs.schemacrawler.postgreSQL)
  implementation(libs.schemacrawler.sql.server)

  implementation(libs.fury.core)
  implementation(libs.fury.format)

  compileOnly(libs.avaje.spi.service)
  annotationProcessor(libs.avaje.spi.service)
  antlr(libs.antlr)

  testImplementation(enforcedPlatform(libs.testcontainers.bom))
  testImplementation(libs.testcontainers.junit)
  testImplementation(libs.testcontainers.mysql)
  testImplementation(libs.testcontainers.postgresql)
  testImplementation(libs.testcontainers.mssqlserver)
}

javafx {
  modules(*javafxModules.toTypedArray())
  configurations =
    arrayOf(
      IMPLEMENTATION,
      TEST_IMPLEMENTATION,
    )
}

val jdkVersion: String = libs.versions.jdk.get()

tasks.compileJava {
  // options.forkOptions.jvmArgs!!.add("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005")
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

manifest {
  buildAttributes = true
  implementationAttributes = true
  scmAttributes = true
  attributes =
    mapOf(
      VERSION_KEY to version,
      GIT_HASH_KEY to git.commit,
      LATEST_TAG_KEY to git.latestTag,
      BRANCH_KEY to git.branch,
      MAIN_CLASS_KEY to mainClassPath,
    )
}

graalvmNative {
  binaries {
    named("main") {
      mainClass.set(mainClassPath)
      sharedLibrary.set(false)
      buildArgs(
        "--initialize-at-build-time=org.apache.sshd.sftp.client.fs.SftpFileSystemProvider",
      )
    }
  }
}

tasks.shadowJar {
  minimize()
  mergeServiceFiles()
  val jar: Jar by tasks
  manifest.inheritFrom(jar.manifest)
}

tasks.withType(AbstractArchiveTask::class.java) {
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true
}

jlink {
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  mainClass.set(mainClassPath)
  moduleName.set(mainModule)
  mergedModule {}
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

tasks.generateGrammarSource {
  maxHeapSize = "1G"
  arguments = arguments + listOf("-visitor", "-long-messages")
  outputDirectory = File("${project.projectDir}/build/generated/antlr/main/java/org/visual/grammar")
}

ebean {
  debugLevel = 1
  queryBeans = true
}