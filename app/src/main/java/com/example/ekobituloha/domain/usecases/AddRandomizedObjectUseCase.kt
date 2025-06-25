package com.example.ekobituloha.domain.usecases

import com.example.ekobituloha.data.repository.ObjectsRepository
import com.example.ekobituloha.domain.Content
import com.example.ekobituloha.domain.OperationResult
import org.koin.java.KoinJavaComponent.inject
import kotlin.getValue

class AddRandomizedObjectUseCase {

    val repository: ObjectsRepository by inject(ObjectsRepository::class.java)


    suspend operator fun invoke(): OperationResult<Unit> {
        try {
            repository.addObject(Content.titles.random(), Content.descriptions.random())
            return OperationResult.Success<Unit>(Unit)
        } catch (e: Exception) {
            return OperationResult.Error()
        }
    }

}
