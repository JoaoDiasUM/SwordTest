package com.example.swordcattest.data.repository

import com.example.swordcattest.fakes.ERROR_RESPONSE
import com.example.swordcattest.fakes.FakeCat
import com.example.swordcattest.feature_cat_listing.data.remote.CatApiService
import com.example.swordcattest.feature_cat_listing.data.repository.CatRepositoryImpl
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CatRepositoryImplTest {

    private lateinit var sut: CatRepositoryImpl
    private val catApiService = mockk<CatApiService>()
    private val cat = FakeCat.cat

    private val errorResponseBody =
        ERROR_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())

    @Before
    fun setup() {
        sut = CatRepositoryImpl(catApiService)
    }

    @Test
    fun `When user retrieves cats, returns cats list`() = runTest {

        val response = Response.success(listOf(cat))

        coEvery {
            catApiService.getCats(any(),any(),any(),any(),any(),any(),any())
        }.returns(response)

        val result = sut.getCats()
        assert(result?.isNotEmpty() == true && result.contains(cat))
    }

    @Test
    fun `When user retrieves cats, returns null if not cats are found`() = runTest {
        val mockResponse = Response.error<List<CatItem>>(400, errorResponseBody)

        coEvery {
            catApiService.getCats(any(),any(),any(),any(),any(),any(),any())
        }.returns(mockResponse)

        val result = sut.getCats()
        assert(result == null)
    }
}