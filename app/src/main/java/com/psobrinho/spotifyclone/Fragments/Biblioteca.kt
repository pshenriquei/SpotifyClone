package com.psobrinho.spotifyclone.Fragments

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.psobrinho.spotifyclone.FragmentsTab.Albuns
import com.psobrinho.spotifyclone.FragmentsTab.Artistas
import com.psobrinho.spotifyclone.FragmentsTab.Playlists
import com.psobrinho.spotifyclone.R
import com.psobrinho.spotifyclone.databinding.FragmentBibliotecaBinding

class Biblioteca : Fragment() {

    companion object {
        fun newInstace(): Biblioteca {
            val fragmentBiblioteca = Biblioteca()
            val argumentos = Bundle()
            fragmentBiblioteca.arguments = argumentos
            return fragmentBiblioteca

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biblioteca, container, false)
    }

    private lateinit var fragmentBiblioteca: FragmentBibliotecaBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bindingBiblioteca = FragmentBibliotecaBinding.bind(view)
        fragmentBiblioteca = bindingBiblioteca

        val viewPager = bindingBiblioteca.viewpager
        val viewPagerTab = bindingBiblioteca.viewpagertab

        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)

                .add("Playlists", Playlists::class.java)
                .add("Artistas", Artistas::class.java)
                .add("Albuns", Albuns::class.java)
                .create())

        viewPager.adapter = adapter
        viewPagerTab.setViewPager(viewPager)
    }

}