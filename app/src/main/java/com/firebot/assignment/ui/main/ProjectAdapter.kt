
package com.example.android.codelabs.paging.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firebot.assignment.service.model.Project

/**
 * Adapter for the list of repositories.
 */
class ProjectAdapter : ListAdapter<Project, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProjectViewHolder.create(parent)
    }

    private var previousPosition = -1
    private var mExpandedPosition = -1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as ProjectViewHolder).bind(repoItem)

            val isExpanded = position == mExpandedPosition
            if (isExpanded) {
                previousPosition = position
                getItem(previousPosition).expanded = !isExpanded
            }

            holder.itemView.setOnClickListener {
                repoItem.expanded = !isExpanded
                mExpandedPosition = if (isExpanded) -1 else position
                if(position!=previousPosition) notifyItemChanged(previousPosition)
                notifyItemChanged(position)
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean =
                    oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean =
                    oldItem == newItem
        }
    }
}
