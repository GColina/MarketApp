package com.gcolina.supermarkeapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.gcolina.supermarkeapp.R
import com.gcolina.supermarkeapp.databinding.ActivityHomeBinding
import com.gcolina.supermarkeapp.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    // private val homeViewModel :HomeViewModel by viewModels ()


    //Esta manera diferente de hacerlo
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Tambien como lo hice con el Gaby es llamar a una funcion getData dentro del View Model

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initUi()

    }

    private fun initUi() {
        initListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { state ->
                    renderLastProduct(state.lastProduct)
                    /*renderTopProducts(state.topProduct)
                    renderProducts(state.products)*/
                }
            }
        }
    }

    private fun initListeners() {
        binding.viewToolbar.tvAddAsset.setOnClickListener { }
    }

 /*   private fun renderProducts(products: List<Product>) {

    }

    private fun renderTopProducts(topProduct: List<Product>) {

    }
*/

    private fun renderLastProduct(lastProduct: Product?) {
        if (lastProduct == null) return

        binding.viewLastAsset.tvTitle.text = lastProduct.title
        binding.viewLastAsset.tvDescription.text = lastProduct.description
        Glide.with(this).load(lastProduct.imageUrl).placeholder(R.drawable.ic_placeholder)
            .into(binding.viewLastAsset.ivLastAsset)
    }
}