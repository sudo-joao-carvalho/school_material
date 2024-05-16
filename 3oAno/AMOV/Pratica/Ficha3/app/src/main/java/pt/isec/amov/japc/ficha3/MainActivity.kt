package pt.isec.amov.japc.ficha3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import pt.isec.amov.japc.ficha3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener { //é preciso derivar o AppCompactActivity ao inves de Activity para podermos usar a Support Library
    //lateinit var tvMessage1 : TextView //lateinit quer dizer qe vai ser iniciado mais tarde

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //layout inflater tem a capacidade de ler xml e transformar todos os objetos em kotlin
        //com o binding ja nao é preciso fazer a linha de baixo
        //setContentView(R.layout.activity_main)

        setContentView(binding.root)
        binding.tvMessage1.text = "DEIS-Amov"

        //isto de ter referencias para os objetos com os ids pode dar problemas se alterarmos o id por exemplo e vai estragar o programa, por isso vamos ao build.grade.kts (Module :app) e metemos em android
        /*buildFeatures{
            viewBinding = true;
        }*/
        //tvMessage1 = findViewById(R.id.tvMessage1)
        //val tvMessage2 = findViewById<TextView>(R.id.tvMessage2) //se nao meti la o tipo antes posso meter ali

        //tvMessage1.text = "DEIS-Amov"

        /*
        *
        * PROCESSAR OS BOTOES
        *
        */

        //implementar View.onClickListenerx
        binding.btnBotao1.setOnClickListener(this)
        binding.btnBotao2.setOnClickListener(ProcBotao2(binding.tvMessage1, "B2"))
        //binding.btnBotao3.setOnClickListener(ProcBotao3)
        binding.btnBotao3.setOnClickListener(ProcBotao3Lambda)
        binding.btnBotao4.setOnClickListener{//forma mais usada
            binding.tvMessage1.text = "B4"
        }
    }

    //CLASSE ANONIMA que implementa aquela interface

    val ProcBotao3 = object : View.OnClickListener{
        override fun onClick(v: View?) {
            binding.tvMessage1.text = "B3"
        }
    }

    //LAMBDA
    val ProcBotao3Lambda = View.OnClickListener{view ->
        val btn = view as Button
        binding.tvMessage1.text = "B3L ${btn.text}"
    }

    class ProcBotao2(val tv : TextView, val txt : String) : View.OnClickListener{
        override fun onClick(v: View?) {
            //val tv = (v.context as MainActivity).findViewById<TextView>(R.id.btnBotao1);
            tv.text = txt
        }
    }

    override fun onClick(v: View?) {
        binding.tvMessage1.text = "B1"
    }

    fun onBotao5(view: View) {
        binding.tvMessage1.text = "B5"
    }

    /*fun xpto(){
        tvMessage1.text = "oi";
    }*/
}