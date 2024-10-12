package com.example.swordcattest.feature_cat_listing.data.remote

import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApiService {
    @Headers(
        "x-api-key: live_79PFE12RiNYRUS7DK2lRVGL0LwCY54QHyexY0fOz6y3JFPlQYNCkwurkvqJjw7xo"
    )
    @GET("/v1/images/search?")
    suspend fun getCats(
        @Query("size") size: String,
        @Query("mime_types") mimeTypes: String,
        @Query("format") format: String,
        @Query("has_breeds") hasBreeds: Boolean,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<CatItem>>
}