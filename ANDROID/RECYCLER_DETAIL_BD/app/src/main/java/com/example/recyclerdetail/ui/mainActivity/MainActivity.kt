package com.example.recyclerdetail.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdetail.R
import com.example.recyclerdetail.daoData.Repository
import com.example.recyclerdetail.databinding.ActivityMainBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.ui.AdapterEmployees
import com.example.recyclerdetail.ui.ScreenConstants
import com.example.recyclerdetail.utils.StringProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.MainActivityViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
            GetEmployeesUseCase(),
            AddEmployeeUseCase(),
            DeleteEmployeeUseCase(),
            GetEmployeeByIdUseCase(),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            val view = root
            setContentView(view)

            setSupportActionBar(bottomAppBar)

            var list: List<Employee>

            list = Repository.getEmployees()

            viewModel.uiState.observe(this@MainActivity) { state ->
                list = state.employeeList
            }

            val recyclerEmployees: RecyclerView = recycler
            val adapter = AdapterEmployees(list)

            list.let {
                recyclerEmployees.adapter = adapter
                recyclerEmployees.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            fab.setOnClickListener {
                try {
                    //Add new employee
                    val defaultEmployee = Employee(ScreenConstants.NEW_EMPLOYEE, ScreenConstants.ZERO_STRING, ScreenConstants.ZERO_STRING, 0, false, 0)
                    viewModel.addEmployee(defaultEmployee)
                    recreate()
                    adapter.notifyDataSetChanged()
                    Snackbar.make(view, ScreenConstants.EMPLOYEE_ADDED, Snackbar.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Snackbar.make(view, ScreenConstants.EMPLOYEE_ADDED_ERROR, Snackbar.LENGTH_SHORT).show()
                }
            }

            bottomAppBar.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.appBarDelete -> {
                        try {
                            adapter.selectedEmployees.forEach {
                                viewModel.deleteEmployee(it)
                            }
                            recreate()
                            adapter.notifyDataSetChanged()
                            Snackbar.make(view, ScreenConstants.EMPLOYEE_DELETED, Snackbar.LENGTH_SHORT)
                                .show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Snackbar.make(view, ScreenConstants.EMPLOYEE_DELETED_ERROR, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottom_app_bar_menu, menu)

        return true
    }

}