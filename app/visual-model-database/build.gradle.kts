plugins {
    alias(libs.plugins.javafx)
    application
    java
    alias(libs.plugins.jlink)
    id("org.graalvm.buildtools.native") version "0.9.28"
    `kotlin-project`
    id("io.avaje.inject") version "0.3"
}

group = "org.visual.model.database"
version = "unspecified"

apply<CommonPlugin>()

apply<FatJarPlugin>()

dependencies {
    implementation(projects.ui.visualModelGraphEditor)
    implementation(projects.ui.visualModelComponent)
    implementation(projects.module.visualModelI18n)
    implementation(projects.ui.visualModelTextEditor)
    implementation(libs.avajeInject)
    implementation(libs.avajeValidaor)
    implementation(libs.avajeValidaorCodegen)
    implementation(libs.picocli)
    annotationProcessor(libs.picocliCodegen)
    annotationProcessor(libs.avajeInjectGenerator)
}

javafx {
    version = libs.versions.javafxVersion.get()
    modules(
        "javafx.controls",
        "javafx.fxml",
        "javafx.graphics",
        "javafx.swing",
        "javafx.media",
        "javafx.web"
    )
    configurations = arrayOf("implementation", "testImplementation")
}