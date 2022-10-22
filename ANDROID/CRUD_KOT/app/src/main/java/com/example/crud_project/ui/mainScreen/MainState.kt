package com.example.crud_project.ui.mainScreen

import com.example.crud_project.domain.model.Employee

data class MainState(
    val employee: Employee,
    val error: String? = null
) {}