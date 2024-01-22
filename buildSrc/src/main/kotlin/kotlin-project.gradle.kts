import org.gradle.kotlin.dsl.`java-base`
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    kotlin("jvm")
    `java-library`
}

java{
    modularity.inferModulePath.set(true)
}
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)

tasks.compileJava {
    options.compilerArgumentProviders.add(
        CommandLineArgumentProvider {
            // Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
            listOf("--patch-module", "$group=${sourceSets["main"].output.asPath}")
        })
}