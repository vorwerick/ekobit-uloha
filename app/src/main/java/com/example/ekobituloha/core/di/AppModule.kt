package com.example.ekobituloha.core.di

import com.example.ekobituloha.data.local.ObjectsCache
import com.example.ekobituloha.data.repository.ObjectsRepository
import com.example.ekobituloha.domain.usecases.AddRandomizedObjectUseCase
import com.example.ekobituloha.domain.usecases.GetObjectsUseCase
import com.example.ekobituloha.presentation.viewmodel.ObjectDetailViewModel
import com.example.ekobituloha.presentation.viewmodel.ObjectListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { ObjectsCache() }

    factory { ObjectsRepository() }

    // Use Cases
    factory { GetObjectsUseCase() }
    factory { AddRandomizedObjectUseCase() }

    // ViewModels
    viewModel { ObjectListViewModel() }
    viewModel { ObjectDetailViewModel() }
}
