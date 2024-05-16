package data

import kotlin.random.Random

object CustomRandom {
    internal var overrideValue: Int? = null

    fun nextInt(): Int {
        return overrideValue ?: Random.nextInt(1000)
    }
}