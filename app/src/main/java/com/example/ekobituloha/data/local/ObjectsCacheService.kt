package com.example.ekobituloha.data.local

interface ObjectsCacheService {
    fun addObject(title: String, description: String)
    fun removeObject(objectId: Int)
    fun getObject(objectId: Int): ObjectCacheModel?
    fun getAllObjects(): List<ObjectCacheModel>
}
