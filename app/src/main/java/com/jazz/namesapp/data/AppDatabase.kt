package com.jazz.namesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Name::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nameDao(): NameDao
}