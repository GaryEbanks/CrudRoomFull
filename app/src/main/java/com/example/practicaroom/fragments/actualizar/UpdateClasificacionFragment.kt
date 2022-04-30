package com.example.practicaroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practicaroom.R
import com.example.practicaroom.database.entities.ClasificacionEntity
import com.example.practicaroom.database.viewmodels.ClasificacionViewModels
import com.example.practicaroom.databinding.FragmentUpdateClasificacionBinding


class UpdateClasificacionFragment : Fragment() {
    lateinit var uBinding: FragmentUpdateClasificacionBinding
    private val args by navArgs<UpdateClasificacionFragmentArgs>()
    private lateinit var viewModel: ClasificacionViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        uBinding =
            FragmentUpdateClasificacionBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(ClasificacionViewModels::class.java)

        with(uBinding){
            TxtAbreviacion.setText(args.currentClasificacion.abreviacion)
            TxtNombre.setText(args.currentClasificacion.nombre)

            BtnEditarC.setOnClickListener {
                GuardarCambios()
            }
        }
        setHasOptionsMenu(true)
        return uBinding.root
    }

    private fun GuardarCambios() {
        val abrev = uBinding.TxtAbreviacion.text.toString()
        val nombre = uBinding.TxtNombre.text.toString()

        val clasif =
            ClasificacionEntity(args.currentClasificacion.idClasificacion, abrev, nombre, args.currentClasificacion.activo)

        viewModel.actualizarClasificacion(clasif)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.update_ListaClasificacion)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.menuEliminar) {
            eliminarClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarClasificacion() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarClasificacion(args.currentClasificacion)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.update_ListaClasificacion)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentClasificacion.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentClasificacion.nombre}?")
        alerta.create().show()
    }
}