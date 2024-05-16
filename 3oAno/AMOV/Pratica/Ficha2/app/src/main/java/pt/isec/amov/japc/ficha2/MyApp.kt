package pt.isec.amov.japc.ficha2

import android.app.Application
import android.content.res.Configuration
import android.util.Log

class MyApp : Application() { //ter o my app permite que a applicaçao nao seja morta  porque o processo fica la, sendo assim é um sitio seguro para ter um hashmap para guardar coisas pois ela nao vai ser terminada

    public var xpto = 0
    private var _i = 0
    val i: Int
        get() = _i++

    override fun onCreate() {
        super.onCreate()

        Log.i(TAG + "App", "onCreate: $i")
    }

    override fun onLowMemory() {
        super.onLowMemory()

        Log.i(TAG + "App", "onLowMemory: $i")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        Log.i(TAG + "App", "onTrimMemory: $i")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.i(TAG + "App", "onConfigurationChanged: $i")
    }

    override fun onTerminate() {
        super.onTerminate()

        Log.i(TAG + "App", "onTerminate: $i")
    }
}