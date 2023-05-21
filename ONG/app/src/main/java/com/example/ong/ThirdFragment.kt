package com.example.ong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
            try {
                if (binding.editTextFecha.text.toString().isEmpty()) {
                    throw Exception("Tiene que escribir la fecha")
                } else {
                    val p: Pattern =
                        Pattern.compile("^(?:0?[1-9]|1[1-2])([\\-/.])(3[01]|[12][0-9]|0?[1-9])\\1\\d{4}\$")
                    val m: Matcher = p.matcher(binding.editTextFecha.text.toString())
                    if (!m.matches()) {
                        throw Exception("La fecha tiene que ser: DD-MM-AAAA , DD/MM/AAAA ")
                    }
                }
                if (binding.spinnerNieblaSiNo.selectedItem.toString().isEmpty()) {
                    throw Exception("Seleccione si hay niebla o no")
                } else {
                    if (binding.spinnerNieblaSiNo.selectedItem.toString().lowercase() == "si") {
                        if (binding.spinnerIntensidad.selectedItem.toString().isEmpty()) {
                            throw Exception("Seleccione la intensidad de la niebla")
                        } else {
                            if (binding.spinnerFranjaHoraria.selectedItem.toString().isEmpty()) {
                                throw Exception("Seleccione la franja horaria en la que ha habido niebla")
                            }
                        }
                    }
                }
                if (binding.spinnerLluviaSiNo.selectedItem.toString().isEmpty()) {
                    throw Exception("Seleccione si ha habido llubia o no")
                } else {
                    if (binding.spinnerLluviaSiNo.selectedItem.toString().lowercase() == "si") {
                        if (binding.editTextDuracionLluvia.text.toString().isEmpty()) {
                            throw Exception("Escriba la duracion de la lluvia si ha puesto que si antes")
                        } else {
                            if (binding.editTextDuracionLluvia.text.toString().toFloat() <= 0) {
                                throw Exception("Escriba la duración de la lluvia en numeros mayores al 0 (1.00 ; 0.45)")
                            }
                        }
                    }
                }
                if (binding.spinnerCortesAguaSiNo.selectedItem.toString().isEmpty()) {
                    throw Exception("Seleccione si ha habido cortes de agua o no")
                } else {
                    if (binding.spinnerCortesAguaSiNo.selectedItem.toString().lowercase() == "si") {
                        if (binding.editTextDuracionCorteAgua.text.toString().isEmpty()) {
                            throw Exception("Escriba la duracion de los cortes de agua si ha puesto que si antes")
                        } else {
                            if (binding.editTextDuracionCorteAgua.text.toString().toFloat() <= 0) {
                                throw Exception("Escriba la duración de los cortes de agua en numeros mayores al 0 (1.00 ; 0.45)")
                            }
                        }
                    }
                }
                var fecha = binding.editTextFecha.text.toString()
                var niebla = binding.spinnerNieblaSiNo.selectedItem.toString().lowercase()
                var intensidadNiebla = ""
                var franjaHoraria = ""

                if (binding.spinnerNieblaSiNo.selectedItem.toString().lowercase() == "si"){
                    intensidadNiebla = binding.spinnerIntensidad.selectedItem.toString().lowercase()
                    franjaHoraria = binding.spinnerFranjaHoraria.selectedItem.toString().lowercase()
                }
                var duracionLluvia = "0"
                if (binding.spinnerLluviaSiNo.selectedItem.toString().lowercase() == "si"){
                     duracionLluvia = binding.editTextDuracionLluvia.text.toString()
                }

                var duracionCortesAgua = "0"
                if (binding.spinnerCortesAguaSiNo.selectedItem.toString().lowercase() == "si"){
                    duracionCortesAgua = binding.editTextDuracionCorteAgua.text.toString()
                }

                var registroNiebla = RegistroNiebla(fecha,niebla,intensidadNiebla,franjaHoraria,duracionLluvia,duracionCortesAgua,(activity as MainActivity).user)
                (activity as MainActivity).miViewModel.insertar(registroNiebla)
                findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment)

            } catch (e: Exception) {
                Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}