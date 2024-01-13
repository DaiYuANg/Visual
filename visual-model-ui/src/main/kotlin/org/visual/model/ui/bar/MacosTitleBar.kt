package org.visual.model.ui.bar

import javafx.scene.control.Button

class MacosTitleBar : CommonTitleBar() {

    private val closeButton = Button("close").apply {
        setOnAction {
            close()
        }
    }
    private val sizeableButton = Button("size").apply {
        setOnMouseClicked {
            if (it.clickCount == 2) {
                restoreSizeOrMax()
            } else {
                maximizeWindow()
            }
        }
    }
    private val minimizeButton = Button("min").apply {
        setOnAction {
            minimizeWindow()
        }
    }

    init {
        children.addAll(closeButton, sizeableButton, minimizeButton)
    }
}
