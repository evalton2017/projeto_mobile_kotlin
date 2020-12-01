package br.com.clonespotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.clonespotify.R


class Biblioteca : Fragment() {

    companion object{
        fun newInstance(): Biblioteca{
            val framentBiblioteca = Biblioteca()
            val argumentos = Bundle()
            framentBiblioteca.arguments = argumentos
            return framentBiblioteca
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biblioteca, container, false)
    }


}