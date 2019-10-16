
package com.example.android.codelabs.paging.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Project

/**
 * View Holder for a [Project] RecyclerView list item.
 */
class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val author: TextView = view.findViewById(R.id.repo_author)
    private val name: TextView = view.findViewById(R.id.repo_name)
    private val description: TextView = view.findViewById(R.id.repo_description)
    private val stars: TextView = view.findViewById(R.id.repo_stars)
    private val language: TextView = view.findViewById(R.id.repo_language)
    private val forks: TextView = view.findViewById(R.id.repo_forks)
    private val avatar: ImageView = view.findViewById(R.id.repo_avatar)

    private var repo: Project? = null

    private val context: Context = view.context;

    init {
        view.setOnClickListener {
           
        }
    }

    fun bind(repo: Project?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            author.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
            language.visibility = View.GONE
            stars.text = resources.getString(R.string.unknown)
            forks.text = resources.getString(R.string.unknown)
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Project) {
        this.repo = repo
        name.text = repo.name
        author.text = repo.author

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        description.text = repo.description
        descriptionVisibility = View.VISIBLE
        description.visibility = descriptionVisibility

        stars.text = repo.stars.toString()
        forks.text = repo.forks.toString()

        Glide.with(context).load(repo.avatar).apply(RequestOptions.circleCropTransform()).into(avatar);

        // if the language is missing, hide the label and the value
        var languageVisibility = View.GONE
        repo.language?.let {
            if(!repo.language!!.isEmpty()) {
                language.text = repo.language
                languageVisibility = View.VISIBLE
            }
        }

        language.visibility = languageVisibility
    }

    companion object {
        fun create(parent: ViewGroup): ProjectViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.project_view_item, parent, false)
            return ProjectViewHolder(view)
        }
    }
}
