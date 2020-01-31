package com.firebot.assignment.service.model

import com.google.gson.annotations.SerializedName

class Milestone {
    @SerializedName("closed_at")
    var closedAt: String? = null
    @SerializedName("closed_issues")
    var closedIssues: Long? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("creator")
    var creator: Creator? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("due_on")
    var dueOn: String? = null
    @SerializedName("html_url")
    var htmlUrl: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("labels_url")
    var labelsUrl: String? = null
    @SerializedName("node_id")
    var nodeId: String? = null
    @SerializedName("number")
    var number: Long? = null
    @SerializedName("open_issues")
    var openIssues: Long? = null
    @SerializedName("state")
    var state: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("url")
    var url: String? = null

}