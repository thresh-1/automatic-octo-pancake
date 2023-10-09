package com.example.appy.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appy.main.model.Text
import com.example.appy.main.repository.TextRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TextViewModel(
    private val repository: TextRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow<Text?>(null)
    val state = _state.asStateFlow()

    fun onEvent(event: Event) {
        viewModelScope.launch(dispatcher) {
            when (event) {
                Event.OnClear -> repository.clear()
                is Event.OnInsert -> repository.insertText(text = event.text)
                is Event.OnLoad -> repository.getText(event.id).collect { text ->
                    _state.value = text
                }

            }
        }
    }

    sealed class Event {
        data class OnLoad(val id: Int) : Event()
        data class OnInsert(val text: Text) : Event()
        data object OnClear : Event()
    }
}