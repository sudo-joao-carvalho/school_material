package pt.isec.amov.japc.ficha2

import android.app.Activity
import android.os.Bundle
import android.util.Log

const val TAG = "P402LifeCycle";

class MyActivity : Activity() { //corespondente ao extends... temos que indicar logo o construtor mesmo que se ja por omissao

    private var _i = 0
    val i: Int
        get() = _i++

    /* b) */ val app : MyApp by lazy { application as MyApp } //by lazy quer dizer que so quando aceder ao app é que a var vai ser criada

    //val app1: MyApp = application as MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_activity) //definir qual é a vista da atividade
        // aqui referenciamos a nossa view com um inteiro pois quando os resources sao criados é lhe atribuido um inteiro

        //Forma 1
        /*if (savedInstanceState != null)
            _i = savedInstanceState.getInt("var_i")*/

        //Forma 2
        _i = savedInstanceState?.getInt("var_i") ?: 0 //se a primeira parte nao for executada o valor vai ser 0 por omissao

        //val xpto = (application as MyApp).xpto
        val xpto = app.xpto /* b) fica assim se tiver aquela linha la em cima, senao tenho que usar a linha de cima desta e fazer sempre o cast*/
        Log.i(TAG, "onCreate: xpto $xpto")
    }

    override fun onStart() {
        super.onStart()

        Log.i(TAG, "onStart: $i")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i(TAG, "onRestart: $i")
    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume: $i ${MyObject.value}")
    }

    override fun onPause() {
        super.onPause()

        (application as MyApp).xpto = _i
        Log.i(TAG, "onPause: $i ${MyObject.value}")
    }

    override fun onStop() {
        super.onStop()

        Log.i(TAG, "onStop: $i")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i(TAG, "onDestroy: $i")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i(TAG, "onSaveInstanceState: $i")
        //Bundle é tipo um hashMap
        outState.putInt("var_i", _i) //guarda a instancia
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.i(TAG, "onRestoreInstanceState: $i")

        _i = savedInstanceState.getInt("var_i") //recuperar a instancia que estava anteriormente (por exemplo quando rodamos o dispositivo a instancia é perdida, entao temos que guarda-la)
    }


}