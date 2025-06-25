package com.example.ekobituloha.domain.usecases

import com.example.ekobituloha.data.local.ObjectCacheModel
import com.example.ekobituloha.data.repository.ObjectsRepository
import com.example.ekobituloha.domain.OperationResult
import org.koin.java.KoinJavaComponent.inject
import kotlin.getValue

class GetObjectsUseCase {

    val repository: ObjectsRepository by inject(ObjectsRepository::class.java)


    suspend operator fun invoke(): OperationResult<List<ObjectCacheModel>> {
        try {
            val objects = repository.getAllObjects()
            return OperationResult.Success<List<ObjectCacheModel>>(objects)
        } catch (e: Exception) {
            return OperationResult.Error()
        }
    }

}
