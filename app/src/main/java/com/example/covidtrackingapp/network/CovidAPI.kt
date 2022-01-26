package com.example.covidtrackingapp.network

import com.example.covidtrackingapp.model.CovidResponse
import retrofit2.http.GET
import retrofit2.Response

interface CovidAPI {
    @GET("/covid19-in/stats/latest")
    suspend fun getStatesData(): Response<CovidResponse>
}