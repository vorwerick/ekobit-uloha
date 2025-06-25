package com.example.ekobituloha.data.repository

import com.example.ekobituloha.data.local.ObjectCacheModel
import com.example.ekobituloha.data.local.ObjectsCache
import com.example.ekobituloha.data.local.ObjectsCacheService
import org.koin.java.KoinJavaComponent.inject

class ObjectsRepository : ObjectsCacheService {

    val cache: ObjectsCache by inject(ObjectsCache::class.java)

    override fun addObject(title: String, description: String) {
        cache.add(ObjectCacheModel(cache.getAll().size, title, description, System.currentTimeMillis()))
    }

    override fun removeObject(objectId: Int) {
        cache.remove(objectId)
    }

    override fun getObject(objectId: Int): ObjectCacheModel? {
        return cache.get(objectId)
    }

    override fun getAllObjects(): List<ObjectCacheModel> {
        return cache.getAll()
    }
}
