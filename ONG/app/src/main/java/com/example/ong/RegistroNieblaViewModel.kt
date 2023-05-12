package com.example.ong

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistroNieblaViewModel(private val miRepositorio: Repositorio): ViewModel()
    {
        lateinit var listaPeliculas: LiveData<List<RegistroNiebla>>
        lateinit var listaUser: LiveData<List<Usuario>>

        fun insertar(miRegistroNiebla: RegistroNiebla) = viewModelScope.launch {
            miRepositorio.insertar(miRegistroNiebla)
        }

        fun cogerUsuario(nombre: String) = viewModelScope.launch {
            listaUser = miRepositorio.cogerUsuario(nombre)
        }

        fun insertarUsuario(user:Usuario) = viewModelScope.launch {
            miRepositorio.insertarUsuario(user)
        }

        fun mostrarTodoUser(user:String) = viewModelScope.launch {
            listaPeliculas = miRepositorio.mostrarTodoUser(user)
        }

        fun insertarCaudalimetro(caudalimetro: Caudalimetro) = viewModelScope.launch {
          miRepositorio.insertarCaudalimetro(caudalimetro)
        }
    }

class PeliculaViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroNieblaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RegistroNieblaViewModel(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }

}
