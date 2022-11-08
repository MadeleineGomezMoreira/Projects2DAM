package com.example.recyclerdetail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdetail.R
import com.example.recyclerdetail.databinding.EmployeeCardBinding
import com.example.recyclerdetail.domain.model.Employee

class AdapterEmployees(
    private var employees: List<Employee>,
    private var onSelect: (Employee) -> Unit,
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(layoutInflater.inflate(R.layout.employee_card, parent, false))
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.render(employees[position])
    }

    override fun getItemCount(): Int {
        return employees.size
    }

}


class EmployeeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    //como un mini claseActivity (donde tendríamos los botones / onCreate etc)

    val binding = EmployeeCardBinding.bind(view)

    fun render(employee: Employee) {

        with(binding) {
            tvEmployeeName.text = employee.name

            //TODO: Pongo aquí el método de onSelectItem????


        }



    }

}
