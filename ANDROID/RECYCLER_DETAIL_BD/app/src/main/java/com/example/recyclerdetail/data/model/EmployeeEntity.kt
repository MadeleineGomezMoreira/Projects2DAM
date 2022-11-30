package com.example.recyclerdetail.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Employees")
data class EmployeeEntity(

    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "birthday")
    val birthday: LocalDate,
    @ColumnInfo(name = "active")
    val active: Boolean,
    @ColumnInfo(name = "phone")
    val phone: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)