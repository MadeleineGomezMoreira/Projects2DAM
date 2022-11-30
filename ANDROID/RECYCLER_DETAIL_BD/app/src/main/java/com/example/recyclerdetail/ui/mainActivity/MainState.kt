package com.example.recyclerdetail.ui.mainActivity

import com.example.recyclerdetail.domain.model.Employee


data class MainState(
    val employeeList: List<Employee> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)