package com.example.swordcattest.feature_cat_listing.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatItem(
    @PrimaryKey val id: String,
    val breeds: List<Breed>,
    val height: Int,
    val url: String,
    val width: Int,
    var favourite: Boolean,
) {
    fun doesMatchSearchBreed(query: String): Boolean {
        val matchingCombinations = listOf(
            "$breeds$id",
            "${breeds.joinToString()} $id",
            "${breeds.joinToString()} $url",
            "${breeds.joinToString()} $width",
            "$id $url",
            "$id $width",
            "$url $width"
        )

        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }
}