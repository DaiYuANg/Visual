package org.visual.model.ui.util

import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Screen
import javafx.stage.Stage


class StageInspector(private val stage: Stage) {
    private val scene: Scene = stage.scene

    private var isDraggable = false
    private var isDragging = false
    private var allowDragging = true
    private var dragMarginTop = 0.0
    private var dragMarginRight = 0.0
    private var dragMarginBottom = 0.0
    private var dragMarginLeft = 0.0

    private var isFullscreenable = false
    private var isFullscreen = false
    private var allowFullscreen = true
    private var fullscreenMarginTop = 0.0
    private var fullscreenMarginRight = 0.0
    private var fullscreenMarginBottom = 0.0
    private var fullscreenMarginLeft = 0.0
    private var stageWidthBeforeFullscreen = 0.0
    private var stageHeightBeforeFullscreen = 0.0
    private var stageXBeforeFullscreen = 0.0
    private var stageYBeforeFullscreen = 0.0

    private var isResizeable = false
    private var isResizing = false
    private var allowResizing = true
    private var resizeDirection: ResizeDirection? = null
    private var resizeMarginTop = 0.0
    private var resizeMarginRight = 0.0
    private var resizeMarginBottom = 0.0
    private var resizeMarginLeft = 0.0

    @JvmOverloads
    fun makeDraggable(
        marginTop: Double = 50.0,
        marginRight: Double = 0.0,
        marginBottom: Double = 0.0,
        marginLeft: Double = 0.0
    ): StageInspector {
        dragMarginTop = marginTop
        dragMarginRight = marginRight
        dragMarginBottom = marginBottom
        dragMarginLeft = marginLeft

        if (!isDraggable) {
            isDraggable = true

            var dragStartOffsetX = 0.0
            var dragStartOffsetY = 0.0

            scene.addEventHandler(MouseEvent.MOUSE_MOVED) {
                val isWithinBounds = detectDraggingBounds(it)

                if (isDraggable && allowDragging && isWithinBounds) {
                    scene.cursor = Cursor.OPEN_HAND
                } else {
                    if (scene.cursor == Cursor.OPEN_HAND) {
                        scene.cursor = Cursor.DEFAULT
                    }
                }
            }

            scene.addEventHandler(MouseEvent.MOUSE_PRESSED) {
                dragStartOffsetX = stage.x - it.screenX
                dragStartOffsetY = stage.y - it.screenY
            }

            scene.addEventHandler(MouseEvent.MOUSE_DRAGGED) {
                val isWithinBounds = detectDraggingBounds(it)

                if (isDraggable && allowDragging && isWithinBounds) {
                    isDragging = true
                    scene.cursor = Cursor.CLOSED_HAND
                }

                if (isDragging) {
                    stage.x = it.screenX + dragStartOffsetX
                    stage.y = it.screenY + dragStartOffsetY
                }
            }

            scene.addEventHandler(MouseEvent.MOUSE_RELEASED) {
                if (isDragging) {
                    isDragging = false
                    scene.cursor = Cursor.DEFAULT
                }
            }
        }

        return this
    }

    private fun detectDraggingBounds(event: MouseEvent): Boolean {
        return event.sceneY <= dragMarginTop
                || scene.height - event.sceneY <= dragMarginBottom
                || event.sceneX <= dragMarginLeft
                || scene.width - event.sceneX <= dragMarginRight
    }

    @JvmOverloads
    fun makeFullscreenable(
        marginTop: Double = 50.0,
        marginRight: Double = 0.0,
        marginBottom: Double = 0.0,
        marginLeft: Double = 0.0
    ): StageInspector {
        fullscreenMarginTop = marginTop
        fullscreenMarginRight = marginRight
        fullscreenMarginBottom = marginBottom
        fullscreenMarginLeft = marginLeft

        if (!isFullscreenable) {
            isFullscreenable = true

            scene.addEventHandler(MouseEvent.MOUSE_PRESSED) {
                val isDoubleClick = it.button == MouseButton.PRIMARY && it.clickCount >= 2

                if (isFullscreenable && allowFullscreen && isDoubleClick && detectFullscreenBounds(it)) {
                    if (isFullscreen) {
                        isFullscreen = false
                        allowDragging = true
                        allowResizing = true

                        stage.x = stageXBeforeFullscreen
                        stage.y = stageYBeforeFullscreen
                        stage.width = stageWidthBeforeFullscreen
                        stage.height = stageHeightBeforeFullscreen
                    } else {
                        isFullscreen = true
                        allowDragging = false
                        allowResizing = false
                        stageWidthBeforeFullscreen = stage.width
                        stageHeightBeforeFullscreen = stage.height
                        stageXBeforeFullscreen = stage.x
                        stageYBeforeFullscreen = stage.y

                        val screenBounds = Screen.getPrimary().visualBounds
                        val newWidth = if (stage.maxWidth < screenBounds.width) {
                            stage.maxWidth
                        } else {
                            screenBounds.width
                        }
                        val newHeight = if (stage.maxHeight < screenBounds.height) {
                            stage.maxHeight
                        } else {
                            screenBounds.height
                        }

                        stage.width = newWidth
                        stage.height = newHeight
                        stage.x = screenBounds.minX
                        stage.y = screenBounds.minY
                    }
                }
            }

        }

        return this
    }

