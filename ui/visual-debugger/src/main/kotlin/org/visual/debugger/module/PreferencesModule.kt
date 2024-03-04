package org.visual.debugger.module

import com.dlsc.preferencesfx.PreferencesFx
import com.dlsc.preferencesfx.model.Category
import com.dlsc.preferencesfx.model.Group
import com.dlsc.preferencesfx.model.Setting
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import java.util.prefs.Preferences
import javafx.beans.property.*
import org.visual.debugger.component.VisualDebugger
import org.visual.debugger.view.VisualDebuggerView
import org.visual.shared.PreferencesWrapper

class PreferencesModule : AbstractModule() {
  private val stringProperty: StringProperty = SimpleStringProperty("String")
  private var booleanProperty: BooleanProperty = SimpleBooleanProperty(true)
  private var integerProperty: IntegerProperty = SimpleIntegerProperty(12)
  private var doubleProperty: DoubleProperty = SimpleDoubleProperty(6.5)

  override fun configure() {}

  @Provides
  @Singleton
  fun preferencesFX(): PreferencesFx {
    val preferencesFx: PreferencesFx =
        PreferencesFx.of(
            VisualDebugger::class
                .java, // Save class (will be used to reference saved values of Settings to)
            Category.of(
                "Category title 1",
                Setting.of("Setting title 1", stringProperty), // creates a group automatically
                Setting.of("Setting title 2", booleanProperty) // which contains both settings
                ),
            Category.of("Category title 2")
                .expand() // Expand the parent category in the tree-view
                .subCategories( // adds a subcategory to "Category title 2"
                    Category.of(
                        "Category title 3",
                        Group.of("Group title 1", Setting.of("Setting title 3", integerProperty)),
                        Group.of( // group without title
                            Setting.of("Setting title 3", doubleProperty)))))
    return preferencesFx
  }

  @Provides
  @Singleton
  fun preferences(): PreferencesWrapper {
    return PreferencesWrapper(Preferences.userNodeForPackage(VisualDebuggerView::class.java))
  }
}
