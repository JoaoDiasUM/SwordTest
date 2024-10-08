package com.example.swordcattest.feature.domain.repository

import com.example.swordcattest.feature.domain.model.CatItem

interface CatRepository {
    suspend fun getCats(): List<CatItem>?
}