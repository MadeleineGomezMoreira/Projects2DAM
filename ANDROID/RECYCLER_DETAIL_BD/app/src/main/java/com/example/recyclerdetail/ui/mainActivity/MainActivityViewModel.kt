package com.example.recyclerdetail.ui.mainActivity

import androidx.lifecycle.*
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.utils.StringProvider
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val stringProvider: StringProvider,
    private val getEmployees: GetEmployees,
    private val insertEmployeeWithThings: InsertEmployeeWithThings,
    private val getEmployeesDes: GetEmployeesDes,
    private val insertEmployee: InsertEmployee,
    private val getEmployeeById: GetEmployeeById,
    private val deleteEmployee: DeleteEmployee,
    private val updateEmployee: UpdateEmployee,

    ) : ViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> get() = _employees

    private val _employee = MutableLiveData<Employee>()
    val employee: LiveData<Employee> get() = _employee

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    init {
        getEmployees()
    }

    private fun getEmployees() {
        viewModelScope.launch {
            try {
                _employees.value = getEmployees.invoke()
                _uiState.value = MainState(getEmployees.invoke())
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    // TODO: fix handle events method


    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetEmployees -> {
                getEmployees
            }
            is MainEvent.InsertEmployee -> {
                insertEmployee(event.employee)
                getEmployees
            }
            MainEvent.ErrorShown -> _uiState.value = _uiState.value?.copy(error = null)
            is MainEvent.GetEmployeeById -> {
                getEmployeeById(event.id)
                //shouldn't I make _employee = getEmployeeById(event.id!!) ?
            }
            is MainEvent.DeleteEmployee -> {
                deleteEmployee(event.employee)
                getEmployees
            }
            is MainEvent.UpdateEmployee -> {
                updateEmployee(event.employee)
                getEmployees
            }
        }
    }

    private fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                updateEmployee.invoke(employee)
                _uiState.value = _uiState.value?.copy(isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                deleteEmployee.invoke(employee)
                getEmployees
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun getEmployeeById(id: Int) {
        viewModelScope.launch {
            try {
                _employee.value = getEmployeeById.invoke(id)
                _uiState.value = MainState(getEmployees.invoke())
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }


    private fun getEmployeesDes() {
        viewModelScope.launch {
            val employees = getEmployeesDes.invoke()
            _employees.value = employees
            _uiState.value = MainState(getEmployees.invoke())

        }
    }

    private fun insertEmployee(employee: Employee) {
        viewModelScope.launch {
            _uiState.value = MainState(isLoading = true)
            insertEmployee.invoke(employee)
        }
    }

    private fun insertEmployeeWithThings(employee: Employee) {
        viewModelScope.launch {
            try {
                insertEmployee.invoke(employee)
                _uiState.value =
                    _uiState.value?.copy(employeeList = getEmployees.invoke()) ?: MainState(
                        employeeList = getEmployees.invoke()
                    )
            } catch (e: Exception) {
                _error.value = e.message
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }

        fun shownError() {
            _uiState.value = _uiState.value?.copy(error = null)
        }

    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */

class MainActivityViewModelFactory(
    private val stringProvider: StringProvider,
    private val getEmployees: GetEmployees,
    private val insertEmployeeWithThings: InsertEmployeeWithThings,
    private val getEmployeesDes: GetEmployeesDes,
    private val insertEmployee: InsertEmployee,
    private val getEmployeeById: GetEmployeeById,
    private val deleteEmployee: DeleteEmployee,
    private val updateEmployee: UpdateEmployee,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(
                stringProvider,
                getEmployees,
                insertEmployeeWithThings,
                getEmployeesDes,
                insertEmployee,
                getEmployeeById,
                deleteEmployee,
                updateEmployee,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


