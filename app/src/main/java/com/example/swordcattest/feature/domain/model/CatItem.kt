package com.example.swordcattest.feature.domain.model

data class CatItem(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
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