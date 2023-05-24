package com.example.hololiveapp.ui.theme.page.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hololiveapp.data.HoloRepo
import com.example.hololiveapp.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartVM(private val repo: HoloRepo) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAllAddedMerch() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repo.getAllAddedMerch()
                .collect { merch ->
                    val totalRequiredPoint =
                        merch.sumOf { it.member.price * it.count }
                    _uiState.value = UiState.Success(CartState(merch, totalRequiredPoint))
                }
        }
    }

    fun updateAddedMerch(id: Long, count: Int) {
        viewModelScope.launch {
            repo.updateMerch(id, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAllAddedMerch()
                    }
                }
        }
    }
}