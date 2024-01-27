import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class CommonPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("common", CommonPluginExtension::class.java)
    }
}

open class CommonPluginExtension {
    companion object{
        fun convertToCamelCase(input: String): String {
            val words = input.split("-")
            val camelCaseWords = words.mapIndexed { _, word ->
                word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }
            return camelCaseWords.joinToString("")
        }
    }
}