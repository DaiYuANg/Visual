package org.visual.model.component.control

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.javafx.FontIcon
import java.util.Optional.ofNullable

class CacheFontIcon {
    private val internal = Object2ObjectArrayMap<Ikon, FontIcon>()

    fun create(icon: Ikon) {
        ofNullable(internal[icon]).orElseGet {
            val fc = FontIcon(icon)
            internal[icon] = fc
            fc
        }
    }
}
