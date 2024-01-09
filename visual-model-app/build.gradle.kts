plugins {
    alias(libs.plugins.javafx)
    application
    id("org.javamodularity.moduleplugin") version "1.8.12"
}

javafx {
    version = "21"
    modules(
        "javafx.controls",
        "javafx.fxml",
        "javafx.web",
        "javafx.graphics",
        "javafx.swing",
        "javafx.media",
    )
}

application {
    mainClass.set("org.visual.model.app.VisualModelUI")
    mainModule.set("org.visual.model.app")
}

dependencies {
    implementation(libs.directories)
    implementation(libs.avajeInject)
    implementation(libs.guice)
    implementation(libs.guiceAssistedinject)
    implementation(libs.guiceThrowingproviders)
    implementation(libs.fontawesome5)
    implementation(libs.ikonliJavafx)
    implementation(libs.fluentuiIcon)
    implementation(projects.visualModelUi)
    implementation(libs.gestaltConfig)
    implementation(libs.gestaltToml)
    testImplementation(libs.guiceTestlib)
    testImplementation(libs.javafxUnitTest)
}

