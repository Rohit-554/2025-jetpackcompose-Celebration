package io.jadu.diwali2024

fun lerp(start: Float, stop: Float, fraction: Float): Float = start + (stop - start) * fraction


fun map(value: Float, min1: Float, max1: Float, min2: Float, max2: Float): Float {
    return (value - min1) / (max1 - min1) * (max2 - min2) + min2
}