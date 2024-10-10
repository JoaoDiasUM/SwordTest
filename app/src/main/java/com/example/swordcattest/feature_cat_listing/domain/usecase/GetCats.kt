package com.example.swordcattest.feature_cat_listing.domain.usecase

import com.example.swordcattest.common.Resource
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.example.swordcattest.feature_cat_listing.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCats @Inject constructor(
    private val catRepository: CatRepository,
) {

    operator fun invoke(): Flow<Resource<List<CatItem>>> = flow {
        try {
            val cats = catRepository.getCats()
            if (cats != null) {
                emit(Resource.Success(cats))
            } else {
                emit(Resource.Error("No messages found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An server error error happened"))
        }
    }
}