package com.example.crud_project.ui.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
                activeSwitch.isChecked = employee.active
                genderChip.setText(employee.gender)
            }


            viewButton.setOnClickListener {

                val employees : List<Employee> = viewModel.getEmployees()
                val index : Int = etNumber.text.toString().toInt()-1
                if (index < employees.size+1 && index > 0){
                    val actualEmployee : Employee = viewModel.getEmployeeIndex(index)
                    changeEmployee(actualEmployee)
                }
                else{
                    Toast.makeText(this@MainActivity, Constants.ERROR, Toast.LENGTH_SHORT).show()
                }

            }


            arrowLeftButton.setOnClickListener {

                val employees : List<Employee> = viewModel.getEmployees()
                etNumber.setText("${etNumber.text.toString().toInt() - 1}")
                println(employees.size.toString())
                if(etNumber.text.toString().toInt()-1 < 0){
                    etNumber.setText(employees.size.toString())
                }

                val index : Int = etNumber.text.toString().toInt()-1
                val actualEmployee : Employee = viewModel.getEmployeeIndex(index)
                changeEmployee(actualEmployee)

            }

            arrowRightButton.setOnClickListener {

                val employees : List<Employee> = viewModel.getEmployees()
                etNumber.setText("${etNumber.text.toString().toInt() + 1}")
                if(etNumber.text.toString().toInt()-1 > employees.size-1){

                    etNumber.setText(Constants.BASEVALUE)
                }

                val index : Int = etNumber.text.toString().toInt()-1
                val actualEmployee : Employee = viewModel.getEmployeeIndex(index)
                changeEmployee(actualEmployee)

            }






        }
    }



}

