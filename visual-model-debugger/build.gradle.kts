plugins {
    alias(libs.plugins.javafx)
    `java-library`
    application
    `kotlin-project`
}

group = "org.visual.model.ui.debugger"

val mainClassPath = "org.visual.model.debugger.VisualModelDebugger"

version = "unspecified"

application {
    mainClass.set(mainClassPath)
    mainModule.set(group.toString())
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

dependencies {
    implementation(projects.ui.visualModelComponent)
    implementation(projects.visualModelShared)
    implementation(libs.avajeInject)
    annotationProcessor(libs.avajeInjectGenerator)
    implementation(libs.gestaltConfig)
    implementation(libs.pcollections)
    implementation(projects.visualModelI18n)
    implementation("org.fxmisc.flowless:flowless:0.7.2")
}

tasks.jar {
    manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}
