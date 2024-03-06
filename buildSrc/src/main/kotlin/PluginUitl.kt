import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.w3c.dom.Element
import java.util.Locale

val commonJvmArgs =
  listOf(
    "-XX:+UseZGC",
    "-XX:+ZGenerational",
    "-XX:+UseCompressedClassPointers",
    "-verbose:gc",
    //        "-XX:+UseLargePages",
    "-XX:+UseStringDeduplication",
    "-XX:+OptimizeStringConcat",
    "-Xlog:gc*",
    "-XX:+UseCompressedOops",
    "-XX:MaxInlineLevel=32",
    "-XX:+AlwaysPreTouch",
    "-XX:+TieredCompilation",
    "-XX:SoftRefLRUPolicyMSPerMB=50",
    "-XX:+UseNUMA",
    "--enable-preview",
    "-Dcom.sun.management.jmxremote",
  )

const val IMPLEMENTATION = "implementation"
const val TEST_IMPLEMENTATION = "testImplementation"
const val COMPILE_ONLY = "compileOnly"
const val ANNOTATION_PROCESSOR = "annotationProcessor"

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