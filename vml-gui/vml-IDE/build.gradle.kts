plugins {
    id("org.javamodularity.moduleplugin")
    id("org.beryx.jlink")
//    id ("com.gluonhq.gluonfx-gradle-plugin") version "1.0.15"
    application
}

dependencies {
    implementation("io.vertx:vertx-core:4.4.5")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1.MR")
    implementation("com.github.oshi:oshi-core-java11:6.4.6")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.dlsc.preferencesfx:preferencesfx-core:11.16.0")
    implementation("commons-io:commons-io:2.14.0")
    implementation("org.apache.commons:commons-configuration2:2.9.0")
    implementation("com.dustinredmond.fxtrayicon:FXTrayIcon:4.0.1")
    implementation("dev.dirs:directories:26")
    annotationProcessor("io.ebean:querybean-generator:13.15.0-jakarta")
    implementation("io.ebean:ebean:13.15.0-jakarta")
    implementation("io.ebean:ebean-querybean:13.15.0-jakarta")
    implementation("io.ebean:ebean-sqlite:13.22.1-jakarta")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.xerial:sqlite-jdbc:3.43.0.0")
    testImplementation("io.ebean:ebean-test:13.15.0-jakarta")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
    testImplementation("io.vertx:vertx-junit5:4.4.5")
}
//
//application {
//    mainModule.set("org.visual.model")
//    mainClass.set("org.visual.model.VisualModelApplication")
//    run {
//    }
//}

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