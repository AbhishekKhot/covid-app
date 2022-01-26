package com.example.covidtrackingapp.model

data class Regional(
    val confirmedCasesForeign: Int,
    val confirmedCasesIndian: Int,
    val deaths: Int,
    val discharged: Int,
    val loc: String,
    val totalConfirmed: Int
)