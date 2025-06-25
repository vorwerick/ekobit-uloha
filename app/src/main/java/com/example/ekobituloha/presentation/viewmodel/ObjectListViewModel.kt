package com.example.ekobituloha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekobituloha.data.local.ObjectCacheModel
import com.example.ekobituloha.domain.OperationResult
import com.example.ekobituloha.domain.usecases.AddRandomizedObjectUseCase
import com.example.ekobituloha.domain.usecases.GetObjectsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ObjectListViewModel : ViewModel() {

    private val getObjectsUseCase: GetObjectsUseCase by inject(GetObjectsUseCase::class.java)
    private val addRandomizedObjectUseCase: AddRandomizedObjectUseCase by inject(AddRandomizedObjectUseCase::class.java)

    private val _objects = MutableStateFlow<List<ObjectCacheModel>>(emptyList())
    val objects: StateFlow<List<ObjectCacheModel>> = _objects.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadObjects()
    }

    fun loadObjects() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = getObjectsUseCase()) {
                is OperationResult.Success -> {
                    // Sort objects by created timestamp in descending order (newest first)
                    _objects.value = result.data.sortedByDescending { it.created }
                }
                is OperationResult.Error -> {
                    _error.value = "Failed to load objects"
                }
            }

            _isLoading.value = false
        }
    }

    fun addRandomObject() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = addRandomizedObjectUseCase()) {
                is OperationResult.Success -> {
                    // Reload objects after adding a new one
                    loadObjects()
                }
                is OperationResult.Error -> {
                    _error.value = "Failed to add object"
                    _isLoading.value = false
                }
            }
        }
    }
}
