package com.example.crud_project.domain.usecases.employees

import com.example.crud_project.daoData.Repository
import com.example.crud_project.domain.model.Employee

class UpdateEmployeeUseCase {

    operator fun invoke(oldEmployee: Employee, newEmployee: Employee) =
        Repository.updateEmployee(oldEmployee, newEmployee)

}