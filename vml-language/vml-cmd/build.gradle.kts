plugins {
    application
    id ("org.graalvm.buildtools.native") version ("0.9.27")
    id("org.beryx.jlink")
}

dependencies {
    val picocliVersion: String by project
    implementation("info.picocli:picocli:$picocliVersion")
    annotationProcessor("info.picocli:picocli-codegen:$picocliVersion")
    implementation(projects.vmlLanguage.vmlLsp)
    implementation(projects.vmlLanguage.vmlParser)
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
