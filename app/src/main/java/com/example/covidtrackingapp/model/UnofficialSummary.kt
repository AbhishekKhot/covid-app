package com.example.covidtrackingapp.model

data class UnofficialSummary(
    val active: Int,
    val deaths: Int,
    val recovered: Int,
    val source: String,
    val total: Int
)