package com.firebot.assignment.db

import com.firebot.assignment.service.model.BuiltBy
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class DataConverterTest {

    val creators = listOf(
        BuiltBy("avatar", "href", "username"),
        BuiltBy("avatar", "href", "username"),
        BuiltBy("avatar", "href", "username"))

    @Test
    fun builtByToString() {
        Assert.assertEquals(GsonBuilder().create().toJson(creators), DataConverter().builtByToString(creators))
    }

    @Test
    fun stringToBuiltBy() {
        Assert.assertEquals(DataConverter().stringToBuiltBy(GsonBuilder().create().toJson(creators))?.get(0)?.avatar, creators[0].avatar)
        Assert.assertEquals(DataConverter().stringToBuiltBy(GsonBuilder().create().toJson(creators))?.get(1)?.avatar, creators[1].avatar)
        Assert.assertEquals(DataConverter().stringToBuiltBy(GsonBuilder().create().toJson(creators))?.get(2)?.avatar, creators[2].avatar)
    }
}