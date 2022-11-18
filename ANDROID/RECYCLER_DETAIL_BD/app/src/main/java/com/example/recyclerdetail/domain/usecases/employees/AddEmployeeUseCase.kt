package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.daoData.Repository
import com.example.recyclerdetail.domain.model.Employee

class AddEmployeeUseCase {

    operator fun invoke(employee: Employee) = Repository.addEmployee(employee)
}