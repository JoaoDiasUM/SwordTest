package com.example.swordcattest.di

import android.content.Context
import androidx.room.Room
import com.example.swordcattest.feature_cat_listing.data.repository.DatabaseRepositoryImpl
import com.example.swordcattest.feature_cat_listing.data.room.dao.CatDao
import com.example.swordcattest.feature_cat_listing.data.room.database.CatsDatabase
import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.usecase.CatDBUseCases
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetAllCats
import com.example.swordcattest.feature_cat_listing.domain.usecase.InsertAllCats
import com.example.swordcattest.feature_cat_listing.domain.usecase.InsertCat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCatsDatabase(@ApplicationContext appContext: Context): CatsDatabase {
        return Room.databaseBuilder(
            appContext,
            CatsDatabase::class.java,
            "cats_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCatsDao(catsDatabase: CatsDatabase): CatDao {
        return catsDatabase.catsDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(catsDatabase: CatsDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(catsDatabase.catsDao())
    }

    @Provides
    @Singleton
    fun provideChatUseCases(databaseRepository: DatabaseRepository): CatDBUseCases {
        return CatDBUseCases(
            getAllCats = GetAllCats(databaseRepository),
            insertCat = InsertCat(databaseRepository),
            insertAllCats = InsertAllCats(databaseRepository),
        )
    }
}