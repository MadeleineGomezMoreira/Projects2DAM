package com.example.recyclerdetail.ui

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdetail.R
import com.example.recyclerdetail.databinding.EmployeeCardBinding
import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.ui.editEmployeesActivity.EditEmployeesActivity
import timber.log.Timber

class AdapterEmployees(
    private var employees: List<Employee>,
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    var selectedEmployees: MutableList<Employee> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

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

    private val binding = EmployeeCardBinding.bind(view)

    fun render(employee: Employee, selectedEmployees: MutableList<Employee>) {

        with(binding) {
            tvEmployeeName.text = employee.name


            cardView.setOnLongClickListener {


                if (selectedEmployees.contains(employee)) {
                    selectedEmployees.remove(employee)
                    cardView.setCardBackgroundColor(Color.parseColor("#018786"))
                    employeeImage.setImageDrawable(
                        AppCompatResources.getDrawable(
                            view.context,
                            R.drawable.account_circle
                        )
                    )
                } else {
                    selectedEmployees.add(employee)
                    cardView.setCardBackgroundColor(Color.parseColor("#D5FFF2"))
                    employeeImage.setImageDrawable(
                        AppCompatResources.getDrawable(
                            view.context,
                            R.drawable.check_bold
                        )
                    )
                    Timber.tag("selectedEmployees").d("List: $selectedEmployees")
                }

                return@setOnLongClickListener true
            }

            cardView.setOnClickListener {

                val intent = Intent(view.context, EditEmployeesActivity::class.java)
                intent.putExtra(ScreenConstants.ID_STRING, employee.id)
                startActivity(view.context, intent, null)

            }
        }
    }
}
