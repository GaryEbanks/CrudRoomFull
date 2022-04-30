package com.example.practicaroom.fragments.agregar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practicaroom.R
import com.example.practicaroom.databinding.FragmentAddClasificacionBinding
import com.example.practicaroom.database.entities.ClasificacionEntity
import com.example.practicaroom.database.viewmodels.ClasificacionViewModels

class AddClasificacionFragment : Fragment() {
    private lateinit var aBinding: FragmentAddClasificacionBinding
    private lateinit var viewModel: ClasificacionViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aBinding =
            FragmentAddClasificacionBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(ClasificacionViewModels::class.java)
        aBinding.btnAgregarC.setOnClickListener {
            guardarRegistro()
        }
        return aBinding.root
    }

    private fun guardarRegistro() {
        val abrev = aBinding.TxtAbreviacion.text.toString()
        val nombre = aBinding.TxtNombre.text.toString()

        val clasificacion = ClasificacionEntity(0,abrev,nombre,true)

        viewModel.agregarClasificacion(clasificacion)

        Toast.makeText(requireContext(), "Registro guardado",
        Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.ir_ListaClasificacion)
    }
}