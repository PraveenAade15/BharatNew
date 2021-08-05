package com.example.bharatnews.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.bharatnews.R
import com.example.bharatnews.ViewModel.NewsViewModel
import com.example.bharatnews.ui.NewsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleNewsFragment: Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    val args:ArticleNewsFragmentArgs by navArgs()

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel
         val article=args.article
          webView.apply {
              webViewClient= WebViewClient()
              loadUrl(article.url)
          }
          fab.setOnClickListener {
              viewModel.saveArticle(article)
              Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()
          }
    }

}