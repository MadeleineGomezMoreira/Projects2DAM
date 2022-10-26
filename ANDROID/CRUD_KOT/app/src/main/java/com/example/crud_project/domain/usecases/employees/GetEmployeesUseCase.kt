package com.example.crud_project.domain.usecases.employees

import com.example.crud_project.daoData.Repository

class GetEmployeesUseCase {

    operator fun invoke() =
        Repository.getEmployees()
}