package com.example.recyclerdetail.data

import com.example.recyclerdetail.data.model.EmployeeEntity
import com.example.recyclerdetail.data.model.EmployeeWithThings
import com.example.recyclerdetail.data.model.toEmployee

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    suspend fun getEmployees() = employeeDao.getEmployeesAsc().map { it.toEmployee() }

    suspend fun getEmployee(id: Int) = employeeDao.getEmployee(id).toEmployee()

    suspend fun getThings() = employeeDao.getThings()

    suspend fun getEmployeesDes() = employeeDao.getEmployeesDes()

    suspend fun getEmployeeWithThings(id: Int) = employeeDao.getEmployeeWithThings(id)

    suspend fun getEmployeeWithThings() = employeeDao.getEmployeeWithThings()

    suspend fun insertEmployee(employee: EmployeeEntity) = employeeDao.insert(employee)

    suspend fun deleteEmployee(employee: EmployeeEntity) = employeeDao.delete(employee)

    suspend fun updateEmployee(employee: EmployeeEntity) = employeeDao.update(employee)

    suspend fun insertEmployeeWithThings(employee: EmployeeWithThings) =
        employeeDao.createTransaction(employee)
}