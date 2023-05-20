package com.example.ong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ong.databinding.FragmentThirdBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGuardarRegistro.setOnClickListener {
            if (binding.editTextFecha.text.toString().isEmpty()){
                Toast.makeText(activity,"La fecha no puede estar vacío", Toast.LENGTH_LONG).show()
            }else{
                val p: Pattern = Pattern.compile("^(?:0?[1-9]|1[1-2])([\\-/.])(3[01]|[12][0-9]|0?[1-9])\\1\\d{4}\$")
                val m: Matcher = p.matcher(binding.editTextFecha.text.toString())
                if(m.matches()){
                    if (binding.spinnerNieblaSiNo.selectedItem.toString().isEmpty()){
                        Toast.makeText(activity,"Seleccione si hay niebla o no", Toast.LENGTH_LONG).show()
                    }else{
                        if (binding.spinnerNieblaSiNo.selectedItem.toString().lowercase() == "si"){
                            if (binding.spinnerIntensidad.selectedItem.toString().isEmpty()){
                                Toast.makeText(activity,"Seleccione la intensidad de la niebla", Toast.LENGTH_LONG).show()
                            }else{
                                if (binding.spinnerLluviaSiNo.selectedItem.toString().lowercase() == "si") {
                                    if (binding.editTextDuracionLluvia.text.toString().isEmpty()) {
                                        Toast.makeText(activity,"Seleccione la duracion de la lluvia si ha puesto que si antes", Toast.LENGTH_LONG).show()
                                    }else{
                                        if (binding.editTextDuracionLluvia.text.toString().toFloat() > 0) {
                                            if (binding.spinnerCortesAguaSiNo.selectedItem.toString().lowercase() == "si") {
                                                if (binding.editTextDuracionLluvia.text.toString().isEmpty()) {
                                                    Toast.makeText(activity,"Seleccione la duracion de la lluvia si ha puesto que si antes", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                        }else{
                                            Toast.makeText(activity,"escriba la duración de la lluvia en numeros (1.00 ; 0.45)", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    Toast.makeText(activity,"La fecha tiene que ser: DD-MM-AAAA , DD/MM/AAAA ", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}