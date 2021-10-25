package com.eddiej.searchbeers.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.ActivityMainBinding
import com.eddiej.searchbeers.feature.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutResourceId(): Int = R.layout.activity_main
}