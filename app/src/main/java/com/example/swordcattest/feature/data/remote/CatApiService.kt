package com.example.swordcattest.feature.data.remote

import com.example.swordcattest.feature.domain.model.CatItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApiService {
    @Headers("x-api-key: live_79PFE12RiNYRUS7DK2lRVGL0LwCY54QHyexY0fOz6y3JFPlQYNCkwurkvqJjw7xo")
    @GET("/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=20")
    suspend fun getCats(): Response<List<CatItem>>
}