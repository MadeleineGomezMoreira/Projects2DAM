package com.example.crud_project.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.*
import com.example.crud_project.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,

    ) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun getEmployeeIndex(index: Int) {
        val employee = getEmployeeByIndexUseCase(index)
        _uiState.value = MainState(
            employee = employee
        )
    }

    fun getListSize(): Int {
        val employees = getEmployeesUseCase()
        return employees.size
    }

    fun addEmployee(employee: Employee) {
        addEmployeeUseCase(employee)
    }

    fun deleteEmployee(index: Int) {
        val employee = getEmployeeByIndexUseCase(index)
        deleteEmployeeUseCase(employee)
    }

    fun updateEmployee(index: Int, newEmployee: Employee) {
        val oldEmployee = getEmployeeByIndexUseCase(index)
        updateEmployeeUseCase(oldEmployee, newEmployee)
    }

    fun shownError() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    class MainViewModelFactory(
        private val stringProvider: StringProvider,
        private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,
        private val getEmployeesUseCase: GetEmployeesUseCase,
        private val addEmployeeUseCase: AddEmployeeUseCase,
        private val updateEmployeeUseCase: UpdateEmployeeUseCase,
        private val deleteEmployeeUseCase: DeleteEmployeeUseCase,


        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(
                    stringProvider,
                    getEmployeeByIndexUseCase,
                    getEmployeesUseCase,
                    addEmployeeUseCase,
                    updateEmployeeUseCase,
                    deleteEmployeeUseCase,

                    ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}


