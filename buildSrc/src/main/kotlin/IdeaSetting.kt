import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.ide.idea.IdeaPlugin

class IdeaSetting : Plugin<Project> {
  override fun apply(target: Project) {
    target.plugins.apply(IdeaPlugin::class.java).model.project.ipr {
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
