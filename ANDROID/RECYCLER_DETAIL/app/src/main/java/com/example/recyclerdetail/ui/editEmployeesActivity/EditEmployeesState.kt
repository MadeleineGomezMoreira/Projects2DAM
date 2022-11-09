package com.example.recyclerdetail.ui.editEmployeesActivity

import com.example.recyclerdetail.domain.model.Employee

data class EditEmployeesState(
    val employee: Employee,
    val error: String? = null
) {}