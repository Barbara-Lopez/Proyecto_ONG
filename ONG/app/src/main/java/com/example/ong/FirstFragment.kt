package com.example.ong

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ong.databinding.FragmentFirstBinding
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btRegistrarse.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btIniciarSesion.setOnClickListener {
            try {
                if(binding.editTextUsuario.text.toString().isEmpty()){
                    throw Exception("El usuario no puede estar vacío")
                }
                if(binding.editTextPassword.text.toString().isEmpty()){
                    throw Exception("La contraseña no puede estar vacío")
                }

                var usuario = binding.editTextUsuario.text.toString()
                var passwd = binding.editTextPassword.text.toString()

                (activity as MainActivity).miViewModel.cogerUsuario(usuario)
                (activity as MainActivity).miViewModel.listaUser.observe(activity as MainActivity){

                    if (it.isEmpty()) {
                        Toast.makeText(activity,"Usuario no encontrado, vuelva a escribirlo",Toast.LENGTH_LONG).show()
                        binding.editTextUsuario.text.clear()
                        binding.editTextPassword.text.clear()
                    }else{
                        if (it[0].contrasena == passwd){
                            (activity as MainActivity).user = usuario
                            findNavController().navigate(R.id.action_FirstFragment_to_fifthFragment)
                        }else{
                            Toast.makeText(activity,"Contraseña erronea, vuelva a escribirlo",Toast.LENGTH_LONG).show()
                            binding.editTextUsuario.text.clear()
                            binding.editTextPassword.text.clear()
                        }
                    }
                }
            }catch (e:Exception){
                Toast.makeText(activity,""+e.message,Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}