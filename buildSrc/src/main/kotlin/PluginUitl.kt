import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.w3c.dom.Element

val commonJvmArgs =
    listOf(
        "-XX:+UseZGC",
        "-XX:+ZGenerational",
        "-XX:+UseCompressedClassPointers",
        "-verbose:gc",
        "-XX:+UseLargePages",
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
        "-Dcom.sun.management.jmxremote")

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
