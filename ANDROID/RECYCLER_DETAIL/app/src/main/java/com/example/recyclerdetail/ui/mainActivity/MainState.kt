package com.example.recyclerdetail.ui.mainActivity

import com.example.recyclerdetail.domain.model.Employee


data class MainState(
    val employeeList: List<Employee>,
    val error: String? = null
)