package com.example.crud_project.daoData

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.crud_project.domain.model.Employee

class Repository {

    private val employeeList = mutableListOf<Employee>()

    init {
        employeeList.add(Employee("e1", "1", "F", 1999, true, 916921723))
        employeeList.add(Employee("e2", "2", "F", 2000, true, 916921724))
        employeeList.add(Employee("e3", "3", "F", 2001, false, 916921725))
    }

    private val mapEmployees = mutableMapOf<String, Employee>()

    fun addEmployee(employee: Employee) =
        employeeList.add(employee)

    fun getEmployees(): MutableList<Employee> {
        return employeeList
    }

    fun removeEmployee(newEmployee: Employee) {
        employeeList.remove(newEmployee)
    }

    fun updateEmployee(newEmployee: Employee, oldEmployee: Employee) {
        employeeList.remove(oldEmployee)
        employeeList.add(newEmployee)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun idExists(id: String): Boolean {
        var response: Boolean = false
        if (employeeList.stream().findAny().filter { it.id == id }.isPresent()) {
            response = true
        }
        response = false

        return response

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getEmployee(id: String): Employee? {
        var response: Employee? = null
        if (idExists(id)) {
            var response: Employee? = employeeList.find { it.id == id }!!
        }
        return response;
    }

    fun employeeExists(newEmployee: Employee): Boolean {
        return employeeList.contains(newEmployee)
    }

    fun getEmployeeByIndex(index: Int): Employee {
        return employeeList[index]
    }

    //Falta la función que hará que se muestre el empleado en el formulario según el index

    companion object {

        fun getInstance(): Repository = Repository()

    }


}