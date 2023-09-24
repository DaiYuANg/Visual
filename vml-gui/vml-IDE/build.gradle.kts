plugins {
    id("org.javamodularity.moduleplugin")
    id("org.beryx.jlink")
//    id ("com.gluonhq.gluonfx-gradle-plugin") version "1.0.15"
    application
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.almasb:fxgl:17.3")
    implementation("io.vertx:vertx-core:4.4.4")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("net.synedra:validatorfx:0.4.2")
    implementation("com.google.inject:guice:7.0.0")
    testImplementation("com.google.inject.extensions:guice-testlib:7.0.0")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    implementation("com.google.jimfs:jimfs:1.3.0")
    implementation("com.dlsc.formsfx:formsfx-core:11.3.2")
    implementation("jakarta.inject:jakarta.inject-api:1.0.5")
    implementation("io.github.eckig.grapheditor:grapheditor-core:19.0.0")
    implementation("jakarta.inject:jakarta.inject-api")
    implementation("org.controlsfx:controlsfx:11.1.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.dlsc.preferencesfx:preferencesfx-core:11.16.0")
//    implementation(projects.vmlGui.vmlComponents)
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
    run {
    }
}

configurations{
    all{
        exclude("javax.annotation",module = "javax.annotation-api")
        exclude("javax.annotation",module = "javax.annotation-api")
    }
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
}

tasks {
    withType<Jar> {

    }
}

patchModules {

}