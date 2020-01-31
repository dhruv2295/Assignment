package com.firebot.assignment.db

import androidx.room.TypeConverter
import com.firebot.assignment.service.model.Assignee
import com.firebot.assignment.service.model.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun assigneeListToString(creators: List<Assignee>?): String {
        val gson = GsonBuilder().create()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun stringToAssigneeList(creators: String): List<Assignee>? {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<List<Assignee>>() {}.type
        val parser = JsonParser()
        val p = parser.parse(creators).asJsonArray
        return gson.fromJson<List<Assignee>>(p, type)
    }

    @TypeConverter
    fun assigneeToString(creators: Assignee?): String {
        val gson = GsonBuilder().create()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun stringToAssignee(creators: String): Assignee? {
        val gson = GsonBuilder().create()

       return gson.fromJson(creators, Assignee::class.java)
    }

    @TypeConverter
    fun userToString(creators: User?): String {
        val gson = GsonBuilder().create()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun stringToUser(creators: String): User? {
        val gson = GsonBuilder().create()

        return gson.fromJson(creators, User::class.java)
    }
}
