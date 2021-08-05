package com.example.bharatnews.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bharatnews.R
import com.example.bharatnews.ViewModel.NewsViewModel
import com.example.bharatnews.adapters.NewsAdapter
import com.example.bharatnews.ui.NewsActivity
import com.example.bharatnews.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel
   lateinit var newsAdapter:NewsAdapter
    val TAG="BreakingNews Fragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                    R.id.action_breakingNewsFragment_to_articleNewsFragment,bundle
            )
        }



        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")

                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }


            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility=View.INVISIBLE

    }
    private fun showProgressBar() {
        paginationProgressBar.visibility=View.VISIBLE

    }

    private fun setupRecyclerView(){
        newsAdapter= NewsAdapter()
        rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}