package com.example.recyclerdetail.ui.EditEmployeesActivity

import com.example.recyclerdetail.domain.model.Employee

data class MainState(
    val employee: Employee,
    val error: String? = null
) {}