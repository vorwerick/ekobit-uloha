package com.example.ekobituloha.domain

sealed class OperationResult<T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    class Error<T>() : OperationResult<T>()

}
