package com.firebot.assignment.service.model

import com.google.gson.annotations.SerializedName

class Comment {
    @SerializedName("author_association")
    var authorAssociation: String? = null
    @SerializedName("body")
    var body: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("html_url")
    var htmlUrl: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("issue_url")
    var issueUrl: String? = null
    @SerializedName("node_id")
    var nodeId: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("user")
    var user: User? = null

}