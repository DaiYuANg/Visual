plugins {
  java
  application
  antlr
  alias(libs.plugins.javafx)
  alias(libs.plugins.ebean)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.jlink)
  alias(libs.plugins.graalvm)
  alias(libs.plugins.shadow)
  alias(libs.plugins.sass)
}

apply<ModulePlugin>()

val mainClassPath = "org.visual.app.VisualApplication"
val mainModule = "org.visual.app"

application {
  mainClass.set(mainClassPath)
  applicationDefaultJvmArgs += devJvmArguments + hazelcastArguments
}

group = "org.visual.app"

dependencies {
  implementation(projects.visualCore)
  implementation(projects.visualConfig)
  implementation(projects.visualDatabase)
  implementation(projects.visualApi)
  implementation(libs.fastutil)
  implementation(libs.vertx.web)
  implementation(libs.mutiny.vertx.web.client)
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
  implementation(libs.caffine)
  implementation(libs.theme.detector) {
    exclude(group = "net.java.dev.jna", module = "jna")
    exclude(group = "net.java.dev.jna", module = "jna-platform")
    exclude(group = "com.github.oshi", module = "oshi-core")
  }
  implementation(libs.ikonliJavafx)
  implementation(libs.materialIcons)
  implementation(libs.fluentuiIcon)
  implementation(libs.fontawesome5)
  implementation(libs.simpleicon)

  implementation(libs.plantuml)

  implementation(libs.logback)

  implementation(libs.picocli)
  annotationProcessor(libs.picocliCodegen)

  testImplementation(libs.javafxUnitTest)

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

  implementation(libs.directories)
  implementation(libs.vavr)

  implementation(libs.slf4jJdkPlatform)
  implementation(libs.slf4jJulBridage)

  implementation(libs.apache.common.io)
  implementation(libs.apache.common.db.util)
  implementation(libs.apache.common.lang3)
  implementation(libs.apache.common.pool)
  implementation(libs.apache.common.text)

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

  implementation("org.webjars.npm:fontsource-variable__jetbrains-mono:5.0.6")

  implementation(libs.fury.core)
  implementation(libs.fury.format)

  compileOnly(libs.avaje.spi.service)
  annotationProcessor(libs.avaje.spi.service)
  antlr(libs.antlr)
}

javafx {
  modules(*javafxModules.toTypedArray())
  version = "23"
  configurations =
    arrayOf(
      IMPLEMENTATION,
      TEST_IMPLEMENTATION,
    )
}

manifest {
  buildAttributes = true
  implementationAttributes = true
  scmAttributes = true
  attributes =
    mapOf(
      VERSION_KEY to version,
      GIT_HASH_KEY to rootProject.extra[GIT_HASH_KEY],
      LATEST_TAG_KEY to rootProject.extra[LATEST_TAG_KEY],
      BRANCH_KEY to rootProject.extra[BRANCH_KEY],
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
  mergeServiceFiles()
  val jar: Jar by tasks
  manifest.inheritFrom(jar.manifest)
}

tasks.withType(AbstractArchiveTask::class.java) {
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true
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

jlink {
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  mainClass.set(mainClassPath)
  moduleName.set(mainModule)
  //  mergedModule { uses("org.visual.api.Lifecycle") }
}

tasks.compileJava { dependsOn(tasks.compileSass) }

tasks.compileSass {
  style = compressed
  sourceMap = embed
  sourceDir = layout.projectDirectory.file("src/main/sass").asFile
  outputDir =
    layout.buildDirectory
      .file("resources/main/style")
      .get()
      .asFile
}