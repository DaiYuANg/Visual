import org.asciidoctor.gradle.jvm.AsciidoctorTask
import java.io.FileFilter

plugins{
    id("org.asciidoctor.jvm.pdf") version "3.3.2"
    id("org.asciidoctor.jvm.gems") version "3.3.2"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

tasks {
    "asciidoctor"(AsciidoctorTask::class) {
        println(project.sourceSets.asMap)
        setSourceDir(file("src"))
        sources(delegateClosureOf<PatternSet> {
            include("zh/README.adoc", "zh/GettingStart.adoc", "third.adoc")
        })
        setOutputDir(file("build/docs"))
//        outputDir = file("build/docs")
        baseDirIsProjectDir()
        baseDirIsRootProjectDir()
    }
}
