package com.example.swordcattest.di

import com.example.swordcattest.feature_cat_listing.data.repository.CatRepositoryImpl
import com.example.swordcattest.feature_cat_listing.domain.repository.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    abstract fun provideMessagingRepository(
        catRepositoryImpl: CatRepositoryImpl,
    ): CatRepository
}