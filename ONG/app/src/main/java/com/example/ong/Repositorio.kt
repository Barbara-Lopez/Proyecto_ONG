package com.example.ong

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class Repositorio(val miBBDDParse: BBDDParse) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertar(miRegistroNiebla: RegistroNiebla){
        miBBDDParse.insertar(miRegistroNiebla)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun cogerUsuario(nombre: String): LiveData<List<Usuario>> {
        return miBBDDParse.cogerUsuario(nombre)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertarUsuario(user:Usuario) {
        return miBBDDParse.insertarUsuario(user)
    }

    @Suppress("RedundatSuspendModifier")
    @WorkerThread
    suspend fun mostrarTodoUser(user:String): LiveData<List<RegistroNiebla>> {
        return miBBDDParse.mostrarTodoUser(user)
    }

    @Suppress("RedundatSuspendModifier")
    @WorkerThread
    suspend fun insertarCaudalimetro(caudalimetro: Caudalimetro){
        miBBDDParse.insertarCaudalimetro(caudalimetro)
    }

}