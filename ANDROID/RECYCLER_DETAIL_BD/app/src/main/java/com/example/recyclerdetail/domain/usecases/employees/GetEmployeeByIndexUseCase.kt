package com.example.recyclerdetail.domain.usecases.employees

import com.example.recyclerdetail.data.Repository

class GetEmployeeByIndexUseCase {

    operator fun invoke(index: Int) =
        Repository.getEmployeeByIndex(index)
}
