package com.example.crud_project.ui.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crud_project.databinding.ActivityMainBinding
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.*
import com.example.crud_project.utils.StringProvider
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var action: Int = 0

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
            GetEmployeesUseCase(),
            AddEmployeeUseCase(),
            UpdateEmployeeUseCase(),
            DeleteEmployeeUseCase(),
        )
    }

    //THIS WILL CHANGE THE BUTTONS BASED ON THE USERS ACTIONS - IT RUNS ALL THE TIME
    private fun setActionButton() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate({
            runOnUiThread {
                with(binding) {
                    action = Constants.ACTION_CREATE

                    //Function that changes buttons' visibility (CREATE or UPDATE + DELETE)
                    fun actionButtonShow(action: Int) {
                        when (action) {
                            Constants.ACTION_CREATE -> {
                                deleteButton.visibility = View.INVISIBLE
                                updateButton.visibility = View.INVISIBLE
                                addButton.visibility = View.VISIBLE
                            }
                            Constants.ACTION_UPDATE_DELETE -> {
                                addButton.visibility = View.INVISIBLE
                                deleteButton.visibility = View.VISIBLE
                                updateButton.visibility = View.VISIBLE
                            }
                        }
                    }

                    //Function that determines current action based on whether the index field is empty or not
                    fun determineAction(): Int {
                        val result =
                            if (textIndex.text.isNullOrBlank()) {
                                Constants.ACTION_CREATE
                            } else {
                                Constants.ACTION_UPDATE_DELETE
                            }
                        return result
                    }

                    actionButtonShow(determineAction())
                }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            val view = root
            setContentView(view)

            setActionButton()

            //SHOW EMPLOYEE'S VALUES ON FIELDS
            fun changeEmployee(employee: Employee) {

                textName.setText(employee.name)
                textPhone.setText("${employee.phoneNumber}")
                textBirthYear.setText("${employee.birthYear}")
                textId.setText(employee.id)
                activeSwitch.isChecked = employee.active
                genderChip.text = employee.gender
            }

            addButton.setOnClickListener {

                val name = textName.text.toString()
                val id = textId.text.toString()
                val gender = "F"
                val birthYear = textBirthYear.text.toString().toInt()
                val active = activeSwitch.isChecked
                val phone = textPhone.text.toString().toInt()

                val employee = Employee(name, id, gender, birthYear, active, phone)

                viewModel.addEmployee(employee)
                Toast.makeText(this@MainActivity, Constants.ADDED, Toast.LENGTH_SHORT).show()
            }

            deleteButton.setOnClickListener {
                val index = textIndex.text.toString().toInt()
                var employee: Employee

                try {
                    viewModel.deleteEmployee(index)

                    if (textIndex.text.toString().toInt() > viewModel.getListSize()) {
                        textIndex.setText("${viewModel.getListSize()}")
                    }
                    viewModel.getEmployeeIndex(textIndex.text.toString().toInt())
                    viewModel.uiState.observe(this@MainActivity) { state ->
                        employee = state.employee
                        changeEmployee(employee)
                    }

                    Toast.makeText(this@MainActivity, Constants.DELETED, Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, Constants.NOT_FOUND, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            updateButton.setOnClickListener {

                val newEmployee = Employee(
                    textName.text.toString(),
                    textId.text.toString(),
                    genderChip.text.toString(),
                    textBirthYear.text.toString().toInt(),
                    activeSwitch.isChecked,
                    textPhone.text.toString().toInt()
                )

                val index = textIndex.text.toString().toInt()
                viewModel.getEmployeeIndex(index)

                viewModel.updateEmployee(index, newEmployee)

                viewModel.getEmployeeIndex(textIndex.text.toString().toInt())

                changeEmployee(newEmployee)


                Toast.makeText(this@MainActivity, Constants.UPDATED, Toast.LENGTH_SHORT).show()
            }

            viewButton.setOnClickListener {
                var employee: Employee
                val index: Int = textIndex.text.toString().toInt()

                if (!textIndex.text.isNullOrBlank()) {
                    try {
                        viewModel.getEmployeeIndex(index)
                        viewModel.uiState.observe(this@MainActivity) { state ->
                            employee = state.employee
                            changeEmployee(employee)
                        }

                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        Toast.makeText(this@MainActivity, Constants.ERROR, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, Constants.BLANK, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            arrowLeftButton.setOnClickListener {

                if (textIndex.text.toString().toInt() - 1 == 0) {
                    textIndex.setText(viewModel.getListSize().toString())
                } else {
                    textIndex.setText("${textIndex.text.toString().toInt() - 1}")
                }

                val index: Int = textIndex.text.toString().toInt()
                var nextEmployee: Employee

                try {
                    viewModel.getEmployeeIndex(index)
                    viewModel.uiState.observe(this@MainActivity) { state ->
                        nextEmployee = state.employee
                        changeEmployee(nextEmployee)

                    }
                } catch (e: IndexOutOfBoundsException) {
                    Toast.makeText(this@MainActivity, Constants.NOT_FOUND, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            arrowRightButton.setOnClickListener {

                if (textIndex.text.toString().toInt() + 1 > viewModel.getListSize()) {

                    textIndex.setText(Constants.BASE_VALUE)
                } else {
                     val newText = "${textIndex.text.toString().toInt() + 1}"
                    textIndex.setText(newText)
                }

                val index: Int = textIndex.text.toString().toInt()
                var nextEmployee: Employee

                try {
                    viewModel.getEmployeeIndex(index)
                    viewModel.uiState.observe(this@MainActivity) { state ->
                        nextEmployee = state.employee
                        changeEmployee(nextEmployee)

                    }
                } catch (e: IndexOutOfBoundsException) {
                    Toast.makeText(this@MainActivity, Constants.NOT_FOUND, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

