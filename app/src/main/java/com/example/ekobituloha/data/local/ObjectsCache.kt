package com.example.ekobituloha.data.local

import com.example.ekobituloha.data.local.ObjectCacheModel
import java.util.concurrent.ConcurrentHashMap


class ObjectsCache {
    private val cache = ConcurrentHashMap<Int, ObjectCacheModel>()

    fun add(model: ObjectCacheModel): ObjectCacheModel {
        cache[model.id] = model
        return model
    }

    fun remove(id: Int): ObjectCacheModel? {
        return cache.remove(id)
    }

    fun get(id: Int): ObjectCacheModel? {
        return cache[id]
    }

    fun getAll(): List<ObjectCacheModel> {
        return cache.values.toList()
    }
}
