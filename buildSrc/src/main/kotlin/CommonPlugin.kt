import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class CommonPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("common", CommonPluginExtension::class.java)
    }
}
