package br.com.clonespotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.clonespotify.R


class Buscar : Fragment() {

    companion object{
        fun newInstance(): Buscar{
            val framentBuscar = Buscar()
            val argumentos = Bundle()
            framentBuscar.arguments = argumentos
            return framentBuscar
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

}