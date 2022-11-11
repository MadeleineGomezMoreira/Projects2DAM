package com.example.recyclerdetail.ui.editEmployeesActivity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.utils.StringProvider

class EditEmployeesViewModel(

    private val stringProvider: StringProvider,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,

    ) : ViewModel() {
    private val _uiState = MutableLiveData<EditEmployeesState>()
    val uiState: LiveData<EditEmployeesState> get() = _uiState


    @RequiresApi(Build.VERSION_CODES.N)
    fun getEmployeeById(id: String) {
        _uiState.value = EditEmployeesState(
            employee = getEmployeeByIdUseCase(id)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun updateEmployee(oldEmployee: Employee, editedEmployee: Employee) {
        updateEmployeeUseCase(editedEmployee, oldEmployee)
        _uiState.value = EditEmployeesState(
            employee = getEmployeeByIdUseCase(editedEmployee.id)
        )
    }

    fun shownError() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    class EditEmployeesActivityViewModelFactory(
        private val stringProvider: StringProvider,
        private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,
        private val updateEmployeeUseCase: UpdateEmployeeUseCase,


        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditEmployeesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditEmployeesViewModel(
                    stringProvider,
                    getEmployeeByIdUseCase,
                    updateEmployeeUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}