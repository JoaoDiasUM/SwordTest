package com.example.swordcattest.feature_cat_listing.data.room

import androidx.room.TypeConverter
import com.example.swordcattest.feature_cat_listing.domain.model.Breed
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem
import com.google.gson.Gson

object ListConverters {
    @TypeConverter
    fun listToJson(value: List<Breed>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String?): List<Breed> {
        return if (value != null && value != "null") {
            Gson().fromJson(value, Array<Breed>::class.java).toList()
        } else {
            emptyList()
        }
    }
}