import com.xenoterracide.gradle.semver.GitMetadataExtension
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

plugins {
  java
  application
  antlr
  alias(libs.plugins.lombok)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.javafx)
  alias(libs.plugins.hibernate)
  alias(libs.plugins.kotlinJvm)
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
    mavenLocal()
    mavenCentral()
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
  implementation(libs.logback)
  implementation(libs.fastutil)
  implementation(libs.mutiny)
  implementation(libs.mutiny.vertx)
  implementation(libs.vertx.web)
  implementation(libs.mutiny.vertx.web.client)
  implementation(libs.vertx.core)
  implementation(libs.mutiny.vertx.web)
  implementation(libs.vertx.web.client)
  testImplementation(libs.vertx.junit5)
  implementation(libs.dataFaker)
  implementation(libs.jgrapht)
  implementation(libs.eclipseCollections)
  implementation(libs.eclipseCollectionsAPI)
  implementation(libs.eclipseCollectionsForkjoin)
  implementation(libs.atlantafx)
  implementation(libs.controlfx)
  implementation(libs.guava)
  implementation(libs.vavr)
  implementation(libs.devicons)

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
  implementation(libs.slf4jJulBridage)
  implementation(libs.recordBuilderCore)
  annotationProcessor(libs.recordBuilderProcess)

  testImplementation(libs.javafxUnitTest)

  testImplementation(libs.avajeInjectTest)

  implementation(enforcedPlatform(libs.hibernatePlatform))
  runtimeOnly(libs.h2)
  implementation(libs.hibernateCore)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateJfr)
  implementation(libs.hibernateEnvers)
  implementation(libs.hibernateValidator)
  implementation(libs.eclipse.parsson)
  implementation(libs.slf4j)
  implementation(libs.hibernateGraalvm)
  testImplementation(libs.hibernateTesting)
  implementation(libs.bytebuddy)
  implementation(libs.jakarta.cdi.api)
  implementation(libs.jakarta.xml.bind)
  implementation(libs.jboss.logging)

  implementation(libs.apacheCommonIO)
  implementation(libs.jakartaPersistenceAPI)

  annotationProcessor(libs.hibernateProcessor)
  annotationProcessor(libs.hibernateJpamodelgen)

  implementation(libs.jgit)
  implementation(libs.jgit.lfs)
  implementation(libs.jgit.ssh)
  implementation(libs.jgit.http)
  testImplementation(libs.jgit.junit)
  val queryDSLApt = variantOf(libs.queryDslApt) { classifier(jakarta) }
  // queryDSL
  compileOnly(queryDSLApt)
  annotationProcessor(queryDSLApt)
  implementation(libs.queryDslJPA)
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslCollection)
  implementation(libs.queryDslSpatial)
  annotationProcessor(libs.jakartaPersistenceAPI)

  compileOnly(libs.jetbrainsAnnotation)
  implementation(libs.apacheCommonIO)
  implementation(libs.directories)
  implementation(libs.guava)
  implementation(libs.vavr)
  implementation(libs.gestaltToml)
  implementation(libs.gestaltConfig)
  implementation(libs.kotlinLogging)
  implementation(libs.slf4j)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.slf4jJulBridage)
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
  implementation(libs.jackson.data.type.eclipse.collection)
  implementation(libs.jackson.data.type.pcollection)
  implementation(libs.jackson.databind)
  implementation(libs.jackson.annotations)

  implementation(libs.jsh)

  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mySQL)
  implementation(libs.schemacrawler.postgreSQL)
  implementation(libs.schemacrawler.sql.server)

  implementation(libs.fury.core)
  implementation(libs.fury.format)

  antlr(libs.antlr)

  //  implementation(libs.graalvm.polyglot)
  //  implementation(libs.graalvm.polyglot.js)
  //  implementation(libs.graalvm.polyglot.python)
  //  implementation(libs.graalvm.polyglot.profile)
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

kotlin { jvmToolchain(jdkVersion.toInt()) }

tasks.withType(KotlinCompile::class) {
  dependsOn(tasks.generateGrammarSource)
  val compileJava: JavaCompile by tasks
  destinationDirectory.set(compileJava.destinationDirectory)
}

val sourceSets = extensions.getByType(SourceSetContainer::class)

tasks.withType(JavaCompile::class).configureEach {
  options.compilerArgumentProviders.add(
    CommandLineArgumentProvider {
      listOf("--patch-module", "$group=${sourceSets["main"].output.asPath}")
    },
  )
}

tasks.compileJava {
  //
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
  //  enableCds()
  mainClass.set(mainClassPath)
}

spotless {
  java {
    target("*.java")
    importOrder()
    removeUnusedImports()
    //    googleJavaFormat().formatJavadoc(true)
    //    indentWithSpaces(2)
    //
    //    formatAnnotations()
    //      .addTypeAnnotation("Empty")
    //      .addTypeAnnotation("NonEmpty")
    //      .removeTypeAnnotation("Localized")
  }
  kotlin {
    target("*.kt")
    ktfmt()
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