package com.firebot.assignment.service.model

import com.google.gson.annotations.SerializedName

class Label {
    @SerializedName("color")
    var color: String? = null
    @SerializedName("default")
    var default: Boolean? = null
    @SerializedName("description")
    var description: Any? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("node_id")
    var nodeId: String? = null
    @SerializedName("url")
    var url: String? = null

}