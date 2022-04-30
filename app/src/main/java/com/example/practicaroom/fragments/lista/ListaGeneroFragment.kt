package com.example.practicaroom.fragments.lista

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaroom.R
import com.example.practicaroom.database.viewmodels.GeneroViewModels
import com.example.practicaroom.databinding.FragmentListaGeneroBinding


class ListaGeneroFragment : Fragment() {
    lateinit var vBinding: FragmentListaGeneroBinding

    private lateinit var viewModel: GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding = FragmentListaGeneroBinding.inflate(inflater, container, false)

        val adapter = GeneroAdapter()
        val recycleView = vBinding.RcvListaGenero

        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(GeneroViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer { gen -> adapter.setData(gen) })

        setHasOptionsMenu(true)

        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(vBinding){
            BtnAgregar.setOnClickListener {
                it.findNavController().navigate(R.id.add_Genero)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuEliminar) {
            eliminarTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarTodo() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarTodo()
            Toast.makeText(
                requireContext(),
                "Registros eliminados satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando todos los registro")
        alerta.setMessage("¿Esta seguro de eliminar los registros?")
        alerta.create().show()
    }

}