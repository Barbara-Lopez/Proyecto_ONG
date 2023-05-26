package com.example.ong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
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
        var id=arguments?.getString("id") ?:"-1"
        var regsitro = RegistroNiebla()
        if (id == "-1"){
            binding.bEliminar.isEnabled = false
            binding.bEliminar.isVisible = false
            binding.buttonGuardarRegistro.isEnabled = true
            binding.buttonGuardarRegistro.isVisible = true
            binding.editTextFecha.isEnabled = true
            binding.spinnerNieblaSiNo.isEnabled = true
            binding.spinnerIntensidad.isEnabled = true
            binding.spinnerFranjaHoraria.isEnabled = true
            binding.spinnerLluviaSiNo.isEnabled = true
            binding.editTextDuracionLluvia.isEnabled = true
            binding.spinnerCortesAguaSiNo.isEnabled = true
            binding.editTextDuracionCorteAgua.isEnabled = true
        }else{
            binding.bEliminar.isEnabled = true
            binding.bEliminar.isVisible = true
            binding.buttonGuardarRegistro.isEnabled = false
            binding.buttonGuardarRegistro.isVisible = false
            binding.editTextFecha.isEnabled = false
            binding.spinnerNieblaSiNo.isEnabled = false
            binding.spinnerIntensidad.isEnabled = false
            binding.spinnerFranjaHoraria.isEnabled = false
            binding.spinnerLluviaSiNo.isEnabled = false
            binding.editTextDuracionLluvia.isEnabled = false
            binding.spinnerCortesAguaSiNo.isEnabled = false
            binding.editTextDuracionCorteAgua.isEnabled = false
            try {
                (activity as MainActivity).miViewModel.buscarPorId(id)
                (activity as MainActivity).miViewModel.registro.observe(activity as MainActivity){
                    regsitro=it
                    binding.editTextFecha.setText(it.fecha)
                    if (it.niebla.lowercase() == "si") {
                        binding.spinnerNieblaSiNo.setSelection(0)
                    }else{
                        binding.spinnerNieblaSiNo.setSelection(1)
                        binding.spinnerIntensidad.setSelection(0)
                        binding.spinnerFranjaHoraria.setSelection(0)
                    }
                    if(it.intensidadNiebla == "Intensa"){
                        binding.spinnerIntensidad.setSelection(1)
                    }
                    if(it.intensidadNiebla == "Normal"){
                        binding.spinnerIntensidad.setSelection(2)
                    }
                    if(it.intensidadNiebla == "Poco intensa"){
                        binding.spinnerIntensidad.setSelection(3)
                    }
                    if(it.franjaHoraria == "6:00 a 9:00"){
                        binding.spinnerFranjaHoraria.setSelection(1)
                    }
                    if(it.franjaHoraria == "9:00 a 12:00"){
                        binding.spinnerFranjaHoraria.setSelection(2)
                    }
                    if(it.franjaHoraria == "12:00 a 14:00"){
                        binding.spinnerFranjaHoraria.setSelection(3)
                    }
                    if(it.franjaHoraria == "15:00 a 18:00"){
                        binding.spinnerFranjaHoraria.setSelection(4)
                    }
                    if(it.franjaHoraria == "18:00 a 21:00"){
                        binding.spinnerFranjaHoraria.setSelection(5)
                    }
                    if(it.franjaHoraria == "21:00 a 24:00"){
                        binding.spinnerFranjaHoraria.setSelection(6)
                    }
                    if(it.franjaHoraria == "24:00 a 6:00"){
                        binding.spinnerFranjaHoraria.setSelection(7)
                    }
                    if (it.duracionLluvia == "0") {
                        binding.spinnerLluviaSiNo.setSelection(1)
                    }else{
                        binding.spinnerLluviaSiNo.setSelection(0)
                    }
                    binding.editTextDuracionLluvia.setText(it.duracionLluvia)
                    if (it.duracionCortesAgua == "0") {
                        binding.spinnerCortesAguaSiNo.setSelection(1)
                    }else{
                        binding.spinnerCortesAguaSiNo.setSelection(0)
                    }
                    binding.editTextDuracionCorteAgua.setText(it.duracionCortesAgua)
                }
            }catch (e: Exception) {
                Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()
            }

        }
        binding.bEliminar.setOnClickListener {
            try {
                (activity as MainActivity).miViewModel.borrar(id)
                Toast.makeText(activity, "Registro borrado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment)

            }catch (e: Exception) {
                Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonGuardarRegistro.setOnClickListener {
            try {
                if (binding.editTextFecha.text.toString().isEmpty()) {
                    throw Exception("Tiene que escribir la fecha")
                } else {
                    val p: Pattern =
                        Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})\$")
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
                var usuario = (activity as MainActivity).user
                var registroNiebla = RegistroNiebla("",fecha,niebla,intensidadNiebla,franjaHoraria,duracionLluvia,duracionCortesAgua,usuario)
                (activity as MainActivity).miViewModel.insertar(registroNiebla)
                Toast.makeText(activity, "Registro creado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment)

            } catch (e: Exception) {
                Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as MainActivity).miViewModel.registro.removeObservers(activity as MainActivity)
    }
}