package com.example.crud_project.daoData

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.crud_project.domain.model.Employee
import android.util.Log

object Repository {

    private val employeeList = mutableListOf<Employee>()

    init {
        populateList();
    }

    private val mapEmployees = mutableMapOf<String, Employee>()

    //método para poblar la lista de empleados
    fun populateList() {
        employeeList.add(Employee("Martha", "12642", "F", 1999, true, 916921723))
        employeeList.add(Employee("Sarah", "27432", "F", 2000, true, 916921724))
        employeeList.add(Employee("Lilith", "32108", "F", 2001, false, 916921725))
        employeeList.add(Employee("Jacob", "43473", "M", 1997, false, 916921726))
        employeeList.add(Employee("Isaac", "52037", "M", 1998, true, 916921727))
    }

    fun addEmployee(employee: Employee) {
        employeeList.add(employee)
        Log.d("addEmployee", employeeList.size.toString())
    }

    fun getEmployees(): MutableList<Employee> {
        return employeeList
    }

    fun deleteEmployee(newEmployee: Employee) {
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



}