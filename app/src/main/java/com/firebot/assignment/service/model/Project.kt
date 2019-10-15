package com.firebot.assignment.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.firebot.assignment.db.DataConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "projects")
data class Project (
    @PrimaryKey @field:SerializedName("author") var author: String? = null,

    @field:SerializedName("avatar") var avatar: String? = null,

    @TypeConverters(DataConverter::class)
    @field:SerializedName("builtBy") var builtBy: List<BuiltBy>? = null,

    @field:SerializedName("currentPeriodStars") var currentPeriodStars: Long? = null,

    @field:SerializedName("description") var description: String? = null,

    @field:SerializedName("forks") var forks: Long? = null,

    @field:SerializedName("name") var name: String? = null,

    @field:SerializedName("stars") var stars: Long? = null,

    @field:SerializedName("url") var url: String? = null

)

