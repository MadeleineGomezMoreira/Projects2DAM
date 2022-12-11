package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository

class GetEmployeeById(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke(id: Int) =
        employeeRepository.getEmployee(id)
}
