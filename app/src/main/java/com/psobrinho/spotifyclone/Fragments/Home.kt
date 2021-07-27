package com.psobrinho.spotifyclone.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psobrinho.spotifyclone.Model.*
import com.psobrinho.spotifyclone.R
import com.psobrinho.spotifyclone.databinding.AlbumItemBinding
import com.psobrinho.spotifyclone.databinding.CategoriaItemBinding
import com.psobrinho.spotifyclone.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class Home : Fragment() {

    private lateinit var categoriaAdapter: CategoriaAdatper

    companion object {
        fun newInstace(): Home {
            val fragmentHome = Home()
            val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private var fragmentHome: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bindingHome = FragmentHomeBinding.bind(view)
        fragmentHome = bindingHome

        val recycler_view_categorias = bindingHome.recyclerViewCategorias

        val categorias = arrayListOf<Categoria>()


        categoriaAdapter = CategoriaAdatper(categorias)
        recycler_view_categorias.adapter = categoriaAdapter
        recycler_view_categorias.layoutManager = LinearLayoutManager(context)

        retroFit().create(SpotifyAPI::class.java)
            .ListCategorias()
            .enqueue(object : Callback<Categorias>{
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                    Toast.makeText(context, "Erro no servidor!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            categoriaAdapter.categorias.clear()
                            categoriaAdapter.categorias.addAll(it.categorias)
                            categoriaAdapter.notifyDataSetChanged()

                        }

                    }
                }

            })
    }

    private inner class CategoriaAdatper(internal val categorias: MutableList<Categoria>) :
        RecyclerView.Adapter<CategoriaHolder>() {

        private lateinit var binding: CategoriaItemBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {

            binding =
                CategoriaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoriaHolder(binding)

        }

        override fun getItemCount(): Int = categorias.size


        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)

        }
    }

    private inner class CategoriaHolder(binding: CategoriaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val text_titulo = binding.textTitulo
        private val recycler_albuns = binding.recyclerAlbuns

        fun bind(categoria: Categoria) {
            text_titulo.text = categoria.titulo
            recycler_albuns.adapter = AlbunsAdapter(categoria.albuns) { album ->
                //val intent = Intent(context, Detalhes::class.java)
                //intent.putExtra("album", album.album)
               // intent.putExtra("titulos", titulos[album.id])
               // startActivity(intent)
            }
            recycler_albuns.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

    }

    //____________________________________________________________________________________________

    private inner class AlbunsAdapter(private val albuns: List<Album>, private val listener: ((Album) -> Unit)?) : RecyclerView.Adapter<AlbunsHolder>() {

        private lateinit var binding: AlbumItemBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolder =
            AlbunsHolder(

                binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClick = listener
            )

        override fun getItemCount(): Int = albuns.size


        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }

    }

    private inner class AlbunsHolder(binding: AlbumItemBinding, val onClick: ((Album) -> Unit)?) :
        RecyclerView.ViewHolder(binding.root) {

        private val image_album = binding.imageAlbum

        fun bind(album: Album) {
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit()
                .into(image_album)
            image_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }

    }

}