package com.firebot.assignment.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class IssueFetchResults(
    val data: LiveData<List<Issues>>,
    val networkErrors: MutableLiveData<String>
) {
}