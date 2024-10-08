package com.example.swordcattest.feature.data.repository

import com.example.swordcattest.feature.data.remote.CatApiService
import com.example.swordcattest.feature.domain.model.CatItem
import com.example.swordcattest.feature.domain.repository.CatRepository
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