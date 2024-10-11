package com.example.swordcattest.domain.usecases

import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.fakes.FakeDatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.model.Breed
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetAllCatsDB
import com.example.swordcattest.feature_cat_listing.domain.usecase.InsertAllCats
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class InsertAllCatsUseCaseTest {
    private lateinit var sut: InsertAllCats
    private var fakeDatabaseRepository = FakeDatabaseRepository()

    @Before
    fun setup() {
        sut = InsertAllCats(
            databaseRepository = fakeDatabaseRepository
        )
    }

    @Test
    fun `Get all cats from database`() = runTest {
        sut.invoke(listOf(FakeCat.cat,FakeCat.cat))
        val expected = fakeDatabaseRepository.getAllCats()
        assert(expected == listOf(FakeCat.cat,FakeCat.cat,FakeCat.cat))
    }

}