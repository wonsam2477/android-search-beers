package com.eddiej.searchbeers.feature.main.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.eddiej.searchbeers.R
import com.eddiej.searchbeers.databinding.FragmentBeerDetailBinding
import com.eddiej.searchbeers.feature.BaseFragment
import com.eddiej.searchbeers.feature.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailFragment : BaseFragment<FragmentBeerDetailBinding>() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun setupViews() {

    }

    override fun bindViews() {
        viewModel.selectedItem.observe(this, { item ->
            binding?.item = item
        })
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_beer_detail
}