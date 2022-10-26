package com.example.crud_project.domain.usecases.employees

import com.example.crud_project.daoData.Repository

class GetEmployeeByIndexUseCase {

    operator fun invoke(index : Int) =
        Repository.getEmployeeByIndex(index)
}
