package com.example.bharatnews.models


import com.example.bharatnews.models.Article
import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @SerializedName("articles")
    val articles: List<Article>,
        @SerializedName("status")
    val status: String,
        @SerializedName("totalResults")
    val totalResults: Int
)