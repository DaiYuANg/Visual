plugins {
  java
  checkstyle
  jacoco
  idea
  id("io.freefair.lombok") apply false
  id("com.diffplug.spotless")
}

allprojects {
  repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    google()
    maven { setUrl("https://jitpack.io") }
  }
}

subprojects {
  apply {
    plugin("java")
    plugin("idea")
    plugin("io.freefair.lombok")
    plugin("com.diffplug.spotless")
  }

  dependencies {
    val junitVersion: String by project
    val slf4jVersion: String by project
    val logbackVersion: String by project
    val mapstructVersion: String by project
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.jetbrains:annotations:24.0.1")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("com.google.dagger:dagger:2.47")
    annotationProcessor("com.google.dagger:dagger-compiler:2.27")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("net.java.dev.jna:jna:5.13.0")
    implementation("net.java.dev.jna:jna-platform:5.13.0")
    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
  }
  tasks {
    withType<JavaCompile> {
      options.encoding = Charsets.UTF_8.name()
      dependsOn(rootProject.tasks.spotlessApply.name)
    }
    withType<Test> { useJUnitPlatform() }
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

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
    removeUnusedImports()
    importOrder()
    eclipse()
    formatAnnotations()
  }
  format("styling") {
    target("**/*/*.scss")
    prettier()
  }
  antlr4 {
    target("src/*/antlr4/**/*.g4") // default value, you can change if you want
    antlr4Formatter() // has its own section below
    //        licenseHeader '/* (C) $YEAR */' // or licenseHeaderFile
  }
}
