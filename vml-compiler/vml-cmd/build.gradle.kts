plugins {
    application
    id ("org.graalvm.buildtools.native") version ("0.9.23")
    id("org.beryx.jlink")
}

dependencies {
    implementation("info.picocli:picocli:4.7.4")
    annotationProcessor("info.picocli:picocli-codegen:4.7.4")
}

nativeBuild{
    mainClass.set("org.visual.model.language.cmd.VMLCommand")
}

