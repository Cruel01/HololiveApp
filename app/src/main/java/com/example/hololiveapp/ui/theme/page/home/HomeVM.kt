package com.example.hololiveapp.ui.theme.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hololiveapp.data.HoloRepo
import com.example.hololiveapp.model.Merch
import com.example.hololiveapp.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeVM(private val repo: HoloRepo): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Merch>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Merch>>>
        get() = _uiState

    fun getAllMerch() {
        viewModelScope.launch {
            repo.getAllMembers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderTickets ->
                    _uiState.value = UiState.Success(orderTickets)
                }
        }
    }

}