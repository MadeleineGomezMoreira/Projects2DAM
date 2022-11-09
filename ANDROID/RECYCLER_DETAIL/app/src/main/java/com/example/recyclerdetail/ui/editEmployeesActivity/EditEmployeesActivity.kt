package com.example.recyclerdetail.ui.editEmployeesActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recyclerdetail.databinding.ActivityEditEmployeesBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.ui.mainActivity.MainActivity
import com.example.recyclerdetail.utils.StringProvider

class EditEmployeesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditEmployeesBinding


    private val viewModel: EditEmployeesViewModel by viewModels {
        EditEmployeesViewModel.EditEmployeesActivityViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIdUseCase(),
            UpdateEmployeeUseCase(),
        )
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditEmployeesBinding.inflate(layoutInflater).apply {

            var employee: Employee = Employee("0", "0", "0", 0, false, 0)
            val view = root
            setContentView(view)

            intent.extras?.let {
                val employeeId = it.getString("id")
                viewModel.getEmployeeById(employeeId.toString())
            }

            fun getEmployeeFromScreen(): Employee {
                val gender = if (radioBtnF.isChecked) "F" else "M"
                return Employee(
                    textName.text.toString(),
                    textId.text.toString(),
                    gender,
                    textBirthYear.text.toString().toInt(),
                    activeSwitch.isChecked,
                    textPhone.text.toString().toInt()
                )
            }

            fun changeEmployee(employee: Employee) {

                textName.setText(employee.name)
                textPhone.setText("${employee.phoneNumber}")
                textBirthYear.setText("${employee.birthYear}")
                textId.setText(employee.id)
                activeSwitch.isChecked = employee.active
                if (employee.gender == "F") {
                    radioBtnF.isChecked = true
                } else {
                    radioBtnM.isChecked = true
                }
            }

            viewModel.uiState.observe(this@EditEmployeesActivity) { state ->
                employee = state.employee
                changeEmployee(employee)
            }

            fabDone.setOnClickListener {
                val newScreenEmployee = getEmployeeFromScreen()
                viewModel.updateEmployee(employee, newScreenEmployee)
                changeEmployee(newScreenEmployee)
                Thread.sleep(500)

                val intent = Intent(view.context, MainActivity::class.java)
                ContextCompat.startActivity(view.context, intent, null)

            }


        }
    }
}