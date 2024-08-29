package com.jazz.namesapp.data

import kotlinx.coroutines.flow.Flow

interface NameRepository {
    val names: Flow<List<Name>>
    suspend fun addName(name: String)
    suspend fun deleteName(name: Name)
}

class NameRepositoryImpl(private val nameDao: NameDao): NameRepository {
    override val names: Flow<List<Name>> = nameDao.getNames()

    override suspend fun addName(name: String) {
        val newName = Name(name = name)
        nameDao.insertName(newName)
    }

    override suspend fun deleteName(name: Name) {
        nameDao.deleteName(name)
    }
}