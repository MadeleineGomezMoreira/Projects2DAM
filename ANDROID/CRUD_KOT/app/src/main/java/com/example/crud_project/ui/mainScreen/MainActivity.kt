package com.example.crud_project.ui.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.crud_project.databinding.ActivityMainBinding
import com.example.crud_project.domain.model.Employee
import com.example.crud_project.domain.usecases.employees.AddEmployeeUseCase
import com.example.crud_project.domain.usecases.employees.GetEmployeeByIndexUseCase
import com.example.crud_project.domain.usecases.employees.GetEmployeesUseCase
import com.example.crud_project.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var action : Int = 0;

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
            GetEmployeesUseCase(),
            AddEmployeeUseCase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            val view = root
            setContentView(view)

            action = Constants.ACTION_CREATE

            fun actionButtonShow (action : Int){
                when(action){
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

            //How do I check this periodically after every action?
            //Function that determines current action
            fun determineAction() : Int{
                var result : Int = 0
                result = if(etName.text.isEmpty() && etPhone.text.isEmpty() && etBirthYear.text.isEmpty() && etId.text.isEmpty() && !activeSwitch.isChecked && genderChip.text.isEmpty()){
                    Constants.ACTION_CREATE
                } else {
                    Constants.ACTION_UPDATE_DELETE
                }
                return result
            }


            fun changeEmployee(employee: Employee){
                etName.setText(employee.name)
                //This'll be useful in order to change a number value using setText (if we do it without the $, it'll be wrong)
                etPhone.setText("${employee.phoneNumber}")
                etBirthYear.setText("${employee.birthYear}")
                etId.setText(employee.id)
                activeSwitch.isChecked = employee.active
                genderChip.setText(employee.gender)
            }


            addButton.setOnClickListener {


                val name = etName.text.toString()
                val id = etId.text.toString()
                val gender = "F"
                val birthYear = etBirthYear.text.toString().toInt()
                val active = activeSwitch.isChecked
                val phone = etPhone.text.toString().toInt()

                val employee = Employee(name,id,gender,birthYear,active,phone)

                viewModel.addEmployee(employee)
                Toast.makeText(this@MainActivity, Constants.ADDED, Toast.LENGTH_SHORT).show()

            }



            viewButton.setOnClickListener {

                val employees : List<Employee> = viewModel.getEmployees()
                val index : Int = etNumber.text.toString().toInt()


                try {
                        val actualEmployee : Employee = viewModel.getEmployeeIndex(index)
                        changeEmployee(actualEmployee)

                }
                catch (e: java.lang.IndexOutOfBoundsException){
                    Toast.makeText(this@MainActivity, Constants.ERROR, Toast.LENGTH_SHORT).show()
                }

                actionButtonShow(determineAction())

            }


            arrowLeftButton.setOnClickListener {

                //un try catch para que si no hay nada en index pues que no
                // muestre nada o te ponga solo el numero 1 de index

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

