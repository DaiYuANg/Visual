package org.visual.feature

import com.dlsc.formsfx.model.structure.Field
import com.dlsc.formsfx.model.structure.Form
import com.dlsc.formsfx.model.structure.Group
import com.dlsc.formsfx.view.renderer.FormRenderer
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.layout.FlowPane
import org.visual.component.display.Card
import org.visual.component.display.SingleChildStackPane

enum class AvailableFeature(val thumbnail: Node, val guideForm: Node) {
  OBJECT_ORIENTED(Card().apply { setContent(Button("OBJECT")) }, FlowPane()),
  ER(
      Card().apply { setContent(Button("ER")) },
      SingleChildStackPane().apply {
        val form =
            Form.of(
                    Group.of(
                        Field.ofStringType("").label("Username"),
                        Field.ofStringType("")
                            .label("Password")
                            .required("This field can’t be empty")),
                )
                .title("Login")
        setContent(FormRenderer(form))
      })
}
