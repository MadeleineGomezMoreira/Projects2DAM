package com.example.recyclerdetail.ui.mainActivity

import com.example.recyclerdetail.domain.model.Employee

sealed class MainEvent {

    class InsertEmployee(val employee: Employee) : MainEvent()
    class GetEmployeeById(val id: Int) : MainEvent()
    class DeleteEmployee(val employee: Employee) : MainEvent()
    class UpdateEmployee(val employee: Employee) : MainEvent()
    object GetEmployees : MainEvent()
    object ErrorShown : MainEvent()

}