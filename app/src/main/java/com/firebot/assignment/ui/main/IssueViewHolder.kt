package com.example.android.codelabs.paging.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Issues

/**
 * View Holder for a [Issues] RecyclerView list item.
 */
class IssueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_name)
    private val description: TextView = view.findViewById(R.id.repo_description)

    private var repo: Issues? = null

    private val context: Context = view.context

    fun bind(repo: Issues?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Issues) {
        this.repo = repo
        name.text = repo.title
        if(repo.body.isNullOrBlank())
            description.visibility = View.GONE

        description.text = repo.body
    }

    companion object {
        fun create(parent: ViewGroup): IssueViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.issue_view_item, parent, false)
            return IssueViewHolder(view)
        }
    }
}
