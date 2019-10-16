package com.firebot.assignment.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class ProjectFetchResults(
    val data: LiveData<List<Project>>,
    val networkErrors: MutableLiveData<String>
) {
}