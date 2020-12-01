package br.com.clonespotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.clonespotify.R
import br.com.clonespotify.model.Categoria
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    private lateinit var categoriaAdapater: CategoriaAdapter

    companion object{
        fun newInstance(): Home{
            val framentHome = Home()
            val argumentos = Bundle()
            framentHome.arguments = argumentos
            return framentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categorias: MutableList<Categoria> = ArrayList()
        for(c in 0..4){
            val categoria = Categoria()
            categoria.titulo = "Categoria$c"

            categorias.add(categoria)
        }

        categoriaAdapater = CategoriaAdapter(categorias)
        recycler_categorias.adapter = categoriaAdapater;
        recycler_categorias.layoutManager = LinearLayoutManager(context)
    }

    private inner class CategoriaAdapter(private val categorias: MutableList<Categoria>): RecyclerView.Adapter<CategoriaHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
            return CategoriaHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))
        }

        override fun getItemCount(): Int = categorias.size

        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)
        }
    }


    private inner class CategoriaHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(categoria: Categoria){
            itemView.text_titulo.text = categoria.titulo
        }
    }

  
}