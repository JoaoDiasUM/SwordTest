package com.example.swordcattest.domain.usecases

import app.cash.turbine.test
import com.example.swordcattest.common.Resource
import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.feature_cat_listing.domain.repository.CatRepository
import com.example.swordcattest.feature_cat_listing.domain.usecase.GetCats
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCatsUseCaseTest {
    private lateinit var sut: GetCats
    private lateinit var catRepository: CatRepository

    @Before
    fun setup() {
        catRepository = mockk()
        sut = GetCats(
            catRepository = catRepository
        )
    }

    @Test
    fun `Get all cats from database`() = runTest {
        val result = sut.invoke()

        val cats =  listOf(FakeCat.cat, FakeCat.cat)

        coEvery { catRepository.getCats() } returns cats

        result.test {
            val expected = awaitItem()
            assert(expected is Resource.Success)
            assertEquals(cats, (expected as Resource.Success).data)
            awaitComplete()
        }
    }
}