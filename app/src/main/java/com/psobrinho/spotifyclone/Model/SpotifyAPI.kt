package com.psobrinho.spotifyclone.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpotifyAPI {

    @GET("Imagens%20Spotify.json?alt=media&token=df6e0a3b-9a5c-4bea-971a-87b7f9a0c9ca")
    fun ListCategorias(): Call<Categorias>

}

fun retroFit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://firebasestorage.googleapis.com/v0/b/spotify-45398.appspot.com/o/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()