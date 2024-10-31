package io.jadu.diwali2024

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

internal fun Offset.x(value: Float): Offset = Offset(x = value, y = y)
internal fun Offset.y(value: Float): Offset = Offset(x = x, y = value)
internal fun Offset.xTimes(value: Float): Offset = Offset(x = x * value, y = y)
internal fun Offset.yTimes(value: Float): Offset = Offset(x = x, y = y * value)

internal fun Random.nextFloat(from: Float, until: Float): Float
        = nextDouble(from = from.toDouble(), until = until.toDouble()).toFloat()

internal fun Float.sq(): Float = this * this

internal fun Offset.mag(): Float = sqrt(x.sq() + y.sq()) // Pythagoras Theorem => c.sq() = a.sq() + b.sq()

internal fun Offset.normalize(): Offset
        = Offset(
    x = x / this.mag(),
    y = y / this.mag()
)

internal fun Offset.setMag(mag: Float): Offset = this.normalize().times(mag)


internal fun Offset.Companion.random(xMin: Float = 0f, xMax: Float, yMin: Float = 0f, yMax: Float): Offset
        = Offset(
    x = Random.nextFloat(from = xMin, until = xMax+1),
    y = Random.nextFloat(from = yMin, until = yMax+1)
)

internal fun Offset.Companion.randomMirror(xMax: Float, yMax: Float): Offset
        = Offset(
    x = Random.nextFloat(from = -xMax, until = xMax+1),
    y = Random.nextFloat(from = -yMax, until = yMax+1)
)

internal fun Offset.Companion.randomX(xMax: Float, xMin: Float = 0f): Offset
        = Offset(
    x = Random.nextFloat(from = xMin, until = xMax+1),
    y = 0f
)

internal fun Offset.Companion.randomY(yMax: Float, yMin: Float = 0f): Offset
        = Offset(
    x = 0f,
    y = Random.nextFloat(from = yMin, until = yMax+1),
)

internal fun Offset.Companion.randomXMirror(xMax: Float): Offset
        = Offset(
    x = Random.nextFloat(from = -xMax, until = xMax+1),
    y = 0f
)

internal fun Offset.Companion.randomYMirror(yMax: Float): Offset
        = Offset(
    x = 0f,
    y = Random.nextFloat(from = -yMax, until = yMax+1),
)

internal fun Offset.Companion.random2D(): Offset {
    val angle = Random.nextDouble() * (2*PI)
    val length = 1
    return Offset(
        x = (length * cos(angle)).toFloat(),
        y = (length * sin(angle)).toFloat()
    )
}