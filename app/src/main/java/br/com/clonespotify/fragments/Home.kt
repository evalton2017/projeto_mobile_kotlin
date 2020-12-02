package br.com.clonespotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.clonespotify.R
import br.com.clonespotify.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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

        val categorias = arrayListOf<Categoria>()
        categoriaAdapater = CategoriaAdapter(categorias)
        recycler_categorias.adapter = categoriaAdapater;
        recycler_categorias.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotfyAPI::class.java)
                .ListCategorias()
                .enqueue(object : Callback<Categorias>{
                    override fun onFailure(call: Call<Categorias>, t: Throwable) {
                        Toast.makeText(context, "Erro no servidor!", Toast.LENGTH_SHORT)
                    }

                    override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                        if(response.isSuccessful){
                            response.body()?.let{

                                categoriaAdapater.categorias.clear()
                                categoriaAdapater.categorias.addAll(it.categorias)
                                categoriaAdapater.notifyDataSetChanged()
                            }
                        }
                    }
                })

    }

    private inner class CategoriaAdapter(internal val categorias: MutableList<Categoria>): RecyclerView.Adapter<CategoriaHolder>() {

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
            itemView.recycler_albuns.adapter = AlbunsAdapter(categoria.albuns)
            itemView.recycler_albuns.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    /*----------------------------------------ALBUNS-------------------------------*/
    private inner class AlbunsAdapter(internal val albuns: List<Album>): RecyclerView.Adapter<AlbunsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolder {
            return AlbunsHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun getItemCount(): Int = albuns.size

        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }
    }


    private inner class AlbunsHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        fun bind(album: Album){
          //  itemView.image_album.setImageResource(album.album)
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
        }
    }

  
}