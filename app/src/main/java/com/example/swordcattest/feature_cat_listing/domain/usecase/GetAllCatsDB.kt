package com.example.swordcattest.feature_cat_listing.domain.usecase

import com.example.swordcattest.feature_cat_listing.domain.repository.DatabaseRepository
import javax.inject.Inject

class GetAllCatsDB @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() = databaseRepository.getAllCats()
}