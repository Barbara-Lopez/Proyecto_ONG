package com.example.ong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ong.databinding.FragmentFourthBinding
import java.text.SimpleDateFormat
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FourthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bEnviar.setOnClickListener {
            try {
                if (binding.editTextMetrosCubicos.text.toString().isEmpty()){
                    throw Exception("Tiene que escribir los metros cubicos")
                }
                if (binding.editTextLitros.text.toString().isEmpty()){
                    throw Exception("Tiene que escribir los litros")
                }
                if (binding.editTextMililitros.text.toString().isEmpty()){
                    throw Exception("Tiene que escribir los mililitros")
                }
                var metros3 = binding.editTextMetrosCubicos.text.toString()
                var litros = binding.editTextLitros.text.toString()
                var mililitros = binding.editTextMililitros.text.toString()
                val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
                val currentDateandTime: String = simpleDateFormat.format(Date())
                var caudalimetro = Caudalimetro(metros3.toInt(),litros.toInt(),mililitros.toInt(),(activity as MainActivity).user
                ,currentDateandTime)
                (activity as MainActivity).miViewModel.insertarCaudalimetro(caudalimetro)
                findNavController().navigate(R.id.action_fourthFragment_to_fifthFragment)

            }catch (e: Exception) {
                Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}