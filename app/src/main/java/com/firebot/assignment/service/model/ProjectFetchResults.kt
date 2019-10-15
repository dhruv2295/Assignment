package com.firebot.assignment.service.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProjectFetchResults(
    data: LiveData<List<Project>>,
    networkErrors: MutableLiveData<String>
) {
}