package com.example.covidtrackingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtrackingapp.model.CovidResponse
import com.example.covidtrackingapp.other.Resource
import com.example.covidtrackingapp.repository.CovidRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CovidViewModel(val repository: CovidRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<CovidResponse>> = MutableLiveData()

    init {
        getBreakingNews()
    }

    fun getBreakingNews() = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = repository.getStateData()
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<CovidResponse>): Resource<CovidResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}