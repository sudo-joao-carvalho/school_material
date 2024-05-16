package pt.isec.ans.teonavigationcontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_1, container, false)
        root.findViewById<Button>(R.id.btn1).setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment3)
            (activity as MainActivity).updateMsg(requireContext().getString(R.string.fragment_1))
                // A solução adequada seria usar ViewModel+LiveData

        /* Se não se pretender voltar a este fragmento:
            findNavController().run {
                popBackStack()
                navigate(R.id.fragment3)
            }
             ver também o app:popUpToInclusive nas actions
            */

        }
        return root
    }

}