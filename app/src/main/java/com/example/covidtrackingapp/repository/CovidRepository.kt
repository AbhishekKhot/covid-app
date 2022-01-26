package com.example.covidtrackingapp.repository

import com.example.covidtrackingapp.network.RetrofitInstance

class CovidRepository {
    suspend fun getStateData() = RetrofitInstance.api.getStatesData()
}