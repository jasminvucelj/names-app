package com.jazz.namesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NameDao {
    @Query("SELECT * FROM names")
    fun getNames(): Flow<List<Name>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertName(name: Name)

    @Delete
    suspend fun deleteName(name: Name)
}