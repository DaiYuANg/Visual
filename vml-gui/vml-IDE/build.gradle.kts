plugins {
    id("org.javamodularity.moduleplugin")
    id("org.beryx.jlink")
//    id ("com.gluonhq.gluonfx-gradle-plugin") version "1.0.15"
    application
}

dependencies {
    implementation("io.vertx:vertx-core:4.4.5")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("com.google.inject:guice:7.0.0")
    testImplementation("com.google.inject.extensions:guice-testlib:7.0.0")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    implementation("jakarta.inject:jakarta.inject-api:1.0.5")
//    implementation("io.github.eckig.grapheditor:grapheditor-core:19.0.0")
    implementation("jakarta.inject:jakarta.inject-api")
//    implementation("org.controlsfx:controlsfx:11.1.2")
    implementation("com.github.oshi:oshi-core-java11:6.4.6")
    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("com.dlsc.preferencesfx:preferencesfx-core:11.16.0")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
    run {
    }
}

//configurations{
//    all{
//        exclude("javax.annotation",module = "javax.annotation-api")
//        exclude("javax.annotation",module = "javax.annotation-api")
//    }
//}

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