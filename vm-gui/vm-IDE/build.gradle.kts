plugins {
    id("org.javamodularity.moduleplugin")
    id("org.beryx.jlink")
    application
//    id("org.gradlex.extra-java-module-info") version "1.4.1"
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.almasb:fxgl:17.3")
    implementation("io.vertx:vertx-core:4.4.4")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("net.synedra:validatorfx:0.4.2")
    implementation("com.google.jimfs:jimfs:1.3.0")
    implementation("com.dlsc.formsfx:formsfx-core:11.3.2")
    implementation("jakarta.inject:jakarta.inject-api:1.0.5")
//    implementation("com.github.iAmGio:froxty:1.4.0")
    implementation("io.github.eckig.grapheditor:grapheditor-core:19.0.0")
    implementation("org.controlsfx:controlsfx:11.1.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(projects.vmGui.vmComponents)
//    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")
    implementation("com.dlsc.preferencesfx:preferencesfx-core:11.16.0")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
}

jlink {
    imageZip.set(project.file("${layout.buildDirectory}/distributions/app-${javafx.platform.classifier}.zip"))
    launcher { name = "VisualModeling" }
    group = "distribution"
}

tasks{
    withType<Jar>{

    }
}

patchModules {

}
//extraJavaModuleInfo {
//    // This does not have to be a complete description (e.g. here 'org.apache.commons.collections' does not export anything here).
//    // It only needs to be good enough to work in the context of this application we are building.
////    module("commons-beanutils-1.9.4.jar", "org.apache.commons.beanutils", "1.9.4") {
////        exports("org.apache.commons.beanutils")
////
////        requires("org.apache.commons.logging")
////        requires("java.sql")
////        requires("java.desktop")
////    }
////    module("commons-cli-1.4.jar", "org.apache.commons.cli", "3.2.2") {
////        exports("org.apache.commons.cli")
////    }
////    module("commons-collections-3.2.2.jar", "org.apache.commons.collections", "3.2.2")
////    automaticModule("commons-logging-1.2.jar", "org.apache.commons.logging")
//}