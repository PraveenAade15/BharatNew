package com.example.bharatnews.repository

import com.example.bharatnews.db.ArticleDatabase
import com.example.bharatnews.models.Article
import com.example.bharatnews.ui.api.RetrofitInstance

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBeakingNews(countryCode:String,pageNumber:Int)=
            RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
suspend fun searchNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)



    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)
fun getSvedNews()=db.getArticleDao().getAllArticles()
suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)


}