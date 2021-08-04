  package com.example.bharatnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bharatnews.R
import com.example.bharatnews.ViewModel.NewsViewModel
import com.example.bharatnews.ViewModel.NewsViewModelProviderFactory
import com.example.bharatnews.db.ArticleDatabase
import com.example.bharatnews.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

  class NewsActivity : AppCompatActivity() {
      lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val newsRepository=NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory=NewsViewModelProviderFactory(newsRepository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}