package com.firebot.assignment.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.firebot.assignment.db.DataConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issues")
@TypeConverters(DataConverter::class)

data class Issues(
    @PrimaryKey @field:SerializedName("id") var id: Long?,

    @field:SerializedName("assignee") var assignee: Assignee? = null,

    @field:SerializedName("assignees") var assignees: List<Assignee>? = null,

    @field:SerializedName("author_association") var authorAssociation: String? = null,

    @field:SerializedName("body") var body: String? = null,

    @field:SerializedName("closed_at") var closedAt: String? = null,

    @field:SerializedName("comments") var comments: Long? = null,

    @field:SerializedName("comments_url") var commentsUrl: String? = null,

    @field:SerializedName("created_at") var createdAt: String? = null,

    @field:SerializedName("events_url") var eventsUrl: String? = null,

    @field:SerializedName("html_url") var htmlUrl: String? = null,

//    @field:SerializedName("labels") var labels: List<Label>? = null,

    @field:SerializedName("labels_url") var labelsUrl: String? = null,

    @field:SerializedName("locked") var locked: Boolean? = null,

//    @field:SerializedName("milestone") var milestone: Milestone? = null,

    @field:SerializedName("node_id") var nodeId: String? = null,

    @field:SerializedName("number") var number: Long? = null,

    @field:SerializedName("repository_url") var repositoryUrl: String? = null,

    @field:SerializedName("state") var state: String? = null,

    @field:SerializedName("title") var title: String? = null,

    @field:SerializedName("updated_at") var updatedAt: String? = null,

    @field:SerializedName("url") var url: String? = null,

    @field:SerializedName("user") var user: User? = null,

    var timeAdded: Long

)