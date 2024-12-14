import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  antlr
  alias(libs.plugins.javamodularity)
}

group = "org.visual.database"

apply<ModulePlugin>()

dependencies {
  testImplementation(libs.testcontainers.mysql)
  testImplementation(libs.testcontainers.postgresql)
  testImplementation(libs.testcontainers.mssqlserver)

  implementation(projects.visualCore)
  implementation(libs.apache.common.text)
  implementation(libs.mysql)
  implementation(libs.sqlite)
  implementation(libs.postgres)

  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mySQL)
  implementation(libs.schemacrawler.postgreSQL)
  antlr(libs.antlr)

  implementation(libs.avaje.inject)
  annotationProcessor(libs.avaje.inject.generator)
  testImplementation(libs.avaje.inject.test)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> { addModules = listOf("schemacrawler") }
}

tasks.generateGrammarSource {
  maxHeapSize = "1G"
  arguments = arguments + listOf("-visitor", "-long-messages")
  outputDirectory = File("${project.projectDir}/build/generated/antlr/main/java/org/visual/grammar")
}