package com.example.recyclerdetail.domain.model

import java.time.LocalDate

data class Employee(
    val name: String,
    val id: Int,
    val gender: String,
    val birthday: LocalDate,
    val active: Boolean,
    val phoneNumber: Int,
    val things: List<Thing>?
)