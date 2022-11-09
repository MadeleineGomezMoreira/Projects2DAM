package com.example.recyclerdetail.ui

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdetail.R
import com.example.recyclerdetail.databinding.EmployeeCardBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.ui.editEmployeesActivity.EditEmployeesActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.coroutineContext

class AdapterEmployees(
    private var employees: List<Employee>,
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    var selectedEmployees: List<Employee>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        Log.d("Adapter", "onCreateViewHolder")

        return EmployeeViewHolder(layoutInflater.inflate(R.layout.employee_card, parent, false))

    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.render(employees[position], selectedEmployees)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

}


class EmployeeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    //como un mini claseActivity (donde tendríamos los botones / onCreate etc)

    val binding = EmployeeCardBinding.bind(view)

    fun render(employee: Employee, selectedEmployees: List<Employee>?) {

        with(binding) {
            tvEmployeeName.text = employee.name


            cardView.setOnLongClickListener() {

                //Snackbar.make(it, "Click en ${employee.name}", Snackbar.LENGTH_SHORT).show()

                //EL IF NO FUNCIONA NO ENTRA EN EL PRIMERO AAAAAAAAAAAA
                if (cardView.cardBackgroundColor.equals(Color.parseColor("#018786"))) {
                    selectedEmployees?.minus(employee)
                    cardView.setCardBackgroundColor(Color.parseColor("#018786"))
                } else {
                    selectedEmployees?.plus(employee)
                    Snackbar.make(
                        it,
                        "Click on ${employee.name}, not in list",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    cardView.setCardBackgroundColor(Color.parseColor("#D5FFF2"))
                }

                return@setOnLongClickListener true
            }

            cardView.setOnClickListener() {
                Snackbar.make(it, "Click en ${employee.name}", Snackbar.LENGTH_SHORT).show()

                val intent = Intent(view.context, EditEmployeesActivity::class.java)
                intent.putExtra("id", employee.id)
                startActivity(view.context, intent, null)

            }

        }
    }

}
