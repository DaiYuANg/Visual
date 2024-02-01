import CommonPluginExtension.Companion.convertToCamelCase
//import org.beryx.jlink.JlinkPlugin
//import org.beryx.jlink.data.JlinkPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class JlinkProject : Plugin<Project> {
    private val extraDependencies = mutableListOf<String>()

    override fun apply(target: Project) {
//        target.plugins.apply(JlinkPlugin::class.java)
        setupJlink(target)
    }

    private fun setupJlink(target: Project) {
//        val targetName = convertToCamelCase(target.name)
//        target.extensions.configure<JlinkPluginExtension> {
//            enableCds()
//            options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
//            launcher {
//                noConsole = true
//                name = targetName
//                jvmArgs = commonJvmArgs
//            }
//            imageZip.set(target.file("${target.layout.buildDirectory}/image-zip/${targetName}-image.zip"))
//            jpackage { appVersion = target.version.toString() }
//            addExtraDependencies(*extraDependencies.toTypedArray())
//        }
    }

    fun extraDependencies(deps:Collection<String>){
        extraDependencies.addAll(deps)
    }
}