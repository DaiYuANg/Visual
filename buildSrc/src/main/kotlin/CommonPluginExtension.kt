import java.util.*

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