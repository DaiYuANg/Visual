import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class ExecutablePlugin : Plugin<Project> {
    companion object {
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
    }

    override fun apply(target: Project) {
        target.apply<ApplicationPlugin>()
        target.extensions.configure<JavaExec> {
            mainModule.set(target.group.toString())
        }
    }
}

class ExecutableExtension{

}