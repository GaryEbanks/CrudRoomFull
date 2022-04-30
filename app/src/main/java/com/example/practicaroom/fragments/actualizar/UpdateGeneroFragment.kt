package com.example.practicaroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practicaroom.R
import com.example.practicaroom.database.entities.GeneroEntity
import com.example.practicaroom.database.viewmodels.GeneroViewModels
import com.example.practicaroom.databinding.FragmentUpdateGeneroBinding

class UpdateGeneroFragment : Fragment() {
    lateinit var uBinding: FragmentUpdateGeneroBinding
    private val args by navArgs<UpdateGeneroFragmentArgs>()
    private lateinit var viewModel: GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        uBinding =
            FragmentUpdateGeneroBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(GeneroViewModels::class.java)

        with(uBinding){
            TxtNombre.setText(args.currentGenero.nombre)

            BtnEditarG.setOnClickListener {
                GuardarCambios()
            }
        }
        setHasOptionsMenu(true)
        return uBinding.root
    }

    private fun GuardarCambios() {
        val nombre = uBinding.TxtNombre.text.toString()

        val gen =
            GeneroEntity(args.currentGenero.idGenero, nombre, args.currentGenero.activo)

        viewModel.actualizarGenero(gen)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.update_ListaGenero)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.menuEliminar) {
            eliminarGenero()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarGenero() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarGenero(args.currentGenero)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.update_ListaGenero)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentGenero.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentGenero.nombre}?")
        alerta.create().show()
    }
}