package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.Repository

class GetEmployeesUseCase {

    operator fun invoke() =
        Repository.getEmployees()
}