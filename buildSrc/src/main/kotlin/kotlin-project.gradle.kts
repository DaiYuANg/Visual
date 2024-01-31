import org.gradle.kotlin.dsl.`java-base`
import org.gradle.kotlin.dsl.kotlin
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

dependencies{
    implementation ("io.github.oshai:kotlin-logging-jvm:5.1.0")
}
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)

tasks.compileJava {
    options.compilerArgumentProviders.add(
        CommandLineArgumentProvider {
            listOf("--patch-module", "$group=${sourceSets["main"].output.asPath}")
        })
}

kotlin {
    jvmToolchain(rootLibs(project).versions.jdk.get().toInt())
}