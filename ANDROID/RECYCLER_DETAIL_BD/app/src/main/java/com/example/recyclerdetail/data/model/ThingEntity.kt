package com.example.recyclerdetail.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Things",
    foreignKeys = [
        ForeignKey(
            entity = EmployeeEntity::class,
            parentColumns = ["id"],
            childColumns = ["employeeId"],
        )
    ]
)

class ThingEntity(
    @ColumnInfo(name = "name")
    val name: String,
    var employeeId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)