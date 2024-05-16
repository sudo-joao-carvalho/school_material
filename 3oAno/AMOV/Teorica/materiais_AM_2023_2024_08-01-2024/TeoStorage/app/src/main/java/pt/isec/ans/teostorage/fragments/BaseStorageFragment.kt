package pt.isec.ans.teostorage.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import pt.isec.ans.teostorage.R




abstract class BaseStorageFragment : Fragment() {
    companion object {
        const val FILENAME = "AMOV_"
    }

    interface Result {
        fun show(string: String)
    }

    open val txtTitle : String
        get() = "Title"
    open val txtButton1 : String
        get() = "Button #1"
    open val txtButton2 : String
        get() = "Button #2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_base_storage, container, false)
        v.findViewById<TextView>(R.id.tvTitle).apply {
            text = txtTitle
        }
        v.findViewById<Button>(R.id.btn1).apply {
            text = txtButton1
            setOnClickListener { onButton1(this) }
        }
        v.findViewById<Button>(R.id.btn2).apply {
            text = txtButton2
            setOnClickListener { onButton2(this) }
        }
        return v
    }

    abstract fun onButton1(v: View)

    abstract fun onButton2(v: View)

    fun showResult(string: String) {
        (activity as? Result)?.show(string)
    }
}

