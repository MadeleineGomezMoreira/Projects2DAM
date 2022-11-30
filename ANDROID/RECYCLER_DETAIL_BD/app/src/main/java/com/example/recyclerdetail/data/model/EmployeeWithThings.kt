package com.example.recyclerdetail.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithThings(
    @Embedded val employee: EmployeeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "personaId"
    )
    val things: List<ThingEntity>?

)