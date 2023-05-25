package com.example.ong

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistroNieblaViewModel(private val miRepositorio: Repositorio): ViewModel()
    {
        lateinit var listaRegistros: LiveData<List<RegistroNiebla>>
        lateinit var listaUser: LiveData<List<Usuario>>
        lateinit var registro: LiveData<RegistroNiebla>

        fun insertar(miRegistroNiebla: RegistroNiebla) = viewModelScope.launch {
            miRepositorio.insertar(miRegistroNiebla)
        }
        fun borrar(id: String) = viewModelScope.launch {
            miRepositorio.borrar(id)
        }
        fun buscarPorId(id:String) = viewModelScope.launch {
            registro = miRepositorio.buscarPorId(id)
        }

        fun cogerUsuario(nombre: String) = viewModelScope.launch {
            listaUser = miRepositorio.cogerUsuario(nombre)
        }

        fun insertarUsuario(user:Usuario) = viewModelScope.launch {
            miRepositorio.insertarUsuario(user)
        }

        fun mostrarTodoUser(user:String) = viewModelScope.launch {
            listaRegistros = miRepositorio.mostrarTodoUser(user)
        }

        fun insertarCaudalimetro(caudalimetro: Caudalimetro) = viewModelScope.launch {
          miRepositorio.insertarCaudalimetro(caudalimetro)
        }
    }

class RegistroNieblaViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroNieblaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RegistroNieblaViewModel(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }

}
