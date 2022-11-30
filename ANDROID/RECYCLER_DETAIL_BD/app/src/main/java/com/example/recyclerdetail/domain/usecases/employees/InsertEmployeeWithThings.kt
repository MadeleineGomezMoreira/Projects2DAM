package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.model.toEmployeeWithThings
import com.example.recyclerdetail.domain.model.Employee

class InsertEmployeeWithThings(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke(employee: Employee) =
        employeeRepository.insertEmployeeWithThings(employee.toEmployeeWithThings())
}
