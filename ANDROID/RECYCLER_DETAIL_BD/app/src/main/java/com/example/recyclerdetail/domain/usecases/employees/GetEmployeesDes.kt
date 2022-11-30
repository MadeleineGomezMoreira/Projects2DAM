package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.model.toEmployee
import com.example.recyclerdetail.data.model.toThing

class GetEmployeesDes(private val employeeRepository: EmployeeRepository) {

    suspend fun invoke(id:Int) = employeeRepository.getEmployeeWithThings(id).toEmployee()

    suspend fun invoke() = employeeRepository.getEmployeesDes().map { it.toEmployee() }

    suspend fun invokeThings() = employeeRepository.getThings().map { it.toThing() }
}
