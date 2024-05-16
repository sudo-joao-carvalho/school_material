package pt.isec.amov.japc.calculatorex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import pt.isec.amov.japc.calculatorex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var strNumber = ""
    var oldNumber = 0.0
    var operator = 0
    var isNewValue = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PROCESSAMENTO DE BOTOES //sem implementar View.OnClickListener
        /*binding.btnOne.setOnClickListener{
            binding.tvDisplay.text = "1,0"
        }
        binding.btnTwo.setOnClickListener{
            binding.tvDisplay.text = "2,0"
        }
        binding.btnThree.setOnClickListener{
            binding.tvDisplay.text = "3,0"
        }
        binding.btnFour.setOnClickListener{
            binding.tvDisplay.text = "4,0"
        }
        binding.btnFive.setOnClickListener{
            binding.tvDisplay.text = "5,0"
        }
        binding.btnSix.setOnClickListener{
            binding.tvDisplay.text = "6,0"
        }
        binding.btnSeven.setOnClickListener{
            binding.tvDisplay.text = "7,0"
        }
        binding.btnEight.setOnClickListener{
            binding.tvDisplay.text = "8,0"
        }
        binding.btnNine.setOnClickListener{
            binding.tvDisplay.text = "9,0"
        }
        binding.btnAC.setOnClickListener{
            binding.tvDisplay.text = "0,0"
        }
        binding.btnPercentage.setOnClickListener{
            binding.tvDisplay.text = "%"
        }
        binding.btnDiv.setOnClickListener{
            binding.tvDisplay.text = "/"
        }
        binding.btnMul.setOnClickListener{
            binding.tvDisplay.text = "*"
        }
        binding.btnPlus.setOnClickListener{
            binding.tvDisplay.text = "+"
        }
        binding.btnMin.setOnClickListener{
            binding.tvDisplay.text = "-"
        }
        binding.btnEqual.setOnClickListener{
            binding.tvDisplay.text = "="
        }
        binding.btnDot.setOnClickListener{
            binding.tvDisplay.text = "."
        }*/

        /*binding.btnZero.setOnClickListener(ProcNumberButton)
        binding.btnOne.setOnClickListener(ProcNumberButton)
        binding.btnTwo.setOnClickListener(ProcNumberButton)
        binding.btnThree.setOnClickListener(ProcNumberButton)
        binding.btnFour.setOnClickListener(ProcNumberButton)
        binding.btnFive.setOnClickListener(ProcNumberButton)
        binding.btnSix.setOnClickListener(ProcNumberButton)
        binding.btnSeven.setOnClickListener(ProcNumberButton)
        binding.btnEight.setOnClickListener(ProcNumberButton)
        binding.btnNine.setOnClickListener(ProcNumberButton)*/

        /*binding.btnPlus.setOnClickListener{
            oldNumber = strNumber.toDoubleOrNull() ?: 0.0 //double or null para o caso de ser um valor invalido
            operator = it.id
            isNewValue = true
        }*/
        binding.btnPlus.setOnClickListener(procOperator)
        binding.btnMin.setOnClickListener(procOperator)
        binding.btnMul.setOnClickListener(procOperator)
        binding.btnDiv.setOnClickListener(procOperator)
    }

    /*val ProcNumberButton = View.OnClickListener { view ->
        val btn = view as Button
        strNumber += "${btn.text}"
        binding.tvDisplay.text = strNumber
    }*/

    val procOperator = View.OnClickListener {

        if(operator != 0)
            calculate()
        else
            oldNumber = strNumber.toDoubleOrNull() ?: 0.0 //double or null para o caso de ser um valor invalido
        operator = it.id
        isNewValue = true
    }

    fun onDigit(view: View) {
        val btn = view as Button

        if(isNewValue)
            strNumber = btn.text.toString()
        else
            strNumber += btn.text
        isNewValue = false
        binding.tvDisplay.text = strNumber
    }

    fun calculate(){

        val newNumber = strNumber.toDoubleOrNull() ?: 0.0
        val result = when (operator){ //operator é um id entao tenho que usar o id
            R.id.btnPlus /* binding.btnPlus.id */ -> oldNumber + newNumber
            else -> 0.0
        }

        binding.tvDisplay.text = result.toString()
        oldNumber = result
        isNewValue = true
    }

}