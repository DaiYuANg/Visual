plugins {
    application
    java
}

group = "org.visual.database"

version = "unspecified"

plugins.getPlugin(FxProjectPlugin::class.java).modules("javafx.media", "javafx.web")

application {
    mainClass = "${group}.VisualModelDatabase"
    mainModule.set(group.toString())
    applicationDefaultJvmArgs = commonJvmArgs
}

dependencies {
    implementation(projects.ui.visualGraphEditor)
    implementation(projects.ui.visualTextEditor)
    implementation(projects.ui.visualI18n)
    implementation(projects.module.visualShared)
    implementation(projects.ui.visualComponent)
    implementation(libs.avajeValidaor)
    implementation(libs.avajeValidaorCodegen)
    implementation(libs.picocli)
    implementation(projects.ui.visualCollaborativeServer)
    annotationProcessor(libs.picocliCodegen)
}
