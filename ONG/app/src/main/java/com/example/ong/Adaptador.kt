package com.example.ong

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var registros: LiveData<List<RegistroNiebla>>) : RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder (v: View) : RecyclerView.ViewHolder(v){
        var tvFecha : TextView
        var tvNiebla : TextView
        var tvIntensidadNiebla: TextView
        var tvFranjaHoraria: TextView
        var tvDuracionLluvia : TextView
        var tvDuracionCortesAgua : TextView
        var id:Int=-1
        init {
            tvFecha = v.findViewById(R.id.tvFecha)
            tvNiebla = v.findViewById(R.id.tvNiebla)
            tvIntensidadNiebla = v.findViewById(R.id.tvIntensidadNiebla)
            tvFranjaHoraria = v.findViewById(R.id.tvFranjaHoraria)
            tvDuracionLluvia = v.findViewById(R.id.tvDuracionLluvia)
            tvDuracionCortesAgua = v.findViewById(R.id.tvDuracionCortesAgua)
            /*v.setOnClickListener {
                val bundle = bundleOf("id" to id)
                v.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }*/
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_registro,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return registros.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var registro = registros[position]
        holder.tvFecha.text = registro.fecha
        holder.tvNiebla.text = registro.niebla
        holder.tvIntensidadNiebla.text= registro.intensidadNiebla
        holder.tvFranjaHoraria.text= registro.franjaHoraria
        holder.tvDuracionLluvia.text= registro.duracionLluvia
        holder.tvDuracionCortesAgua.text= registro.duracionCortesAgua
        holder.id = position
    }
}