package com.example.practicaroom.fragments.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.databinding.ItemClasificacionBinding
import com.example.practicaroom.database.entities.ClasificacionEntity

class ClasificacionAdapter:
    RecyclerView.Adapter<ClasificacionAdapter.ClasificacionHolder> (){
        private var listadoClasificacion = emptyList<ClasificacionEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClasificacionHolder {
        val binding = ItemClasificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClasificacionHolder(binding)
    }

    override fun onBindViewHolder(holder: ClasificacionHolder, position: Int) {
        holder.bind(
            listadoClasificacion[position]
        )
    }

    override fun getItemCount(): Int = listadoClasificacion.size

    fun setData(clasificaciones: List<ClasificacionEntity>){
        this.listadoClasificacion = clasificaciones
        notifyDataSetChanged()
    }

    inner class ClasificacionHolder(val binding: ItemClasificacionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clasificacion: ClasificacionEntity){
            with(binding){
                TvAbreviacion.text = clasificacion.abreviacion
                TvIdClasificacion.text = clasificacion.idClasificacion.toString()
                TvNombreC.text = clasificacion.nombre

                ClFilaClas.setOnClickListener {
                    val action = ListaClasificacionFragmentDirections.listaUpdateClasificacion(clasificacion)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

}