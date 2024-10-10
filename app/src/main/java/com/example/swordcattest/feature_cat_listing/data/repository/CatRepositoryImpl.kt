package com.example.swordcattest.feature_cat_listing.data.repository

import com.example.swordcattest.feature_cat_listing.data.remote.CatApiService
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catApiServices: CatApiService
): CatRepository {
    override suspend fun getCats(): List<CatItem>? {
        val response = catApiServices.getCats()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}