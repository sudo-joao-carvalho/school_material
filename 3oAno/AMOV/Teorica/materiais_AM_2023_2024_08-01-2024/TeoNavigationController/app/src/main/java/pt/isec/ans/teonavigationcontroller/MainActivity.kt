package pt.isec.ans.teonavigationcontroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fragment_base).addOnDestinationChangedListener { controller, destination, arguments ->
            val str = "Target: ${destination.label}"
            findViewById<TextView>(R.id.tvMsg1).text = str
        }
    }

    fun updateMsg(msg : String) {
        val str = "Order from: $msg"
        findViewById<TextView>(R.id.tvMsg2).text = str
    }
}