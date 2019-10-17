
package com.example.android.codelabs.paging.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions
import com.firebot.assignment.R
import com.firebot.assignment.service.model.Project
import java.text.NumberFormat
import java.util.*

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
    private val detils: LinearLayout = view.findViewById(R.id.repo_details)

    private var repo: Project? = null

    private val context: Context = view.context;

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
        language.text = repo.language
        description.text = repo.description

        if(repo.language==null)
            language.visibility = View.GONE
        else
            language.visibility = View.VISIBLE

        stars.text = NumberFormat.getNumberInstance(Locale.US).format(repo.stars)
        forks.text = NumberFormat.getNumberInstance(Locale.US).format(repo.forks)

        Glide.with(context).load(repo.avatar).apply(RequestOptions.circleCropTransform()).into(avatar);

        if(repo.expanded)
            detils.visibility = View.VISIBLE
        else
            detils.visibility = View.GONE

    }

    companion object {
        fun create(parent: ViewGroup): ProjectViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.project_view_item, parent, false)
            return ProjectViewHolder(view)
        }
    }
}
