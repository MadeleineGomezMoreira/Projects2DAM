package com.example.crud_project.domain.model

data class Employee(
    val name: String,
    val id: String,
    val gender: String,
    val birthYear: Int,
    val active: Boolean,
    val phoneNumber: Int
) {


}