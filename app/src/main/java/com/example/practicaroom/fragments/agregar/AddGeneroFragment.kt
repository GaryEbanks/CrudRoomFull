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
import com.example.practicaroom.database.entities.GeneroEntity
import com.example.practicaroom.database.viewmodels.GeneroViewModels
import com.example.practicaroom.databinding.FragmentAddGeneroBinding

class AddGeneroFragment : Fragment() {
   private lateinit var aBinding: FragmentAddGeneroBinding
   private lateinit var viewModel: GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aBinding =
            FragmentAddGeneroBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(GeneroViewModels::class.java)
        aBinding.btnAgregarG.setOnClickListener {
            guardarRegistro()
        }
        return aBinding.root
    }

    private fun guardarRegistro() {
        val nombre = aBinding.TxtNombre.text.toString()

        val gen = GeneroEntity(0,nombre,true)

        viewModel.agregarGenero(gen)

        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.ir_ListaGenero)
    }
}
