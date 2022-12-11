package com.example.recyclerdetail.ui.editEmployeesActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recyclerdetail.data.Converters
import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.EmployeeRoomDatabase
import com.example.recyclerdetail.databinding.ActivityEditEmployeesBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.ui.ScreenConstants
import com.example.recyclerdetail.ui.mainActivity.MainActivity
import com.example.recyclerdetail.ui.mainActivity.MainActivityViewModel
import com.example.recyclerdetail.ui.mainActivity.MainActivityViewModelFactory
import com.example.recyclerdetail.ui.mainActivity.MainEvent
import com.example.recyclerdetail.utils.StringProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate

class EditEmployeesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditEmployeesBinding


    private val viewModel: MainActivityViewModel by viewModels {

        MainActivityViewModelFactory(
            StringProvider(this),
            GetEmployees(EmployeeRepository(EmployeeRoomDatabase.getDatabase(this).employeeDao())),
            InsertEmployeeWithThings(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
            GetEmployeesDes(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
            InsertEmployee(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
            GetEmployeeById(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
            DeleteEmployee(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
            UpdateEmployee(
                EmployeeRepository(
                    EmployeeRoomDatabase.getDatabase(this).employeeDao()
                )
            ),
        )
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditEmployeesBinding.inflate(layoutInflater).apply {

            val view = root
            setContentView(view)

            //TODO: all of this might be WRONG - CHECK!

            var date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now()
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                date = Converters().fromTimestamp("01-01-2020")
            }

            //TODO: al añadir un nuevo empleado, su ID no se debe editar, pues es autoincremental (asi q no lo tengo q meter yo)

            //Debería hacer un método que directamente meta un empleado por defecto y no deba pasar los datos por aquí :(

            var employee = Employee(
                ScreenConstants.NEW_EMPLOYEE, 1, "F", date, false, 0,
                listOf()
            ) //It looks like it doesn't make sense. Investigate!


            intent.extras?.let {
                val employeeId = it.getInt(ScreenConstants.ID_STRING)
                viewModel.handleEvent(MainEvent.GetEmployeeById(employeeId))
            }

            fun getEmployeeFromScreen(): Employee {
                val gender =
                    if (radioBtnF.isChecked) ScreenConstants.FEMALE else ScreenConstants.MALE
                return Employee(
                    textName.text.toString(),
                    textId.text.toString().toInt(),
                    gender,
                    Converters().fromTimestamp(textBirthYear.text.toString())!!,
                    activeSwitch.isChecked,
                    textPhone.text.toString().toInt(),
                    listOf()
                )
            }

            fun changeEmployee(employee: Employee) {

                val birthday = Converters().dateToTimestamp(employee.birthday)

                textName.setText(employee.name)
                textPhone.setText("${employee.phoneNumber}")
                textBirthYear.setText("$birthday")
                textId.setText(employee.id)
                activeSwitch.isChecked = employee.active
                if (employee.gender == ScreenConstants.FEMALE) {
                    radioBtnF.isChecked = true
                } else {
                    radioBtnM.isChecked = true
                }
            }


            val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
            datePickerBuilder.setTitleText("Select a date")

            var datePicker = datePickerBuilder.build()
            datePicker.addOnPositiveButtonClickListener {
                val selectedDate = datePicker.headerText
                textBirthYear.setText(selectedDate)
            }

            textBirthYear.setOnClickListener {
                datePicker.show(supportFragmentManager, "DATE_PICKER")
            }


            viewModel.uiState.observe(this@EditEmployeesActivity) { state ->
                employee = state.employee!!
                changeEmployee(employee)
            }

            fabDone.setOnClickListener {
                val newScreenEmployee = getEmployeeFromScreen()

                //viewModel.updateEmployee(employee, newScreenEmployee)
                //TODO: how does this method work??
                //viewModel.handleEvent(MainEvent.UpdateEmployee(employee, newScreenEmployee))

                val intent = Intent(view.context, MainActivity::class.java)
                ContextCompat.startActivity(view.context, intent, null)

            }


        }
    }
}