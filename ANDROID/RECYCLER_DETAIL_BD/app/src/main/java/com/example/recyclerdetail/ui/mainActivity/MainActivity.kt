package com.example.recyclerdetail.ui.mainActivity

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdetail.R
import com.example.recyclerdetail.data.Converters
import com.example.recyclerdetail.data.EmployeeRepository
import com.example.recyclerdetail.data.EmployeeRoomDatabase
import com.example.recyclerdetail.data.Repository
import com.example.recyclerdetail.databinding.ActivityMainBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.usecases.employees.*
import com.example.recyclerdetail.ui.AdapterEmployees
import com.example.recyclerdetail.ui.ScreenConstants
import com.example.recyclerdetail.utils.StringProvider
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


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

                    var date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        LocalDate.now()
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = Converters().fromTimestamp("01-01-2020")
                    }

                    val defaultEmployee = Employee(
                        ScreenConstants.NEW_EMPLOYEE, 1, "F", date, false, 0,
                        listOf()
                    )

                    //TODO: is this OK??
                    viewModel.handleEvent(MainEvent.InsertEmployee(defaultEmployee))
                    recreate()
                    adapter.notifyDataSetChanged()
                    Snackbar.make(view, ScreenConstants.EMPLOYEE_ADDED, Snackbar.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Snackbar.make(view, ScreenConstants.EMPLOYEE_ADDED_ERROR, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            bottomAppBar.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.appBarDelete -> {
                        try {
                            adapter.selectedEmployees.forEach {
                                viewModel.handleEvent(MainEvent.DeleteEmployee(it))
                            }
                            recreate()
                            adapter.notifyDataSetChanged()
                            Snackbar.make(
                                view,
                                ScreenConstants.EMPLOYEE_DELETED,
                                Snackbar.LENGTH_SHORT
                            )
                                .show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Snackbar.make(
                                view,
                                ScreenConstants.EMPLOYEE_DELETED_ERROR,
                                Snackbar.LENGTH_SHORT
                            )
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