package com.firebot.assignment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.android.codelabs.paging.Injection
import com.example.android.codelabs.paging.ui.IssueAdapter
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Issues
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.shimmer_placeholder.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val adapter = IssueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
            context?.let { Injection.provideViewModelFactory(it) })
            .get(MainViewModel::class.java)


        initAdapter()
        parentShimmerLayout.startShimmerAnimation()
        viewModel.fetchIssues()

        retry.setOnClickListener {
            errorView.visibility = View.GONE
            viewModel.fetchIssues()
            showEmptyList(true)
        }
        swiperefresh.setOnRefreshListener {
            viewModel.forceFetchIssues()
            showEmptyList(true)
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_name -> {
                    viewModel.fetchIssuesByDate().observe(this, Observer<List<Issues>> {
                        adapter.submitList(it)
                    })

                    true
                }

                R.id.action_stars -> {
                    viewModel.fetchLocalIssues().observe(this, Observer<List<Issues>> {
                        adapter.submitList(it)
                    })
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun initAdapter() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        (list.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations = false

        list.adapter = adapter
        viewModel.repos.observe(this, Observer<List<Issues>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            swiperefresh.isRefreshing = false

            viewModel.fetchLocalIssues().observe(this, Observer<List<Issues>>{
                showEmptyList(false)

                if(it.isEmpty()) {
                    errorView.visibility = View.VISIBLE
                }

            })

        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            parentShimmerLayout.startShimmerAnimation()
            list.visibility = View.GONE
        } else {
            parentShimmerLayout.stopShimmerAnimation()
            swiperefresh.isRefreshing = false
            list.visibility = View.VISIBLE
        }
    }
    
}
