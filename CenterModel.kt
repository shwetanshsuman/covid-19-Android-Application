package com.shwetansh.covid_19tracker

data class CenterModel (
        val centerName: String,
        val centerAddress: String,
        val centerFromTime: String,
        val centerToTime: String,
        var fee_type: String,
        var ageLimit: Int,
        var vaccineName: String,
        var availableCapacity: Int
        )