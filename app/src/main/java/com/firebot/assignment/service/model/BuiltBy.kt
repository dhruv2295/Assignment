package com.firebot.assignment.service.model


import com.google.gson.annotations.SerializedName

class BuiltBy(
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("href") var href: String? = null,
    @SerializedName("username") var username: String? = null
)
