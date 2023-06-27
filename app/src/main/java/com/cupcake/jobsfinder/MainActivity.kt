package com.cupcake.jobsfinder

import android.os.Bundle
import android.view.View
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cupcake.ui.R
import com.cupcake.ui.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        setUpButtonNavigationBar()
    }

    private fun setUpButtonNavigationBar() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationBar.setupWithNavController(navController)
        onChangeItemButtonNavigationBarSelected(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment, R.id.notificationFragment -> {
                    binding.bottomNavigationBar.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigationBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun onChangeItemButtonNavigationBarSelected(navController: NavController) {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            navController.popBackStack(item.itemId, inclusive = false)
            true
        }
    }
}