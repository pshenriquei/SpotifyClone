package com.psobrinho.spotifyclone.Model

import com.google.gson.annotations.SerializedName
import com.psobrinho.spotifyclone.R

data class Categoria(

    @SerializedName("titulo") var titulo: String = "",
    @SerializedName("albuns") var albuns: List<Album> = arrayListOf()
)

data class Album(
    @SerializedName("url_imagem") var album: String = "",
    @SerializedName("id") var id: Int = 0
)

data class Categorias(@SerializedName("categoria")

    val categorias: List<Categoria>

)

//____________________________________________________________________

data class Secao(

    var secao: Int,
    var nome_secao: String
)

class SecaoBuilder{
    var secao: Int = 0
    var nome_secao: String = ""

    fun build(): Secao = Secao(secao,nome_secao)
}

fun secao(block: SecaoBuilder.() -> Unit): Secao = SecaoBuilder().apply(block).build()

fun dados(): MutableList<Secao> = mutableListOf(

    secao {

        secao = R.drawable.secao1
        nome_secao = "Podcasts"
    },
    secao {

        secao = R.drawable.secao2
        nome_secao = "Lançamentos"
    },
    secao {

        secao = R.drawable.secao1
        nome_secao = "Em casa"
    },
    secao {

        secao = R.drawable.secao3
        nome_secao = "Paradas"
    },
    secao {

        secao = R.drawable.secao4
        nome_secao = "Shows"
    },
    secao {

        secao = R.drawable.secao2
        nome_secao = "Made For You"
    },
    secao {

        secao = R.drawable.secao1
        nome_secao = "Brasil"
    },
    secao {

        secao = R.drawable.secao2
        nome_secao = "Funk"
    }
)