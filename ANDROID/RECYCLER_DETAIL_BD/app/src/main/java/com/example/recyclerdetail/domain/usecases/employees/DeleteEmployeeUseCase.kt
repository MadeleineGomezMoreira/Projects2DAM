package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.Repository
import com.example.recyclerdetail.domain.model.Employee


class DeleteEmployeeUseCase {

    operator fun invoke(employee: Employee) =
        Repository.deleteEmployee(employee)

}