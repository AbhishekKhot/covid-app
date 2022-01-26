package com.example.covidtrackingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidtrackingapp.repository.CovidRepository

class CovidViewModelProviderFactory(val covidRepository: CovidRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CovidViewModel(covidRepository) as T
    }
}