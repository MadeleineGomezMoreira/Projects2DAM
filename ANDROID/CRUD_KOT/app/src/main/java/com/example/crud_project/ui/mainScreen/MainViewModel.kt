package com.example.crud_project.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.GetEmployeeByIndexUseCase
import com.example.crud_project.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun getEmployeeIndex(index: Int) : Employee{
        val employee = getEmployeeByIndexUseCase(index)
            _uiState.value = MainState(
                employee = employee
            )
        return employee
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    class MainViewModelFactory(
        private val stringProvider: StringProvider,
        private val getEmployeeByIndexUseCase: GetEmployeeByIndexUseCase,


        ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(
                    stringProvider,
                    getEmployeeByIndexUseCase,

                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    //cuando el usuario ponga el índex, de 1- lo que sea, se le mostrará el empleado


}


