import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.kotlin.dsl.withType

class FatJarPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(ShadowPlugin::class.java)
        target.plugins.apply(JavaLibraryPlugin::class.java)
        target.tasks.withType(ShadowJar::class.java) {
            mergeServiceFiles()
            minimize()
            mergeGroovyExtensionModules()
            manifest {

//                manifest.attributes.putAll(project.tasks.jar.get().manifest.attributes)
            }
        }

        target.tasks.withType<AbstractArchiveTask> {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
        }
    }
}