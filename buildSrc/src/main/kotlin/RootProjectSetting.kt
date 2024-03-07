import com.coditory.gradle.manifest.ManifestPlugin
import com.coditory.gradle.manifest.ManifestPluginExtension
import com.github.benmanes.gradle.versions.VersionsPlugin
import com.xenoterracide.gradle.semver.SemverExtension
import org.graalvm.buildtools.gradle.NativeImagePlugin
import org.graalvm.buildtools.gradle.dsl.GraalVMExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaApplication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class RootProjectSetting : Plugin<Project> {
  private val mainClassPath = "org.visual.VisualApplication"

  override fun apply(target: Project) {
    val semver = target.extensions.getByType(SemverExtension::class.java)
    val git = semver.git
    target.plugins.apply(ApplicationPlugin::class.java)
    target.plugins.apply(NativeImagePlugin::class.java)
    target.plugins.apply(VersionsPlugin::class.java)
    target.plugins.apply(ManifestPlugin::class.java)
    target.configure<JavaApplication> {
      mainClass.set(mainClassPath)
      applicationDefaultJvmArgs = commonJvmArgs
    }
    target.configure<GraalVMExtension> {
      toolchainDetection.set(true)
    }

    target.extensions.configure(ManifestPluginExtension::class.java) {
      buildAttributes = true
      implementationAttributes = true
      scmAttributes = true
      attributes =
        mapOf(
          VERSION_KEY to target.version,
          GIT_HASH_KEY to git.commit,
          LATEST_TAG_KEY to git.latestTag,
          BRANCH_KEY to git.branch,
          MAIN_CLASS_KEY to mainClassPath,
        )
    }
  }
}