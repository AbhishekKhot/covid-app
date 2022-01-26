package com.example.covidtrackingapp.model

data class CovidResponse(
    val `data`: Data,
    val lastOriginUpdate: String,
    val lastRefreshed: String,
    val success: Boolean
)