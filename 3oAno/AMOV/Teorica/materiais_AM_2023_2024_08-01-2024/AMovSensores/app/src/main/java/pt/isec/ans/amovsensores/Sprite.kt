package pt.isec.ans.amovsensores

import android.graphics.Canvas

abstract class Sprite(var x: Float, var y: Float,
                      private var width: Float, private var height: Float,
                      private var speedX: Float, private var speedY: Float,
                      private var isStatic: Boolean) {
    companion object {
        private const val WALL_FORCE = 0f
    }
    private var minX = 0f
    private var maxX = 1000f
    private var minY = 0f
    private var maxY = 1000f
    protected var wallForce = WALL_FORCE

    abstract fun onDraw(c: Canvas?)

    fun setLimits(
        minX: Float, maxX: Float,
        minY: Float, maxY: Float
    ) {
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
    }

    /*fun getRect(): RectF {
        return RectF(x - width / 2, y - height / 2,
            x + width / 2, y + height / 2)
    }
    fun collision(x: Float, y: Float): Boolean {
        return getRect().contains(x, y)
    }
    fun collision(r: RectF?): Boolean {
        return getRect().intersect(r!!)
    }
    fun collision(other: Sprite): Boolean {
        return getRect().intersect(other.getRect())
    }*/
    open fun applyAcceleration(accX: Float, accY: Float,
                               elapsedTime: Float) {
        if (isStatic) return
        speedX += elapsedTime * accX
        speedY += elapsedTime * accY
        animate(elapsedTime)
    }

    open fun animate(elapsedTime: Float) {
        if (speedX == 0f && speedY == 0f) return

        val tempX = x + speedX * elapsedTime
        if (x < minX + width / 2) {
            x = minX + width / 2
            speedX = if (isStatic) -speedX else -wallForce * speedX
        } else if (x > maxX - width / 2) {
            x = maxX - width / 2
            speedX = if (isStatic) -speedX else -wallForce * speedX
        } else
            x = tempX

        val tempY = y + speedY * elapsedTime
        if (y < minY + height / 2) {
            y = minY + height / 2
            speedY = if (isStatic) -speedY else -wallForce * speedY
        } else if (y > maxY - height / 2) {
            y = maxY - height / 2
            speedY = if (isStatic) -speedY else -wallForce * speedY
        } else
            y = tempY
    }

}