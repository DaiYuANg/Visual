import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.gradle.plugins.ide.idea.model.IdeaModel

class IdeaSetting : Plugin<Project> {
  override fun apply(target: Project) {
    val rootLib = rootLibs(target)
    val jdkVersion = rootLib.versions.jdk
    target.plugins.apply(IdeaPlugin::class.java)
    target.plugins.withType(IdeaPlugin::class.java) {
      target.extensions.configure<IdeaModel> {
        project {
          jdkName = jdkVersion.get()
          languageLevel.level = jdkVersion.get()
          vcs = "git"
          ipr {
            withXml {
              asElement()
                  .firstElement {
                    tagName == "component" && getAttribute("name") == "VcsDirectoryMappings"
                  }
                  .firstElement { tagName == "mapping" }
                  .setAttribute("vcs", "Git")
            }
            //  TODO set idea disable ana
            // https://stackoverflow.com/questions/16369749/how-to-disable-pre-commit-code-analysis-for-git-backed-projects-using-intellij-i
            withXml {
              asElement().firstElement {
                tagName == "component" && getAttribute("name") == "VcsManagerConfiguration"
              }
            }
          }
        }
      }
    }
  }
}
