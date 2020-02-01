package com.firebot.assignment.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class CommentFetchResults(
    val data: LiveData<List<Comment>>,
    val networkErrors: MutableLiveData<String>
)