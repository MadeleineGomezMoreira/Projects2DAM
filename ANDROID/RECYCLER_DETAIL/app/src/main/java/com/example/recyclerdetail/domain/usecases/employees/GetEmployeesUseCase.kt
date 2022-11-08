package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.daoData.Repository

class GetEmployeesUseCase {

    operator fun invoke() =
        Repository.getEmployees()
}