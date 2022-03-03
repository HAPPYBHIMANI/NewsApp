package com.app.newsapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navController = findNavController(R.id.nav_host_fragment)

        bottomNavigationView.setupWithNavController(navController!!)

        navController!!.addOnDestinationChangedListener { _controller: NavController, destination: NavDestination?, arguments: Bundle? ->
            when (_controller.currentDestination!!.id) {
            }
        }

    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onClick(p0: View?) {

    }
}