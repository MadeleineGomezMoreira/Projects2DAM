package com.example.crud_project.domain.usecases.employees

import com.example.crud_project.daoData.Repository
import com.example.crud_project.domain.model.Employee

class DeleteEmployeeUseCase {

    operator fun invoke(employee: Employee) =
        Repository.deleteEmployee(employee)

}