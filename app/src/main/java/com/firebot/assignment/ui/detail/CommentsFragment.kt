package com.firebot.assignment.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.android.codelabs.paging.Injection
import com.example.android.codelabs.paging.ui.CommentAdapter
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Comment
import kotlinx.android.synthetic.main.comment_fragment.*
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.main_fragment.list
import kotlinx.android.synthetic.main.main_fragment.swiperefresh
import kotlinx.android.synthetic.main.shimmer_placeholder.*


class CommentsFragment : Fragment() {

    private lateinit var viewModel: CommentViewModel
    private val adapter = CommentAdapter()
    private val args: CommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.comment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
            context?.let { Injection.provideCommentViewModelFactory(it) })
            .get(CommentViewModel::class.java)

        initAdapter()
        parentShimmerLayout.startShimmerAnimation()
        viewModel.fetchComments(args.issueId)

        retry.setOnClickListener {
            errorView.visibility = View.GONE
            viewModel.fetchComments(args.issueId)
            showEmptyList(true)
        }
        swiperefresh.setOnRefreshListener {
            viewModel.fetchComments(args.issueId)
            showEmptyList(true)
        }
    }

    private fun initAdapter() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        (list.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations = false

        list.adapter = adapter
        viewModel.repos.observe(viewLifecycleOwner, Observer<List<Comment>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyView(it?.size == 0)
            adapter.submitList(it)

        })
        viewModel.networkErrors.observe(viewLifecycleOwner, Observer<String> {
            swiperefresh.isRefreshing = false
            errorView.visibility = View.VISIBLE
        })
    }

    private fun showEmptyView(b: Boolean) {
        if (b) {
            emptyText.visibility = View.VISIBLE
        }
        list.visibility = View.VISIBLE
        swiperefresh.isRefreshing = false
        parentShimmerLayout.stopShimmerAnimation()
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
