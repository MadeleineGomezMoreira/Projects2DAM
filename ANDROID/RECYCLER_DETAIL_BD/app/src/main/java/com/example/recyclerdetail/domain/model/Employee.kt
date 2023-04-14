package com.example.recyclerdetail.domain.model

import java.time.LocalDate

data class Employee(
    val name: String,
    val gender: String,
    val birthday: LocalDate,
    val active: Boolean,
    val phoneNumber: Int,
    val things: List<Thing>?
){
    constructor(name: String,
                id : Int,
                gender: String,
                birthday: LocalDate,
                active: Boolean,
                phoneNumber: Int,
                things: List<Thing>?):this(name,gender,birthday,active,phoneNumber,things)
}