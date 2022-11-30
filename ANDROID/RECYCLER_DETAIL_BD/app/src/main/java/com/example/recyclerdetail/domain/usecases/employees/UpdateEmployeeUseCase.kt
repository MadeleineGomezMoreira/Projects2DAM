package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.Repository
import com.example.recyclerdetail.domain.model.Employee


class UpdateEmployeeUseCase {

    operator fun invoke(oldEmployee: Employee, newEmployee: Employee) =
        Repository.updateEmployee(oldEmployee, newEmployee)

}