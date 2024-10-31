package io.jadu.diwali2024

import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.min

data class HSBColor(
    val hu: Int,
    val saturation: Int,
    val brightness: Int
) {
    fun toRGBColor(): Color {
        val newSaturation = saturation / 100
        val newBrightness = brightness / 100

        val k: (Int) -> Int = { (it + hu / 60) % 6 }
        val f: (Int) -> Int = {
            val minK = min(k(it), min(4-k(it), 1))
            newBrightness * (1 - newSaturation * max(0, minK))
        }

        return Color(red = 255 * f(5), green = 255 * f(3), blue = 255 * f(1))
    }
}