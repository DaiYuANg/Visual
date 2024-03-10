import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.github.spotbugs.snom.SpotBugsPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.plugins.quality.PmdPlugin
import java.nio.charset.StandardCharsets

class FormatSetting : Plugin<Project> {
  override fun apply(target: Project) {
    target.plugins.apply(SpotlessPlugin::class.java)
    target.plugins.apply(CheckstylePlugin::class.java)
    target.plugins.apply(PmdPlugin::class.java)
    target.plugins.apply(SpotBugsPlugin::class.java)
    target.extensions.configure(SpotlessExtension::class.java) {
      encoding = StandardCharsets.UTF_8
      format("misc") {
        target("*.md", ".gitignore", "gradle/libs.versions.toml", "**/*.fxml")
        indentWithSpaces(IDENT_WIDTH)
        trimTrailingWhitespace()
        targetExclude("**/node_modules/**/*")
        endWithNewline()
      }
      java {
        target("**/src/**/*.java")
        importOrder()
        googleJavaFormat().formatJavadoc(true)
        indentWithSpaces(2)
        removeUnusedImports()
        formatAnnotations()
          .addTypeAnnotation("Empty")
          .addTypeAnnotation("NonEmpty")
          .removeTypeAnnotation("Localized")
      }
      kotlin {
        target("buildSrc/src/main/kotlin/*.kt")
        ktfmt()
        ktlint()
      }
      kotlinGradle {
        target("**/*.gradle.kts")
        ktfmt()
        ktlint()
          .setEditorConfigPath(
            "${target.rootProject.layout.projectDirectory}/.editorconfig",
          ) // sample unusual placement
        indentWithSpaces(IDENT_WIDTH)
      }
      json {
        targetExclude("**/node_modules/**/*")
        target("**/src/**/*.json")
        jackson()
      }
      yaml {
        targetExclude("**/node_modules/**/*", "pnpm-lock.yaml")
        target("**/src/**/*.yaml") // you have to set the target manually
        prettier() // has its own section below
        jackson()
      }
      sql {
        target("**/src/**/*.sql") // have to set manually
        dbeaver()
      }
      antlr4 {
        target("**/src/main/antlr4/**/*.g4")
        antlr4Formatter()
      }
    }
//    target.
  }
}