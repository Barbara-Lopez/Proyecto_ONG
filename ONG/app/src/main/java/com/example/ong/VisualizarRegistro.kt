package com.example.ong

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ong.databinding.FragmentFifthBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VisualizarRegistro.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisualizarRegistro : Fragment() {
    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var usser1 = (activity as MainActivity).user
        (activity as MainActivity).miViewModel.mostrarTodoUser(usser1)

        (activity as MainActivity).miViewModel.listaPeliculas.observe(activity as MainActivity){
            miRecycleView = binding.rvPosiciones
            miRecycleView.layoutManager = LinearLayoutManager(activity)
            miRecycleView.adapter = Adaptador(it)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}