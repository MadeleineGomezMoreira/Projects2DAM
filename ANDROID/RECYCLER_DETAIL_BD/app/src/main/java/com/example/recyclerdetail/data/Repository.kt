package com.example.recyclerdetail.data


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.recyclerdetail.domain.model.Employee
import timber.log.Timber

object Repository {

    private val employeeList = mutableListOf<Employee>()


    init {
        populateList()
    }

    private val mapEmployees = mutableMapOf<String, Employee>()

    //THIS WILL POPULATE THE LIST OF EMPLOYEES
    private fun populateList() {
        employeeList.add(Employee("Martha", "12642", "F", 1999, true, 916921723))
        employeeList.add(Employee("Sarah", "27432", "F", 2000, true, 916921724))
        employeeList.add(Employee("Lilith", "32108", "F", 2001, false, 916921725))
        employeeList.add(Employee("Jacob", "43473", "M", 1997, false, 916921726))
        employeeList.add(Employee("Isaac", "52037", "M", 1998, true, 916921727))
        employeeList.add(Employee("Samantha", "72022", "F", 1988, true, 916921728))
    }

    fun addEmployee(employee: Employee) {
        employeeList.add(employee)
    }

    fun getEmployees(): MutableList<Employee> {
        Timber.tag("Repository").d("$employeeList")
        employeeList.sortBy { it.name }
        return employeeList
    }

    fun deleteEmployee(employee: Employee) {
        employeeList.remove(employee)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun updateEmployee(oldEmployee: Employee, newEmployee: Employee) {

        employeeList.indexOf(oldEmployee).let {
            employeeList[it] = newEmployee
        }

    }

    fun getEmployeeByIndex(index: Int): Employee {
        return employeeList[index - 1]
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getEmployeeById(id: String): Employee {
        return employeeList.stream().filter { e -> e.id == id }.findFirst().get()
    }


}