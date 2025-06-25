package com.example.ekobituloha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekobituloha.data.local.ObjectCacheModel
import com.example.ekobituloha.data.repository.ObjectsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ObjectDetailViewModel : ViewModel() {

    private val repository: ObjectsRepository by inject(ObjectsRepository::class.java)

    private val _object = MutableStateFlow<ObjectCacheModel?>(null)
    val objectDetails: StateFlow<ObjectCacheModel?> = _object.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadObject(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getObject(id)
                if (result != null) {
                    _object.value = result
                } else {
                    _error.value = "Object not found"
                }
            } catch (e: Exception) {
                _error.value = "Failed to load object: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
