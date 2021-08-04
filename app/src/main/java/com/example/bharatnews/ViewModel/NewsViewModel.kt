package com.example.bharatnews.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bharatnews.models.NewsResponse
import com.example.bharatnews.repository.NewsRepository
import com.example.bharatnews.ui.util.Resource

class NewsViewModel(val newsRepository: NewsRepository):ViewModel() {
    val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage=1


}