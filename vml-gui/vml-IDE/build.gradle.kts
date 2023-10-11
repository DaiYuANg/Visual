plugins {
    id("org.javamodularity.moduleplugin")
    id("org.beryx.jlink")
    application
}

val vertxVersion = "4.4.5"
val atlantafxVersion = "2.0.1"
val oshiVersion = "6.4.4"
val ebeanVersion = "13.15.0-jakarta"
val ikonliVersion = "12.3.1"

dependencies {
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-uri-template:$vertxVersion")
    implementation("io.github.mkpaz:atlantafx-base:$atlantafxVersion")
    implementation("com.github.oshi:oshi-core-java11:$oshiVersion")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("commons-io:commons-io:2.14.0")
    implementation("org.apache.commons:commons-configuration2:2.9.0")
    implementation("com.dustinredmond.fxtrayicon:FXTrayIcon:4.0.1")
    implementation("dev.dirs:directories:26")
    implementation("io.ebean:ebean:$ebeanVersion")
    implementation("io.ebean:ebean-querybean:$ebeanVersion")
    implementation("io.ebean:ebean-sqlite:13.22.1-jakarta")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.xerial:sqlite-jdbc:3.43.0.0")
    implementation(projects.vmlGui.vmlComponents)
    implementation(projects.libs.shared)
    implementation("org.pf4j:pf4j-update:2.3.0")
    implementation("org.kordamp.ikonli:ikonli-simpleicons-pack:$ikonliVersion")
    implementation("org.kordamp.ikonli:ikonli-devicons-pack:$ikonliVersion")
    implementation("org.kordamp.ikonli:ikonli-fontawesome-pack:$ikonliVersion")
    implementation("org.kordamp.ikonli:ikonli-fontawesome5-pack:$ikonliVersion")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.codehaus.groovy:groovy-all:2.4.21")
    testImplementation("io.ebean:ebean-test:$ebeanVersion")
    testImplementation("org.testfx:testfx-junit5:4.0.17")
    testImplementation("io.vertx:vertx-junit5:$vertxVersion")
    annotationProcessor("io.ebean:querybean-generator:13.15.0-jakarta")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
}

configurations {
    all {
        exclude("javax.annotation", module = "javax.annotation-api")
        exclude("javax.annotation", module = "javax.annotation-api")
        attributes {
        }
    }
}

java {
    modularity.inferModulePath.set(false)
}

tasks.withType<JavaExec> {
    args = listOf("--add-opens", "java.base/java.io=ALL-UNNAMED")
//    options.compilerArgs.addAll(listOf("--add-opens", "java.base/java.io=ALL-UNNAMED"))
}

jlink {
    imageZip.set(project.file("${layout.buildDirectory}/distributions/app-${javafx.platform.classifier}.zip"))
    launcher { name = "VisualModeling" }
    group = "distribution"
    mergedModule {
        requires("org.slf4j")
        requires("jakarta.inject")
        requires("jakarta.annotation")
    }
    forceMerge("org.slf4j")
    mergedModule {
        additive = true
        requires("org.slf4j")
        requires("com.fasterxml.jackson.databind")
    }
}

//tasks.named<JavaCompile>("compileJava") {
//    inputs.property("moduleName", moduleName)
//    doFirst {
//        val modulePath = classpath.asPath
//        classpath = files() // Clear classpath since we are using module path instead
//        options.compilerArgs.addAll(listOf(
//            "--module-path", modulePath // Supply accumulated class path as module path instead.
//        ))
//    }
//}
//
//
//tasks.named<JavaCompile>("compileTestJava") {
//    // See https://stackoverflow.com/questions/46991022/junit-5-java-9-and-gradle-how-to-pass-add-modules
//    inputs.property("moduleName", moduleName)
//    doFirst {
//        val modulePath = classpath.asPath
//        classpath = files() // Clear classpath since we are using module path instead
//        options.compilerArgs.addAll(listOf(
//            "--module-path", modulePath // Supply accumulated class path as module path instead.
//        ))
//    }
//}
//
//tasks.named<JavaExec>("run") {
//    doFirst {
//        val modulePath = classpath.asPath
//        jvmArgs = listOf(
//            "--module-path", modulePath,
//            "--add-modules", "ALL-MODULE-PATH",
//            "--patch-module", "$moduleName=build/resources/main",
//            "--module", "$moduleName/${application.mainClass}"
//        )
//    }
//}