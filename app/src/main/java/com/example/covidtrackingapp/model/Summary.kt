package com.example.covidtrackingapp.model

data class Summary(
    val confirmedButLocationUnidentified: Int,
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val total: Int
)