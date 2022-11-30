package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository

class GetEmployees(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke() = employeeRepository.getEmployees()
}
