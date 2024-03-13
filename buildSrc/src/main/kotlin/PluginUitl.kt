import java.util.Locale
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.w3c.dom.Element

fun libs(project: Project): LibrariesForLibs {
  return project.the<LibrariesForLibs>()
}

fun rootProject(project: Project): Project {
  return if (project.parent == null) {
    project
  } else {
    rootProject(project.parent!!)
  }
}

fun rootLibs(project: Project): LibrariesForLibs {
  return libs(rootProject(project))
}

fun Element.firstElement(predicate: (Element.() -> Boolean)) =
    childNodes
        .run { (0 until length).map(::item) }
        .filterIsInstance<Element>()
        .first { it.predicate() }

fun convertToCamelCase(input: String): String {
  val words = input.split("-")
  val camelCaseWords =
      words.mapIndexed { _, word ->
        word.replaceFirstChar {
          if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
      }
  return camelCaseWords.joinToString("")
}
