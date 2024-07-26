package com.gcolina.supermarkeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcolina.supermarkeapp.data.network.FirebaseDataBaseService
import com.gcolina.supermarkeapp.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository:FirebaseDataBaseService) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUIState>(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        getData()
    }

    private fun getData() {
        getLastProduct()
    }

    private fun getLastProduct() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getLastProduct()
            }
 // Esta tambien es una forma valida           _uiState.value = _uiState.value.copy(lastProduct = response)
            _uiState.update { it.copy(lastProduct = response) }
        }
    }

}

data class HomeUIState(
    val lastProduct: Product? = null,
    val products: List<Product> = emptyList(),
    val topProduct: List<Product> = emptyList()
)