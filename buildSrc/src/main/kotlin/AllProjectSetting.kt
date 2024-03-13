import com.xenoterracide.gradle.semver.SemverExtension
import com.xenoterracide.gradle.semver.SemverPlugin
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import io.gitlab.plunts.gradle.plantuml.plugin.PlantUmlPlugin
import java.nio.charset.StandardCharsets
import name.remal.gradle_plugins.lombok.LombokPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.closureOf
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.dokka.gradle.DokkaPlugin

class AllProjectSetting : Plugin<Project> {
  override fun apply(target: Project) {
    val rootLib = rootLibs(target)
    target.allprojects {
      apply {
        apply<LombokPlugin>()
        apply<PlantUmlPlugin>()
        apply<DokkaPlugin>()
        apply<SemverPlugin>()
      }

      val semver = target.extensions.getByType(SemverExtension::class.java)
      val git = semver.git

      group = "org." + project.name.replace("-", ".")
      project.tasks.withType(JavaCompile::class.java) {
        doFirst {
          println("AnnotationProcessorPath for $name is ${options.annotationProcessorPath?.files}")
        }
        //
        // options.forkOptions.jvmArgs!!.add("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005")
        options.encoding = StandardCharsets.UTF_8.name()
        options.isFork = true
        options.isDebug = true
        options.isIncremental = true
      }

      project.dependencies {
        add(COMPILE_ONLY, rootLib.jetbrainsAnnotation)
        add(IMPLEMENTATION, rootLib.slf4j)
        add(IMPLEMENTATION, rootLib.slf4jJdkPlatform)
        add(IMPLEMENTATION, rootLib.mapstruct)
        add(ANNOTATION_PROCESSOR, rootLib.mapstructProcessor)
        add(TEST_IMPLEMENTATION, platform(rootLib.junitBom))
        add(TEST_IMPLEMENTATION, rootLib.junitJuiter)
        add(TEST_IMPLEMENTATION, rootLib.junitApi)
        add(TEST_IMPLEMENTATION, rootLib.junitEngine)
        add(TEST_IMPLEMENTATION, rootLib.junitPlatformSuite)
        add(TEST_IMPLEMENTATION, rootLib.junitPerf)
        add(TEST_IMPLEMENTATION, platform(rootLib.testcontainersBom))
        add(TEST_IMPLEMENTATION, rootLib.testcontainers)
        add(TEST_IMPLEMENTATION, rootLib.testcontainersJunit)
        add(TEST_IMPLEMENTATION, rootLib.mockitoCore)
        add(TEST_IMPLEMENTATION, rootLib.mockitoJunit)
      }

      project.version = "${git.branch}-${git.commitShort}"

      project.tasks.withType(Jar::class.java) {
        enabled = true
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
      }

      project.tasks.withType(Test::class.java) {
        useJUnitPlatform()
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
        forkEvery = 100
        reports.html.required.set(false)
        reports.junitXml.required.set(false)
        workingDir = projectDir
      }

      project.extensions.configure<JavaPluginExtension> {
        modularity.inferModulePath.set(true)
        sourceCompatibility = JavaVersion.toVersion(rootLib.versions.jdk.get())
        targetCompatibility = JavaVersion.toVersion(rootLib.versions.jdk.get())
      }

      project.configure<ClassDiagramsExtension> {
        val glob = "${project.group}.**"
        val internal = "internal_class_diagram"
        val full = "full_class_diagram"
        @Suppress("UNCHECKED_CAST")
        diagram(
            internal,
            closureOf<ClassDiagramsExtension.ClassDiagram> {
              include(packages().withNameLike(glob))
              writeTo(
                  file(
                      project.layout.buildDirectory.file(
                          "$internal.${project.name}.$PLANTUML_SUFFIX")))
            }
                as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
        )
        @Suppress("UNCHECKED_CAST")
        diagram(
            full,
            closureOf<ClassDiagramsExtension.ClassDiagram> {
              include(packages().withNameLike(glob))
              include(packages().recursive())
              writeTo(
                  file(
                      project.layout.buildDirectory.file("$full.${project.name}.$PLANTUML_SUFFIX")))
            }
                as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
        )
      }
    }
  }
}
