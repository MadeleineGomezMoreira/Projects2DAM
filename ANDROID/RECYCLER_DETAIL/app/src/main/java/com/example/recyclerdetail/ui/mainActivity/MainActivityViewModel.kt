package com.example.recyclerdetail.ui

import com.example.recyclerdetail.utils.StringProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.ui.mainActivity.MainActivity
import com.example.recyclerdetail.ui.mainActivity.MainState

class MainActivityViewModel(
    private val stringProvider: StringProvider,
    private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,

    ) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    /*
    fun getEmployeeIndex(index: Int) {
        val employee = getEmployeeByIndexUseCase(index)
        _uiState.value = MainState(
            employee = employee
        )
    }

     */

    fun getEmployeeList() {
        val employeeList = getEmployeesUseCase()
        _uiState.value = MainState(
            employeeList = employeeList
        )
    }

    fun getListSize(): Int {
        val employees = getEmployeesUseCase()
        return employees.size
    }

    fun addEmployee(employee: Employee) {
        addEmployeeUseCase(employee)
    }

    fun deleteEmployee(employee: Employee) {
        deleteEmployeeUseCase(employee)
    }

    fun shownError() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    class MainActivityViewModelFactory(
        private val stringProvider: StringProvider,
        private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,
        private val getEmployeesUseCase: GetEmployeesUseCase,
        private val addEmployeeUseCase: AddEmployeeUseCase,
        private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
        private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,


        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(
                    stringProvider,
                    getEmployeeByIndexUseCase,
                    getEmployeesUseCase,
                    addEmployeeUseCase,
                    deleteEmployeeUseCase,
                    getEmployeeByIdUseCase,

                    ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}


