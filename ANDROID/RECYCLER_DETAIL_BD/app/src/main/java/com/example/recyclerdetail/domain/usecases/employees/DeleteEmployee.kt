package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.model.toEmployeeEntity
import com.example.recyclerdetail.domain.model.Employee

class DeleteEmployee(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke(employee: Employee) =
        employeeRepository.deleteEmployee(employee.toEmployeeEntity())
}