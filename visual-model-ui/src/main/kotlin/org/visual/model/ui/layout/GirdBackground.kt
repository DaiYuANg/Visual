package org.visual.model.ui.layout

import com.google.common.collect.HashBasedTable
import javafx.application.Platform
import javafx.beans.property.DoubleProperty
import javafx.beans.property.DoublePropertyBase
import javafx.css.CssMetaData
import javafx.css.StyleConverter
import javafx.css.StyleableObjectProperty
import javafx.css.StyleableProperty
import javafx.scene.CacheHint
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.LineTo
import javafx.scene.shape.MoveTo
import javafx.scene.shape.Path
import javafx.scene.shape.PathElement
import org.jetbrains.annotations.Contract
import org.visual.model.shared.KSlf4j


@KSlf4j
data class GirdBackground @JvmOverloads constructor(
    private val halfPixelOffset: Double = -0.5,
    private val defaultGirdColor: Color = Color.GRAY,
) : Region() {
    private val girdColorSelector = "-grid-color"
    private val girdColorPropertyName = "gridColor"
    private var mLastWidth: Double = -1.0
    private var mLastHeight: Double = -1.0
    private val internalStyleClass = "graph-editor-grid"
    private val mGrid = Path()
    private val hMoveToCache: HashBasedTable<Int, Double, MoveTo>
            by lazy { createHashBasedTable() }
    private val hLineToCache: HashBasedTable<Double, Double, LineTo>
            by lazy { createHashBasedTable() }

    private val lMoveToCache: HashBasedTable<Double, Double, MoveTo>
            by lazy {
                createHashBasedTable()
            }

    private val lLineToCache: HashBasedTable<Double, Double, LineTo>
            by lazy { createHashBasedTable() }

    private val mGridSpacing: DoubleProperty by lazy {
        object : DoublePropertyBase(10.0) {
            override fun getBean(): Any {
                return this
            }

            @Contract(pure = true)
            override fun getName(): String {
                return "gridSpacing"
            }

            override fun invalidated() {
                draw(width, height)
            }
        }
    }

    private val girdColor: CssMetaData<GirdBackground, Color> by lazy {
        object : CssMetaData<GirdBackground, Color>(
            girdColorSelector,
            StyleConverter.getColorConverter()
        ) {
            override fun isSettable(node: GirdBackground): Boolean {
                return !node.mGridColor.isBound
            }

            override fun getStyleableProperty(node: GirdBackground): StyleableProperty<Color> {
                return node.mGridColor
            }
        }
    }

    private val mGridColor: StyleableObjectProperty<Color> by lazy {
        object : StyleableObjectProperty<Color>(defaultGirdColor) {
            override fun getCssMetaData(): CssMetaData<GirdBackground, Color> {
                return girdColor
            }

            override fun getBean(): Any {
                return this
            }

            override fun getName(): String {
                return girdColorPropertyName
            }

            override fun invalidated() {
                requestLayout()
            }
        }
    }

    init {
        isManaged = false
        isMouseTransparent = false
        styleClass.add(internalStyleClass)
        mGrid.strokeProperty().bind(mGridColor)
        children.add(mGrid)
        isCacheShape = true
        cacheHint = CacheHint.SPEED
    }

    override fun resize(pWidth: Double, pHeight: Double) {
        super.resize(pWidth, pHeight)
        if (mLastHeight != pHeight || mLastWidth != pWidth) {
            mLastHeight = pHeight
            mLastWidth = pWidth
            Platform.runLater {
                draw(pWidth, pHeight)
            }
        }
    }

    fun draw(pWidth: Double, pHeight: Double) {
        val spacing: Double = mGridSpacing.get()
        val hLineCount = ((pHeight + 1) / spacing).toInt().coerceIn(0, Int.MAX_VALUE)
        val vLineCount = ((pWidth + 1) / spacing).toInt().coerceIn(0, Int.MAX_VALUE)
        mGrid.elements.apply {
            clear()
            addAll(drawL(vLineCount, spacing, pHeight) + drawH(hLineCount, spacing, pWidth, pHeight))
        }
    }

    private fun drawL(vLineCount: Int, spacing: Double, pHeight: Double): Array<PathElement> {
        return (0 until vLineCount).flatMap { i ->
            val x = (i + 1) * spacing + halfPixelOffset
            listOf(lMoveTo(x), lLineTo(x, pHeight))
        }.toTypedArray()
    }

    private fun lLineTo(x: Double, pHeight: Double): LineTo {
        return lLineToCache[x, pHeight] ?: run {
            val c = LineTo(x, pHeight)
            lLineToCache.put(x, pHeight, c)
            c
        }
    }

    private fun lMoveTo(x: Double): MoveTo {
        return lMoveToCache[x, 0.0] ?: run {
            MoveTo(x, 0.0).apply {
                lMoveToCache.put(x, 0.0, this)
            }
        }
    }

    private fun drawH(hLineCount: Int, spacing: Double, pWidth: Double, pHeight: Double): Array<PathElement> {
        return (0 until hLineCount).flatMap { i ->
            val y = (i + 1) * spacing + halfPixelOffset
            listOf(hMoveTo(y), hLineTo(pWidth, y, pHeight))
        }.toTypedArray()
    }

    private fun hLineTo(pWidth: Double, y: Double, pHeight: Double): LineTo {
        return hLineToCache[pWidth, y] ?: run {
            LineTo(pWidth, y).apply {
                hLineToCache.put(pHeight, y, this)
            }
        }
    }

    private fun hMoveTo(y: Double): MoveTo {
        return hMoveToCache[0, y] ?: run {
            MoveTo(0.0, y).apply {
                hMoveToCache.put(0, y, this)
            }
        }
    }

    private fun <R, C, V> createHashBasedTable(rows: Int = 200, cols: Int = 200): HashBasedTable<R, C, V> {
        return HashBasedTable.create(rows, cols)
    }
}