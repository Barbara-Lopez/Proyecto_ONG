package com.example.ong

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parse.ParseObject
import com.parse.ParseQuery

class BBDDParse {
    fun mostrarTodoUser(user:String): LiveData<List<RegistroNiebla>> {
        val misRegistros: MutableLiveData<List<RegistroNiebla>> = MutableLiveData()
        // Configure Query
        val query = ParseQuery.getQuery<ParseObject>("RegistroNiebla")
        // Sorts the results in ascending order by the itemName field
        query.whereEqualTo("user",user)
        query.findInBackground { objects, e ->
            if (e == null) {
                // Adding objects into the Array
                val registro = objects.map { i ->
                    RegistroNiebla(i.objectId,
                        i.getString("fecha") ?: "",
                        i.getString("niebla") ?: "",
                        i.getString("intensidadNiebla") ?: "",
                        i.getString("franjaHoraria") ?: "",
                        i.getString("duracionLluvia") ?: "",
                        i.getString("duracionCortesAgua") ?: "",
                        i.getString("user") ?: "",
                    )
                }
                misRegistros.postValue(registro)
            }
        }
        return misRegistros
    }
    fun insertar(misRegistros:RegistroNiebla) {
        val registroNieblas = ParseObject("RegistroNiebla")
        registroNieblas.put("fecha", misRegistros.fecha)
        registroNieblas.put("niebla", misRegistros.niebla)
        registroNieblas.put("intensidadNiebla", misRegistros.intensidadNiebla)
        registroNieblas.put("franjaHoraria", misRegistros.franjaHoraria)
        registroNieblas.put("duracionLluvia", misRegistros.duracionLluvia)
        registroNieblas.put("duracionCortesAgua", misRegistros.duracionCortesAgua)
        registroNieblas.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message ->
                    throw Exception(message)
                    //Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
                }
            }
        }
    }
    fun cogerUsuario(nombre:String): LiveData<List<Usuario>> {
        val misUsuarios: MutableLiveData<List<Usuario>> = MutableLiveData()
        // Configure Query
        val query = ParseQuery.getQuery<ParseObject>("Usuario")
        // Sorts the results in ascending order by the itemName field
        query.whereEqualTo("nombre",nombre)
        query.findInBackground { objects, e ->
            if (e == null) {
                // Adding objects into the Array
                val user1 = objects.map { i ->
                    Usuario(
                        i.getString("nombre") ?: "",
                        i.getString("contrasena") ?: ""
                    )
                }
                misUsuarios.postValue(user1)
            }
        }
        return misUsuarios
    }

    fun insertarUsuario(usuario:Usuario) {
        val registroUsuario = ParseObject("Usuario")
        registroUsuario.put("nombre", usuario.nombre)
        registroUsuario.put("contrasena", usuario.contrasena)
        registroUsuario.put("lugar", usuario.lugar)
        registroUsuario.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message ->
                    throw Exception(message)
                    //Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
                }
            }
        }
    }
    fun insertarCaudalimetro(caudalimetro: Caudalimetro) {
        val registroCaudalimetro = ParseObject("Caudalimetro")
        registroCaudalimetro.put("metros3", caudalimetro.metros3)
        registroCaudalimetro.put("litros", caudalimetro.litros)
        registroCaudalimetro.put("mililitros", caudalimetro.mililitros)
        registroCaudalimetro.put("user", caudalimetro.user)
        registroCaudalimetro.put("fecha", caudalimetro.fecha)
        registroCaudalimetro.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message ->
                    throw Exception(message)
                    //Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show() }
                }
            }
        }
    }

}