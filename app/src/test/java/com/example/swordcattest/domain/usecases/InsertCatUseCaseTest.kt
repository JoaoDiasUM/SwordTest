package com.example.swordcattest.domain.usecases

import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.fakes.FakeDatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.model.Breed
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.usecase.InsertCat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class InsertCatUseCaseTest {
    private lateinit var sut: InsertCat
    private var fakeDatabaseRepository = FakeDatabaseRepository()

    @Before
    fun setup() {
        sut = InsertCat(
            databaseRepository = fakeDatabaseRepository
        )
    }

    @Test
    fun `Get all cats from database`() = runTest {
        sut.invoke(FakeCat.cat)
        val expected = fakeDatabaseRepository.getAllCats()
        assert(expected == listOf(FakeCat.cat,FakeCat.cat))
    }
}