
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as ProjectViewHolder).bind(repoItem)


            holder.itemView.setOnClickListener {
                repoItem.expanded = !repoItem.expanded
                notifyItemChanged(position)


                if(previousPosition!=-1 && previousPosition!=position) {
                    getItem(previousPosition).expanded = false
                    notifyItemChanged(previousPosition)
                }
                previousPosition = position



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
