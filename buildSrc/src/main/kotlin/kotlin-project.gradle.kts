import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  `java-library`
  kotlin("plugin.lombok")
  kotlin("plugin.noarg")
  kotlin("plugin.allopen")
}

java {
  modularity.inferModulePath.set(true)
}

val rootLibs = rootLibs(project)
dependencies {
  implementation(rootLibs.kotlinLogging)
}
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)

tasks.compileJava {
  options.compilerArgumentProviders.add(
    CommandLineArgumentProvider {
      listOf("--patch-module", "$group=${sourceSets["main"].output.asPath}")
    },
  )
}

kotlin {
  jvmToolchain(rootLibs(project).versions.jdk.get().toInt())
}