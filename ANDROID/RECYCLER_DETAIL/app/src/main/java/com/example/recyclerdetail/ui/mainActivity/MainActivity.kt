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
import com.example.recyclerdetail.ui.MainActivityViewModel
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

            //TODO: change this (can't use repository directly) !!!!!!
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

            //TODO: find a way to refresh list after adding a new employee
            fab.setOnClickListener {
                try {
                    //Add new employee
                    val defaultEmployee = Employee("New Employee", "0", "0", 0, false, 0)
                    viewModel.addEmployee(defaultEmployee)
                    Snackbar.make(view, "Go EditScreen", Snackbar.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Snackbar.make(view, "Error going EditScreen", Snackbar.LENGTH_SHORT).show()
                }
            }

            //TODO: fix these methods uwu
            bottomAppBar.setOnMenuItemClickListener() { item: MenuItem ->
                when (item.itemId) {
                    R.id.appBarDelete -> {
                        try {
                            adapter.selectedEmployees?.forEach {
                                viewModel.deleteEmployee(it)
                            }
                            adapter.notifyDataSetChanged();
                            Snackbar.make(view, "Employee Deleted", Snackbar.LENGTH_SHORT)
                                .show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Snackbar.make(view, "Error deleting employee", Snackbar.LENGTH_SHORT)
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