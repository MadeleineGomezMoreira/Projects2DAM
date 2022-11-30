package com.example.recyclerdetail.data

import androidx.room.*
import com.example.recyclerdetail.data.model.EmployeeEntity
import com.example.recyclerdetail.data.model.EmployeeWithThings
import com.example.recyclerdetail.data.model.ThingEntity

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM Employees ORDER BY name ASC")
    suspend fun getEmployeesAsc(): List<EmployeeEntity>

    @Query("SELECT * FROM Employees ORDER BY name DESC")
    suspend fun getEmployeesDes(): List<EmployeeEntity>

    @Query("SELECT * FROM things")
    suspend fun getThings(): List<ThingEntity>

    @Transaction
    @Query("SELECT * FROM Employees WHERE id = :id")
    suspend fun getEmployeeWithThings(id: Int): EmployeeWithThings

    @Transaction
    @Query("SELECT * FROM Employees")
    suspend fun getEmployeeWithThings(): List<EmployeeWithThings>

    @Query("SELECT * FROM Employees WHERE id = :id")
    suspend fun getEmployee(id: Int): EmployeeEntity

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: EmployeeEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ThingEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: List<ThingEntity>)

    @Transaction
    suspend fun createTransaction(employeeThings: EmployeeWithThings) {
        employeeThings.employee.id = insert(employeeThings.employee).toInt()
        employeeThings.things?.apply{
            forEach { it.employeeId = employeeThings.employee.id  }
            insert(this)
        }
    }

    @Update
    fun update(item: EmployeeEntity)

    @Delete
    fun delete(item: EmployeeEntity)
}