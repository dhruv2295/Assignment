package com.example.android.codelabs.paging.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Comment

/**
 * View Holder for a [Comment] RecyclerView list item.
 */
class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.comment_author)
    private val description: TextView = view.findViewById(R.id.comment_description)

    private var repo: Comment? = null

    fun bind(repo: Comment?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Comment) {
        this.repo = repo
        if (repo.body.isNullOrBlank())
            description.visibility = View.GONE

        name.text = repo.user?.login
        description.text = repo.body
    }

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.comment_view_item, parent, false)
            return CommentViewHolder(view)
        }
    }
}
