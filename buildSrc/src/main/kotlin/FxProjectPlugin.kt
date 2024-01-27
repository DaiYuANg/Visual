import gradle.kotlin.dsl.accessors._ce4685dcb57783202e770920bb44a340.jar
import gradle.kotlin.dsl.accessors._ce4685dcb57783202e770920bb44a340.testImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.openjfx.gradle.JavaFXOptions
import org.openjfx.gradle.JavaFXPlugin

class FxProjectPlugin : Plugin<Project> {

    private val commonFxModule = mutableListOf(
        "javafx.controls",
        "javafx.fxml",
        "javafx.graphics",
        "javafx.swing",
        "javafx.media",
        "javafx.web"
    )

    private val classScope = arrayOf(
        "implementation",
        "testImplementation"
    )

    override fun apply(target: Project) {
        val libs = rootLibs(project = target)
        target.apply<JavaFXPlugin>()
        target.extensions.configure<JavaFXOptions> {
            configureJavaFXOptions(target)
            this.configurations = classScope
        }
        target.tasks.jar.get().manifest.apply {
            attributes["JavaFx-Version"] = libs.versions.javafxVersion.get()
        }
        target.dependencies {
            testImplementation(libs.javafxUnitTest)
        }
    }

    fun modules(vararg modules: String) {
        commonFxModule.addAll(modules)
    }

    private fun configureJavaFXOptions(target: Project) {
        target.extensions.configure<JavaFXOptions> {
            // 设置 JavaFX 模块
            modules(*commonFxModule.toTypedArray())
        }
    }
}