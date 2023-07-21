plugins {
  java
  application
  id("org.javamodularity.moduleplugin") version "1.8.12"
  id("org.openjfx.javafxplugin") version "0.0.14"
  id("org.beryx.jlink") version "2.25.0"
  id("io.freefair.lombok") version "8.1.0"
  id("com.diffplug.spotless") version "6.20.0"
  id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.19"
}
//
group = "org.visual.model"

version = "0.1"
//
repositories {
  mavenLocal()
  mavenCentral()
  gradlePluginPortal()
  google()
}
//

val junitVersion: String = "5.9.2"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
//
application {
  mainModule.set("org.visual.model")
  mainClass.set("org.visual.model.VisualModelApplication")
}

javafx {
  version = "20"
  modules = listOf("javafx.controls", "javafx.fxml", "javafx.media", "javafx.media", "javafx.web")
}

dependencies {
  implementation("com.github.almasb:fxgl:17.3")
  implementation("org.slf4j:slf4j-api:2.0.7")
  implementation("org.jetbrains:annotations:24.0.1")
  implementation("ch.qos.logback:logback-classic:1.4.8")
  implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
  implementation("io.vertx:vertx-core:4.4.4")
  implementation("io.reactivex.rxjava3:rxjava:3.1.6")
  implementation("org.mapstruct:mapstruct:1.5.5.Final")
  implementation("com.google.guava:guava:32.1.1-jre")
  implementation("org.reflections:reflections:0.10.2")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
  implementation("net.synedra:validatorfx:0.4.2")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
  testImplementation("ch.qos.logback:logback-classic:1.4.8")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
  testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
}

jlink {
  imageZip.set(project.file("$buildDir/distributions/app-${javafx.platform.classifier}.zip"))
  launcher { name = "VisualModeling" }
  group = "distribution"
}

tasks {
  withType<JavaCompile> { options.encoding = Charsets.UTF_8.name() }
  withType<Test> {
    useJUnitPlatform()
    //            testLogging {
    //                events("passed", "skipped", "failed")
    //            }
  }
}

gluonfx { isEnableSwRendering = true }

spotless {
  format("misc") {
    target("*.md", ".gitignore", "*.properties")
    indentWithTabs()
    endWithNewline()
  }
  kotlinGradle {
    target("*.gradle.kts")
    ktlint()
    ktfmt()
  }
  java {
    target("**/*.java")
    palantirJavaFormat()
  }
}
