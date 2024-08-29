package com.jazz.namesapp.di

import android.content.Context
import androidx.room.Room
import com.jazz.namesapp.data.AppDatabase
import com.jazz.namesapp.data.NameRepository
import com.jazz.namesapp.data.NameDao
import com.jazz.namesapp.data.NameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "name_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNameDao(database: AppDatabase): NameDao {
        return database.nameDao()
    }

    @Provides
    @Singleton
    fun provideNameRepository(nameDao: NameDao): NameRepository {
        return NameRepositoryImpl(nameDao)
    }
}