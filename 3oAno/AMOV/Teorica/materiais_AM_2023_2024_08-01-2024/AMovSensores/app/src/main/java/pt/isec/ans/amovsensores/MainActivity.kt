package pt.isec.ans.amovsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import pt.isec.ans.amovsensores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TEST_SENSORS = true
        private const val TEST_SENSORS_ONLY = false
    }

    private lateinit var b : ActivityMainBinding
    private lateinit var sm : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        sm = getSystemService(SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        if (TEST_SENSORS) {
            startSensors()
            if (TEST_SENSORS_ONLY) return
        }

        hideSystemUI()
        //window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { sensor ->
            sm.registerListener(b.board,sensor,SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        if (TEST_SENSORS) {
            stopSensors()
            if (TEST_SENSORS_ONLY) return
        }
        //window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        showSystemUI()
        sm.unregisterListener(b.board)
    }

    private fun startSensors() {
        val lstSensors = sm.getSensorList(Sensor.TYPE_ALL)
        //val lstSensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER)

        lstSensors.forEach {
            Log.i("Sensor list", "Sensor: " +
                    "${it.stringType}, ${it.type}, ${it.name}, " +
                    "0-${it.maximumRange}, ${it.power} mA, " +
                    "${it.resolution}, ${it.vendor}, ${it.version}")
            //sm.registerListener(sensorListener,it,
            //    SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun stopSensors() {
        //sm.unregisterListener(sensorListener)
    }

    val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event == null)
                return
            val sensor = event.sensor
            var str = "${sensor.stringType} "
            event.values.forEach { str += String.format(" %.4f",it)  }
            Log.i("Sensor", str)
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
    }

}