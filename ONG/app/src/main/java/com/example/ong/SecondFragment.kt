package com.example.ong

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ong.databinding.FragmentSecondBinding
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGuardarRegistro.setOnClickListener {
            try {
                if(binding.editTextUsuarioRegistro.text.toString().isEmpty()){
                    throw Exception("El usuario no puede estar vacío")
                }
                if(binding.editTextPasswordRegistro.text.toString().isEmpty()){
                    throw Exception("La contraseña no puede estar vacío")
                }
                if(binding.spinnerRegion.selectedItem.toString().isEmpty()){
                    throw Exception("Selecione una region, no puede estar vacío")
                }
                var user = binding.editTextUsuarioRegistro.text.toString()
                var passwd = binding.editTextPasswordRegistro.text.toString()
                var region = binding.spinnerRegion.selectedItem.toString()
                var repe = false
                (activity as MainActivity).miViewModel.cogerUsuario(user)
                (activity as MainActivity).miViewModel.listaUser.observe(activity as MainActivity){

                    if (it.isEmpty() == false) {
                        Toast.makeText(activity,"Error al crear usuario, ya existe",Toast.LENGTH_LONG).show()
                        repe = true

                    }
                    if (repe == false){
                        var usuario:Usuario = Usuario(user,passwd,region)
                        (activity as MainActivity).miViewModel.insertarUsuario(usuario)
                        (activity as MainActivity).user = user
                        findNavController().navigate(R.id.action_SecondFragment_to_fifthFragment)
                    }else{
                        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                    }
                }




            }catch (e: Exception){
                Toast.makeText(activity,""+e.message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}