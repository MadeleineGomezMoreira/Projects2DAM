package com.example.crud_project.dao

import com.example.crud_project.model.Employee

class DaoEmployee {

    val employees: MutableList<Employee> = mutableListOf<Employee>()

    fun getEmployeeList(): MutableList<Employee> {
        return this.employees
    }

    fun addEmployee(newEmployee: Employee) {
        employees.add(newEmployee)
    }

    fun removeEmployee(newEmployee: Employee) {
        employees.remove(newEmployee)
    }

    fun updateEmployee(newEmployee: Employee, oldEmployee: Employee) {
        employees.remove(oldEmployee)
        employees.add(newEmployee)
    }

    fun employeeExists(newEmployee: Employee): Boolean {
        return employees.contains(newEmployee)
    }

    fun findEmployee(id: String): Employee {
        var response: Employee = Employee("a", "b", "c", 1999, false, 916021723)
        for (employee in employees) {
            if (employee.id == id) {
                response = employee
            }
        }
        return response
    }

}