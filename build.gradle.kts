import com.coditory.gradle.manifest.ManifestPlugin
import com.coditory.gradle.manifest.ManifestPluginExtension
import com.palantir.gradle.gitversion.VersionDetails
import io.freefair.gradle.plugins.lombok.LombokPlugin
import io.gitlab.plunts.gradle.plantuml.plugin.ClassDiagramsExtension
import io.gitlab.plunts.gradle.plantuml.plugin.PlantUmlPlugin
import org.jetbrains.dokka.gradle.DokkaPlugin
import java.nio.charset.StandardCharsets

plugins {
  checkstyle
  jacoco
  idea
  pmd
  application
  java
  alias(libs.plugins.gitVersion)
  alias(libs.plugins.lombok)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.dokka)
  alias(libs.plugins.jmh)
  alias(libs.plugins.jreleaser)
  alias(libs.plugins.versionCheck)
  alias(libs.plugins.dependencycheck)
  alias(libs.plugins.javafx)
  alias(libs.plugins.fatjar)
  alias(libs.plugins.spotless)
  alias(libs.plugins.spotbugs)
  alias(libs.plugins.graalvm)
  `kotlin-project`
//  alias(libs.plugins.jlink)
  alias(libs.plugins.download)
  alias(libs.plugins.manifest)
  alias(libs.plugins.dotenv)
//  alias(libs.plugins.javamodularity)
}
val plantUMLSuffix = "puml"
val gitVersion: groovy.lang.Closure<String> by extra
val versionDetails: groovy.lang.Closure<VersionDetails> by extra
val details = versionDetails()
val mainClassPath = "org.visual.VisualApplication"
var javaCompileArg: List<String> =
  if (env.MODE.value == "debug") {
    listOf()
  } else {
    listOf("-g:none", "-02")
  }

idea.project.ipr {
  withXml {
    asElement()
      .firstElement { tagName == "component" && getAttribute("name") == "VcsDirectoryMappings" }
      .firstElement { tagName == "mapping" }
      .setAttribute("vcs", "Git")
  }
//  TODO set idea disable ana https://stackoverflow.com/questions/16369749/how-to-disable-pre-commit-code-analysis-for-git-backed-projects-using-intellij-i
  withXml {
    asElement()
      .firstElement { tagName == "component" && getAttribute("name") == "VcsManagerConfiguration" }
  }
}
allprojects {
  apply<JavaLibraryPlugin>()
  repositories {
    mavenLocal()
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    gradlePluginPortal()
    google()
  }
}

spotless {
  encoding = StandardCharsets.UTF_8
  format("misc") {
    target("*.md", ".gitignore", "gradle/libs.versions.toml")
    indentWithSpaces(identWidth)
    trimTrailingWhitespace()
    targetExclude("**/node_modules/**/*")
    endWithNewline()
  }
  java {
    target("**/src/**/*.java")
    importOrder()
    googleJavaFormat().formatJavadoc(true)
    indentWithSpaces(2)
    removeUnusedImports()
    formatAnnotations()
      .addTypeAnnotation("Empty")
      .addTypeAnnotation("NonEmpty")
      .removeTypeAnnotation("Localized")
  }
  kotlin {
    target("**/src/**/*.kt")
    ktfmt()
    indentWithSpaces(identWidth)
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktfmt()
    ktlint()
      .setEditorConfigPath("$projectDir/.editorconfig") // sample unusual placement
    indentWithSpaces(identWidth)
  }
  json {
    targetExclude("**/node_modules/**/*")
    target("**/src/**/*.json")
    jackson()
  }
  yaml {
    targetExclude("**/node_modules/**/*", "pnpm-lock.yaml")
    target("**/src/**/*.yaml") // you have to set the target manually
    prettier() // has its own section below
    jackson()
  }
  sql {
    target("**/src/**/*.sql") // have to set manually
    dbeaver()
  }
  antlr4 {
    target("**/src/main/antlr4/**/*.g4")
    antlr4Formatter()
  }
}

javafx {
  version = libs.versions.javafxVersion.get()
  modules(*javafxModules.toTypedArray())
  configurations =
    arrayOf(
      "implementation",
      "testImplementation",
    )
}

