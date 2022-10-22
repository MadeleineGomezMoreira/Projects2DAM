package com.example.crud_project.ui.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.crud_project.databinding.ActivityMainBinding
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.GetEmployeeByIndexUseCase
import com.example.crud_project.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
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
                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }

            arrowLeftButton.setOnClickListener {

                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }

            arrowRightButton.setOnClickListener {
                val actualEmployee : Employee = viewModel.getEmployeeIndex(etNumber.text.toString().toInt()-1)

                changeEmployee(actualEmployee)

            }




        }
    }



}