    private fun detectFullscreenBounds(event: MouseEvent): Boolean {
        val isWithinBounds = event.sceneY <= fullscreenMarginTop
                || scene.height - event.sceneY <= fullscreenMarginBottom
                || event.sceneX <= fullscreenMarginLeft
                || scene.width - event.sceneX <= fullscreenMarginRight

        val resizeDirection = detectResizeDirection(event)

        return isWithinBounds && resizeDirection == null
    }

    @JvmOverloads
    fun makeResizable(
        marginTop: Double = 10.0,
        marginRight: Double = 10.0,
        marginBottom: Double = 10.0,
        marginLeft: Double = 10.0
    ): StageInspector {
        resizeMarginTop = marginTop
        resizeMarginRight = marginRight
        resizeMarginBottom = marginBottom
        resizeMarginLeft = marginLeft

        if (isResizeable) return this
        isResizeable = true

        scene.addEventHandler(MouseEvent.MOUSE_MOVED) {
            if (isResizeable && allowResizing && !isResizing) {
                when (detectResizeDirection(it)) {
                    ResizeDirection.NORTH_WEST -> scene.cursor = Cursor.NW_RESIZE
                    ResizeDirection.NORTH_EAST -> scene.cursor = Cursor.NE_RESIZE
                    ResizeDirection.SOUTH_WEST -> scene.cursor = Cursor.SW_RESIZE
                    ResizeDirection.SOUTH_EAST -> scene.cursor = Cursor.SE_RESIZE
                    ResizeDirection.NORTH -> scene.cursor = Cursor.N_RESIZE
                    ResizeDirection.SOUTH -> scene.cursor = Cursor.S_RESIZE
                    ResizeDirection.WEST -> scene.cursor = Cursor.W_RESIZE
                    ResizeDirection.EAST -> scene.cursor = Cursor.E_RESIZE
                    else -> {
                        val cursors = listOf(
                            Cursor.NW_RESIZE,
                            Cursor.NE_RESIZE,
                            Cursor.SW_RESIZE,
                            Cursor.SE_RESIZE,
                            Cursor.N_RESIZE,
                            Cursor.S_RESIZE,
                            Cursor.W_RESIZE,
                            Cursor.E_RESIZE
                        )

                        if (cursors.contains(scene.cursor)) {
                            scene.cursor = Cursor.DEFAULT
                        }
                    }
                }
            }
        }

        var resizeStartFromSceneX = 0.0
        var resizeStartFromSceneY = 0.0
        var resizeStartFromScreenX = 0.0
        var resizeStartFromScreenY = 0.0
        var resizeStartStageWidth = 0.0
        var resizeStartStageHeight = 0.0

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED) {
            if (isResizeable && allowResizing && !isResizing) {
                resizeDirection = detectResizeDirection(it)

                if (resizeDirection != null) {
                    if (it.button == MouseButton.PRIMARY && it.clickCount >= 2) {
                        val screenBounds = Screen.getPrimary().visualBounds

                        if (resizeDirection == ResizeDirection.NORTH || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.NORTH_EAST) {
                            stage.height = ensureStageHeightIsWithinLimits(
                                stage.height + stage.y - screenBounds.minY
                            )
                            stage.y = 0.0
                        }

                        if (resizeDirection == ResizeDirection.SOUTH || resizeDirection == ResizeDirection.SOUTH_WEST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                            stage.height = ensureStageHeightIsWithinLimits(
                                screenBounds.height - stage.y + screenBounds.minY
                            )

                            if (stage.height == screenBounds.height) {
                                stage.y = 0.0
                            }
                        }

                        if (resizeDirection == ResizeDirection.WEST || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.SOUTH_WEST) {
                            stage.width = ensureStageWidthIsWithinLimits(
                                stage.width + stage.x
                            )
                            stage.x = 0.0
                        }

                        if (resizeDirection == ResizeDirection.EAST || resizeDirection == ResizeDirection.NORTH_EAST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                            stage.width = ensureStageWidthIsWithinLimits(
                                screenBounds.width - stage.x
                            )

                            if (stage.width == screenBounds.width) {
                                stage.x = 0.0
                            }
                        }
                    } else {
                        isResizing = true
                        isDraggable = false
                        isFullscreenable = false

                        resizeStartFromScreenX = it.screenX
                        resizeStartFromScreenY = it.screenY
                        resizeStartFromSceneX = it.sceneX
                        resizeStartFromSceneY = it.sceneY
                        resizeStartStageWidth = stage.width
                        resizeStartStageHeight = stage.height
                    }
                }
            }
        }

        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED) {
            if (isResizing) {
                if (resizeDirection == ResizeDirection.NORTH || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.NORTH_EAST) {
                    val newHeight = ensureStageHeightIsWithinLimits(
                        resizeStartStageHeight + (resizeStartFromScreenY - it.screenY)
                    )
                    val newY = when (newHeight) {
                        stage.maxHeight, stage.minHeight -> stage.y
                        else -> it.screenY - resizeStartFromSceneY
                    }

                    stage.height = newHeight
                    stage.y = newY
                }

                if (resizeDirection == ResizeDirection.SOUTH || resizeDirection == ResizeDirection.SOUTH_WEST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                    val newHeight = ensureStageHeightIsWithinLimits(
                        resizeStartStageHeight + (it.screenY - resizeStartFromScreenY)
                    )

                    stage.height = newHeight
                }

                if (resizeDirection == ResizeDirection.WEST || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.SOUTH_WEST) {
                    val newWidth = ensureStageWidthIsWithinLimits(
                        resizeStartStageWidth + (resizeStartFromScreenX - it.screenX)
                    )
                    val newX = when (newWidth) {
                        stage.maxWidth, stage.minWidth -> stage.x
                        else -> it.screenX - resizeStartFromSceneX
                    }

                    stage.width = newWidth
                    stage.x = newX
                }

                if (resizeDirection == ResizeDirection.EAST || resizeDirection == ResizeDirection.NORTH_EAST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                    val newWidth = ensureStageWidthIsWithinLimits(
                        resizeStartStageWidth + (it.screenX - resizeStartFromScreenX)
                    )

                    stage.width = newWidth
                }
            }
        }

        scene.addEventHandler(MouseEvent.MOUSE_RELEASED) {
            if (isResizing) {
                isResizing = false
                isDraggable = true
                isFullscreenable = true
            }
        }

        return this
    }

    private fun detectResizeDirection(event: MouseEvent): ResizeDirection? {
        val isNorthResize = event.sceneY <= resizeMarginTop
        val isSouthResize = scene.height - event.sceneY <= resizeMarginBottom
        val isWestResize = event.sceneX <= resizeMarginLeft
        val isEastResize = scene.width - event.sceneX <= resizeMarginRight
        val isNorthWestResize = isNorthResize && isWestResize
        val isNorthEastResize = isNorthResize && isEastResize
        val isSouthWestResize = isSouthResize && isWestResize
        val isSouthEastResize = isSouthResize && isEastResize

        return when {
            isNorthWestResize -> ResizeDirection.NORTH_WEST
            isNorthEastResize -> ResizeDirection.NORTH_EAST
            isSouthWestResize -> ResizeDirection.SOUTH_WEST
            isSouthEastResize -> ResizeDirection.SOUTH_EAST
            isNorthResize -> ResizeDirection.NORTH
            isSouthResize -> ResizeDirection.SOUTH
            isWestResize -> ResizeDirection.WEST
            isEastResize -> ResizeDirection.EAST
            else -> null
        }
    }

    private fun ensureStageWidthIsWithinLimits(width: Double): Double {
        val screenBounds = Screen.getPrimary().visualBounds

        return when {
            width > stage.maxWidth -> stage.maxWidth
            width < stage.minWidth -> stage.minWidth
            width > screenBounds.width -> screenBounds.width
            else -> width
        }
    }

    private fun ensureStageHeightIsWithinLimits(height: Double): Double {
        val screenBounds = Screen.getPrimary().visualBounds

        return when {
            height > stage.maxHeight -> stage.maxHeight
            height < stage.minHeight -> stage.minHeight
            height > screenBounds.height -> screenBounds.height
            else -> height
        }
    }

    enum class ResizeDirection {
        NORTH, NORTH_EAST, NORTH_WEST,
        SOUTH, SOUTH_EAST, SOUTH_WEST,
        EAST, WEST;
    }

    companion object {
        @JvmStatic
        fun inspect(
            stage: Stage
        ) {
            StageInspector(stage)
                .makeDraggable()
                .makeResizable()
                .makeFullscreenable()
        }
    }
}