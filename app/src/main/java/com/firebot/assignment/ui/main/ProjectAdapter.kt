
package com.example.android.codelabs.paging.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.firebot.assignment.service.model.Project

/**
 * Adapter for the list of repositories.
 */
class ProjectAdapter : ListAdapter<Project, androidx.recyclerview.widget.RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return ProjectViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as ProjectViewHolder).bind(repoItem)
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
