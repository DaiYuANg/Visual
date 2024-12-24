plugins { alias(libs.plugins.javafx) }

apply<ModulePlugin>()

group = "org.visual.editor"

version = "unspecified"

dependencies { compileOnly(libs.jetbrains.annotation) }

javafx {
  modules(*javafxModules.toTypedArray())
  version = "23"
  configurations =
    arrayOf(
      IMPLEMENTATION,
      TEST_IMPLEMENTATION,
    )
}