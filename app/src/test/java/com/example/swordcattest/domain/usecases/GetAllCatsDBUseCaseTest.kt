package com.example.swordcattest.domain.usecases

import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.fakes.FakeDatabaseRepository
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetAllCatsDB
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllCatsDBUseCaseTest {

    private lateinit var sut: GetAllCatsDB
    private var fakeDatabaseRepository = FakeDatabaseRepository()
    @Before
    fun setup() {
        sut = GetAllCatsDB(
            databaseRepository = fakeDatabaseRepository
        )
    }

    @Test
    fun `Get all cats from database`() = runTest {
        val result = sut.invoke()
        assert(result == listOf(FakeCat.cat))
    }
}