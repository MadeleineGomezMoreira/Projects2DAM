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
import com.example.recyclerdetail.ui.MainViewModel
import com.example.recyclerdetail.utils.StringProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            GetEmployeeByIndexUseCase(),
            GetEmployeesUseCase(),
            AddEmployeeUseCase(),
            DeleteEmployeeUseCase(),
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
            val adapter = AdapterEmployees(list, onSelect = { employee ->
                Snackbar.make(view, "Selected ${employee.name}", Snackbar.LENGTH_SHORT).show()
            })

            list.let {
                recyclerEmployees.adapter = adapter
                recyclerEmployees.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            //TODO: se ve cada card como la pantalla entera lmao + no shading ???

            //TODO: find a way to refresh list after adding a new employee
            fab.setOnClickListener {
                try {
                    //EditEmployee
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
                            /*
                            viewModel.deleteEmployee(list.last())
                             */
                            Snackbar.make(view, "Last Employee Deleted", Snackbar.LENGTH_SHORT)
                                .show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Snackbar.make(view, "Error deleting employee", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        true
                    }
                    R.id.appBarAdd -> {
                        try {
                            val defaultEmployee =
                                Employee("New Employee", "666", "F", 6666, true, 666666666)
                            viewModel.addEmployee(defaultEmployee)
                            Snackbar.make(view, "New Employee Added", Snackbar.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Snackbar.make(view, "Error adding employee", Snackbar.LENGTH_SHORT)
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