allprojects {
  if (project.name != "website") {
    apply {
      apply<LombokPlugin>()
      apply<PlantUmlPlugin>()
      apply<DokkaPlugin>()
      apply<ManifestPlugin>()
      apply<KotlinProjectPlugin>()
    }

    group = "org." + project.name.replace("-", ".")
    project.tasks.compileJava {
      doFirst {
        println("AnnotationProcessorPath for $name is ${options.annotationProcessorPath?.files}")
      }
//    options.forkOptions.jvmArgs!!.add("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005")
      options.encoding = StandardCharsets.UTF_8.name()
      options.compilerArgs.addAll(javaCompileArg)
      options.isFork = true
      options.isDebug = true
      options.isIncremental = true
    }
    project.dependencies {
      compileOnly(rootProject.libs.jetbrainsAnnotation)
      implementation(rootProject.libs.slf4j)
      implementation(rootProject.libs.slf4jJdkPlatform)
      implementation(rootProject.libs.guava)
      annotationProcessor(rootProject.libs.lombokMapstructBinding)
      implementation(rootProject.libs.mapstruct)
      annotationProcessor(rootProject.libs.mapstructProcessor)
      testImplementation(platform(rootProject.libs.junitBom))
      testImplementation(rootProject.libs.junitJuiter)
      testImplementation(rootProject.libs.junitApi)
      testImplementation(rootProject.libs.junitEngine)
      testImplementation(rootProject.libs.junitPlatformSuite)
      testImplementation(rootProject.libs.junitPerf)
      testImplementation(platform(rootProject.libs.testcontainersBom))
      testImplementation(rootProject.libs.testcontainers)
      testImplementation(rootProject.libs.testcontainersJunit)
      testImplementation(rootProject.libs.mockitoCore)
      testImplementation(rootProject.libs.mockitoJunit)
    }
    project.version = "${details.branchName}-${details.gitHash}"

    project.tasks.jar {
      enabled = true
      duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    project.extensions.configure(ManifestPluginExtension::class.java) {
      buildAttributes = true
      implementationAttributes = true
      scmAttributes = true
      attributes =
        mapOf(
          versionKey to version,
          gitHashKey to details.gitHashFull,
          latestTagKey to details.lastTag,
          branchKey to details.branchName,
        )
    }

    project.tasks.test {
      useJUnitPlatform()
      maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
      forkEvery = 100
      reports.html.required = false
      reports.junitXml.required = false
    }

    project.java {
      modularity.inferModulePath.set(true)
      sourceCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
      targetCompatibility = JavaVersion.toVersion(rootProject.libs.versions.jdk.get())
    }

    project.classDiagrams {
      val glob = "${project.group}.**"
      val internal = "internal_class_diagram"
      val full = "full_class_diagram"
      @Suppress("UNCHECKED_CAST")
      diagram(
        internal,
        closureOf<ClassDiagramsExtension.ClassDiagram> {
          include(packages().withNameLike(glob))
          writeTo(file(project.layout.buildDirectory.file("$internal.${project.name}.$plantUMLSuffix")))
        }
          as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
      )

      @Suppress("UNCHECKED_CAST")
      diagram(
        full,
        closureOf<ClassDiagramsExtension.ClassDiagram> {
          include(packages().withNameLike(glob))
          include(packages().recursive())
          writeTo(file(project.layout.buildDirectory.file("$full.${project.name}.$plantUMLSuffix")))
        }
          as groovy.lang.Closure<ClassDiagramsExtension.ClassDiagram>,
      )
    }
  }
}

application {
  applicationDefaultJvmArgs = commonJvmArgs
  mainClass = mainClassPath
}

dependencies {
  implementation(libs.hikariCP)
  implementation(libs.mavenResloverAPI)
  implementation(libs.mavenResloverImpl)
  implementation(libs.mavenResloverJDK21)
  implementation(libs.mavenResloverSupplier)
  implementation(libs.mavenResloverUtil)
  implementation(libs.jacksonCore)
  implementation(libs.jacksonDatabind)
  implementation(libs.gestaltGuice)
  implementation(libs.gestaltKotlin)
  implementation(libs.jacksonAnnotations)
  implementation(rootProject.libs.logback)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.avajeValidaor)
  annotationProcessor(libs.avajeValidaorCodegen)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
  testImplementation(libs.javafxUnitTest)
  testImplementation(rootProject.libs.avajeInjectTest)
  annotationProcessor(libs.picocliCodegen)
  implementation(projects.ui.visualI18n)
  implementation(projects.module.visualGit)
  implementation(projects.module.visualShared)
  implementation(projects.ui.visualDebugger)
  implementation(projects.ui.visualComponent)
  implementation(projects.ui.visualGraphEditor)
  implementation(projects.serialize.visualSerializeApi)
  implementation(projects.serialize.visualSerializeJson)
  implementation(libs.picocli)
  implementation(libs.picocliJline)
}

graalvmNative {
  toolchainDetection.set(true)

  binaries {
    named("main") {
      // Main options
      mainClass.set(mainClassPath) // The main class to use, defaults to the application.mainClass
      debug.set(true) // Determines if debug info should be generated, defaults to false (alternatively add --debug-native to the CLI)
      verbose.set(true) // Add verbose output, defaults to false
      fallback.set(true) // Sets the fallback mode of native-image, defaults to false
      sharedLibrary.set(false) // Determines if image is a shared library, defaults to false if `java-library` plugin isn't included
      quickBuild.set(false) // Determines if image is being built in quick build mode (alternatively use GRAALVM_QUICK_BUILD environment variable, or add --native-quick-build to the CLI)
      richOutput.set(true) // Determines if native-image building should be done with rich output

      // Advanced options
      buildArgs.add("--link-at-build-time") // Passes '--link-at-build-time' to the native image builder options. This can be used to pass parameters which are not directly supported by this extension
//      jvmArgs.addAll(*commonJvmArgs.toTypedArray()) // Passes 'flag' directly to the JVM running the native image builder

      // Runtime options
      runtimeArgs.add("--help") // Passes '--help' to built image, during "nativeRun" task

      useFatJar.set(true) // Instead of passing each jar individually, builds a fat jar
    }
  }
}
// jlink {
//  addExtraDependencies(
//    "javafx",
//    "kotlin",
//    "jackson",
//    "picocli",
//  )
//  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
//  enableCds()
//  launcher {
//    noConsole = true
//    name = convertToCamelCase(project.name)
//    jvmArgs = commonJvmArgs
//  }
//  imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
//  jpackage { appVersion = version.toString() }
//  customImage {
//    jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
//    appModules = listOf(group.toString())
//  }
//  mergedModule {
//    additive = true
//    excludeRequires(
//      "java.compiler",
//      "java.rmi",
//      "java.xml.bind",
//      "java.corba",
//      "org.jetbrains.annotations",
//      "java.xml.crypto",
//      "jdk.javadoc",
//      "org.junit.platform.launcher"
//    )
//  }
// }