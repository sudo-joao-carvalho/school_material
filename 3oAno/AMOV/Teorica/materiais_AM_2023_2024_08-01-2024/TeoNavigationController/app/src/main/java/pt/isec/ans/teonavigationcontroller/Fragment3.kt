package pt.isec.ans.teonavigationcontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class Fragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_3, container, false)
        root.findViewById<Button>(R.id.btn1).setOnClickListener {
            //findNavController().navigate(R.id.action_fragment3_to_fragment2)
            findNavController().navigate(R.id.fragment2)
            
            (activity as MainActivity).updateMsg(requireContext().getString(R.string.fragment_3))
                // A solução adequada seria usar ViewModel+LiveData
        }
        return root
    }


}