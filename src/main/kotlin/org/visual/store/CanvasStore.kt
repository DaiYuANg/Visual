package org.visual.store

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Node

object CanvasStore {

  val listProperty: ObservableList<Node> = FXCollections.observableArrayList()
}
