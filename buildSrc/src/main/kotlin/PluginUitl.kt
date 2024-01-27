import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

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
        "-Xnoclassgc",
        "-XX:MaxInlineLevel=32",
        "-XX:+AlwaysPreTouch",
        "-XX:+TieredCompilation",
        "-XX:SoftRefLRUPolicyMSPerMB=50",
        "-XX:+UseNUMA",
        "--enable-preview"
    )


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