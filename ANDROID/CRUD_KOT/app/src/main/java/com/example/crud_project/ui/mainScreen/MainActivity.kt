package com.example.crud_project.ui.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.crud_project.databinding.ActivityMainBinding
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.GetEmployeeByIndexUseCase
import com.example.crud_project.domain.usecases.employees.GetEmployeesUseCase
import com.example.crud_project.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
            GetEmployeesUseCase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            val view = root
            setContentView(view)

            fun changeEmployee(employee: Employee){
                etName.setText(employee.name)
                //This'll be useful in order to change a number value using setText (if we do it without the $, it'll be wrong)
                etPhone.setText("${employee.phoneNumber}")
                etDate.setText("${employee.birthYear}")
                etId.setText(employee.id)
                if(employee.active){
                    activeSwitch.isChecked = true
                }
                genderChip.setText(employee.gender)
            }


            viewButton.setOnClickListener {
                //I need this to cycle through the employees going from the last to the first
                //error control (cannot show a number higher than the list or 0 by looking at etNumber.text.toString().toInt())
                val employees : List<Employee> = viewModel.getEmployees()

                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }


            arrowLeftButton.setOnClickListener {

                val employees : List<Employee> = viewModel.getEmployees()
                if(etNumber.text.toString().toInt() < employees.size){
                    etNumber.setText(employees.size.toString())
                }
                else{
                    etNumber.setText("${etNumber.text.toString().toInt() - 1}")
                }
                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }

            arrowRightButton.setOnClickListener {



                val employees : List<Employee> = viewModel.getEmployees()
                if(etNumber.text.toString().toInt() > employees.size || etNumber.text.toString().toInt() == 0){
                    etNumber.setText("1")
                }
                else{
                    etNumber.setText("${etNumber.text.toString().toInt() + 1}")
                }
                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }






        }
    }



}

