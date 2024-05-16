package pt.isec.ans.amovsensores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.AttributeSet
import android.view.Surface
import android.view.View
import kotlin.random.Random

class Board @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr : Int = 0,
    defStyleRes : Int = 0
) :
    View(context,attrs,defStyleAttr,defStyleRes), SensorEventListener {
    companion object {
        private const val SPEED_FACTOR = 5f
    }

    private var lstSprites: ArrayList<Sprite> = ArrayList()

    init {
        var colorBall = Color.BLACK
        var nrBalls = 1
        context.theme?.obtainStyledAttributes(attrs,R.styleable.Board,0,0)?.apply {
            nrBalls = getInt(R.styleable.Board_nrBalls,1)
            colorBall = getInt(R.styleable.Board_colorBall,Color.MAGENTA)
        }
        repeat(nrBalls) {
            lstSprites.add(Ball(Random.nextFloat()*600, Random.nextFloat() * 600, colorBall))
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (s in lstSprites) { s.onDraw(canvas) }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        for (s in lstSprites) {
            s.setLimits(0f, w.toFloat(), 0f, h.toFloat())
        }
    }
    private fun applyAcceleration(accX: Float, accY: Float,
                                  elapsedTime: Float) {
        for (s in lstSprites) {
            s.applyAcceleration(accX, accY, elapsedTime)
        }
        invalidate()
    }

    private var lastTime: Long = -1

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER && display != null) {
            if (lastTime > 0) {
                var sx = 0f
                var sy = 0f
                when (display!!.rotation) {
                    Surface.ROTATION_0   -> { sx = -event.values[0]; sy = event.values[1]  }
                    Surface.ROTATION_90  -> { sx = event.values[1];  sy = event.values[0]  }
                    Surface.ROTATION_180 -> { sx = event.values[0];  sy = -event.values[1] }
                    Surface.ROTATION_270 -> { sx = -event.values[1]; sy = -event.values[0] }
                }
                val deltaTime = SPEED_FACTOR * (event.timestamp - lastTime) / 1000000000.0f
                applyAcceleration(sx, sy, deltaTime)
            }
            lastTime = event.timestamp
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}