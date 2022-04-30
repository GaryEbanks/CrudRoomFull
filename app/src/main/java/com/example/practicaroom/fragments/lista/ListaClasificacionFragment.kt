package com.example.practicaroom.fragments.lista

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaroom.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.practicaroom.database.dao.ClasificacionDao
import com.example.practicaroom.database.viewmodels.ClasificacionViewModels
import com.example.practicaroom.databinding.FragmentListaClasificacionBinding

class ListaClasificacionFragment : Fragment() {
    lateinit var vBinding: FragmentListaClasificacionBinding

    private lateinit var viewModel: ClasificacionViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding = FragmentListaClasificacionBinding.inflate(inflater, container, false)

        val adapter = ClasificacionAdapter()
        val recycleView = vBinding.RcvListaClasificacion

        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(ClasificacionViewModels::class.java)
        viewModel.lista.observe(viewLifecycleOwner, Observer {clasif -> adapter.setData(clasif) })

        setHasOptionsMenu(true)

        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        }

    private fun setupViews(){
        with(vBinding){
            BtnAgregar.setOnClickListener {
                it.findNavController().navigate(R.id.add_Clasificacion)
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

