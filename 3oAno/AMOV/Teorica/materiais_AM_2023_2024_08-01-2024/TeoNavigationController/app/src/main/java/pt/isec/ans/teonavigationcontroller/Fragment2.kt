package pt.isec.ans.teonavigationcontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_2, container, false)
        root.findViewById<Button>(R.id.btn1).setOnClickListener {
            findNavController().navigate(R.id.action_global_fragment1)
            (activity as MainActivity).updateMsg(requireContext().getString(R.string.fragment_2))
                // A solução adequada seria usar ViewModel+LiveData
        }
        return root
    }

}