package com.example.swordcattest.feature_cat_listing.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breed")
data class Breed(
    @PrimaryKey val id: String,
    val description: String,
    @SerializedName("life_span")
    val lifespan: String,
    val name: String,
    val origin: String,
    val temperament: String,
)