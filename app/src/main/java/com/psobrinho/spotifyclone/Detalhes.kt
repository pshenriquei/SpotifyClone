package com.psobrinho.spotifyclone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psobrinho.spotifyclone.databinding.ActivityDetalhesBinding
import com.squareup.picasso.Picasso

class Detalhes : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.extras?.let {

            var album = it.getString("album")
            var titulos = it.getString("titulos")

            val detalhe_album = binding.detalheAlbum
            val titulo_album = binding.tituloAlbum

            Picasso.get().load(album).into(detalhe_album)
            titulo_album.setText(titulos)
        }

        val toolbar = binding.toolbar

        window.statusBarColor = Color.LTGRAY
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back))
        toolbar.title = null
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}