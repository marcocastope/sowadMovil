package com.marcocastope.sowadmovil.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.ui.incidents.IncidentsFragment
import com.marcocastope.sowadmovil.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private var incidentsFragment: IncidentsFragment = IncidentsFragment()
    private var profileFragment: ProfileFragment = ProfileFragment()

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        showFragment(incidentsFragment)
    }

    private fun initUi() {
        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener {
            showNextFragment(it.itemId)
            true
        }
    }

    private fun showNextFragment(itemId: Int) {
        when (itemId) {
            R.id.menuIncidents -> {
                showFragment(incidentsFragment)
            }
            R.id.menuProfile -> {
                showFragment(profileFragment)
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, fragment).commit()
    }
}