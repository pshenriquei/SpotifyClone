package com.psobrinho.spotifyclone

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.psobrinho.spotifyclone.Fragments.Biblioteca
import com.psobrinho.spotifyclone.Fragments.Buscar
import com.psobrinho.spotifyclone.Fragments.Home
import com.psobrinho.spotifyclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var Content: FrameLayout? = null
    private var mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_inicio -> {
                    val fragment = Home.newInstace()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_buscar -> {
                    val fragment = Buscar.newInstace()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_biblioteca -> {
                    val fragment = Biblioteca.newInstace()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Content = binding.content
        binding.bottomMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = Home.newInstace()
        addFragment(fragment)

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

}