import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.tasks.JacocoReport

class FormatterPlugin : Plugin<Project> {
    private val pluginList = listOf(
        SpotlessPlugin::class.java,
        JacocoPlugin::class.java,
        SpotlessPlugin::class.java
    )

    private val identWidth = 2

    override fun apply(project: Project) {
        pluginList.forEach { project.plugins.apply(it) }

        project.extensions.configure(SpotlessExtension::class.java) {
            format("misc") {
                target("*.md", ".gitignore", "**/*.java")
                indentWithSpaces(identWidth)
                endWithNewline()
            }
            java {
                target("**/*.java")
                importOrder()
                indentWithSpaces(identWidth)
                removeUnusedImports()
                formatAnnotations()
                    .addTypeAnnotation("Empty")
                    .addTypeAnnotation("NonEmpty")
                    .removeTypeAnnotation("Localized")
                cleanthat()
            }

            kotlinGradle {
                target("**/*.gradle.kts") // default target for kotlinGradle
                ktfmt()
                indentWithSpaces(identWidth)
            }
            kotlin {
                ktfmt()
                indentWithSpaces(identWidth)
            }
        }

        project.tasks.withType<JacocoReport> {
            reports {
                xml.required.set(false)
                csv.required.set(false)
                html.outputLocation.set(project.layout.buildDirectory.dir("jacocoHtml"))
            }
        }
    }
}