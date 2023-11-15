package com.tonyydl.shoppingmallapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, UiEvent> : ViewModel() {

    abstract fun setInitialState(): UiState

    private val initialState: UiState by lazy { setInitialState() }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState

    protected var state: UiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    protected fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}