plugins {
    application
    id ("org.graalvm.buildtools.native") version ("0.9.27")
    id("org.beryx.jlink")
}

dependencies {
    implementation("info.picocli:picocli:4.7.4")
    annotationProcessor("info.picocli:picocli-codegen:4.7.4")
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
       named("main"){
           mainClass.set("org.visual.model.language.cmd.VMLCommand")
           quickBuild.set(true)
       }
    }
}
