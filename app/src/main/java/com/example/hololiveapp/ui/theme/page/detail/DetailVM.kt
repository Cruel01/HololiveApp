package com.example.hololiveapp.ui.theme.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hololiveapp.data.HoloRepo
import com.example.hololiveapp.model.Member
import com.example.hololiveapp.model.Merch
import com.example.hololiveapp.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailVM(private val repo: HoloRepo) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Merch>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Merch>>
        get() = _uiState

    fun getById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repo.getById(id))
        }
    }

    fun addToCart(member: Member, count: Int) {
        viewModelScope.launch {
            repo.updateMerch(member.id, count)
        }
    }
}