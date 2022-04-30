package com.example.practicaroom.fragments.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.database.entities.GeneroEntity
import com.example.practicaroom.databinding.ItemGeneroBinding

class GeneroAdapter:
RecyclerView.Adapter<GeneroAdapter.GeneroHolder>(){
    private var listadoGenero = emptyList<GeneroEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneroHolder {
        val binding = ItemGeneroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneroHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneroHolder, position: Int) {
        holder.bind(
            listadoGenero[position]
        )
    }

    override fun getItemCount(): Int = listadoGenero.size

    fun setData(generos: List<GeneroEntity>){
        this.listadoGenero = generos
        notifyDataSetChanged()
    }

    inner class GeneroHolder(val binding: ItemGeneroBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genero: GeneroEntity){
            with(binding){
                TvIdGenero.text = genero.idGenero.toString()
                TvNombreGen.text = genero.nombre

                ClFilaGen.setOnClickListener {
                    val action = ListaGeneroFragmentDirections.listaUpdateGenero(genero)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}