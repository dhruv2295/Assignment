package com.firebot.assignment.db

import androidx.room.TypeConverter
import com.firebot.assignment.service.model.BuiltBy
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun storeBuiltByData(creators: List<BuiltBy>): String {
        val gson = GsonBuilder().create()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun builtBy(creators: String): List<BuiltBy>? {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<List<String>>() {

        }.type
        val parser = JsonParser()
        val p = parser.parse(creators).asJsonArray
        return gson.fromJson<List<BuiltBy>>(p, type)
    }
}
