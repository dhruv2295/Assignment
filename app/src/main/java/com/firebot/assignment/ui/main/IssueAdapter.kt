package com.example.android.codelabs.paging.ui

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firebot.assignment.service.model.Issues
import com.firebot.assignment.ui.main.IssuesFragmentDirections

/**
 * Adapter for the list of repositories.
 */
class IssueAdapter : ListAdapter<Issues, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IssueViewHolder.create(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as IssueViewHolder).bind(repoItem)


            holder.itemView.setOnClickListener {

                val direction =
                    IssuesFragmentDirections.actionMainFragmentToCommentsFragment2(
                        repoItem.number.toString()
                    )
                it.findNavController().navigate(direction)
                notifyItemChanged(position)

            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Issues>() {
            override fun areItemsTheSame(oldItem: Issues, newItem: Issues): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Issues, newItem: Issues): Boolean =
                oldItem == newItem
        }
    }
}
