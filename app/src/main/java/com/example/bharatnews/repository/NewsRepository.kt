package com.example.bharatnews.repository

import com.example.bharatnews.db.ArticleDatabase
import com.example.bharatnews.ui.api.RetrofitInstance

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBeakingNews(countryCode:String,pageNumber:Int)=
            RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

}