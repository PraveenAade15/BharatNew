package com.example.bharatnews.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bharatnews.models.Article
import com.example.bharatnews.models.NewsResponse
import com.example.bharatnews.repository.NewsRepository
import com.example.bharatnews.ui.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository):ViewModel() {
    val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage=1
    val searchgNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage=1
    init {
        getBreakingNews("us")
    }
    //only call suspend or coroutine

    fun getBreakingNews(countryCode:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response=newsRepository.getBeakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    fun searchNews(searchQuery:String)=viewModelScope.launch {
        searchgNews.postValue((Resource.Loading()))
        val response=newsRepository.searchNews(searchQuery,searchNewsPage)
        searchgNews.postValue(handleSearchNewsResponse(response))
    }
    
    private fun handleBreakingNewsResponse(response:Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }
    private fun handleSearchNewsResponse(response:Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews()=newsRepository.getSvedNews()
    fun deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }



}