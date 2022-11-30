package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.model.toEmployeeEntity
import com.example.recyclerdetail.domain.model.Employee

class InsertEmployee(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke(employee: Employee) =
        employeeRepository.insertEmployee(employee.toEmployeeEntity())
}
