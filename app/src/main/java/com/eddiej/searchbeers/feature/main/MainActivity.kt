package com.eddiej.searchbeers.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.ActivityMainBinding
import com.eddiej.searchbeers.feature.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frag = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        setupActionBarWithNavController(frag.navController)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_main

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}