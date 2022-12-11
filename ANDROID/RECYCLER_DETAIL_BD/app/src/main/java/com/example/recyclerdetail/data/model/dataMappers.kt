package com.example.recyclerdetail.data.model

import com.example.recyclerdetail.domain.model.Employee
import com.example.recyclerdetail.domain.model.Thing

fun EmployeeEntity.toEmployee(): Employee {
    return Employee(
        this.name,
        this.id,
        this.gender,
        this.birthday,
        this.active,
        this.phone,
        null
    )
}


fun EmployeeWithThings.toEmployee(): Employee {
    return Employee(
        this.employee.name,
        this.employee.id,
        this.employee.gender,
        this.employee.birthday,
        this.employee.active,
        this.employee.phone,
        this.things?.map { it.toThing() })
}

fun ThingEntity.toThing(): Thing {
    return Thing(this.name, this.id)
}

fun Employee.toEmployeeEntity(): EmployeeEntity = EmployeeEntity(
    this.name, this.gender, this.birthday, this.active, this.phoneNumber, this.id
)


fun Employee.toEmployeeWithThings(): EmployeeWithThings =
    EmployeeWithThings(this.toEmployeeEntity(), this.things?.map { it.toThingEntity(this.id) })

fun Thing.toThingEntity(employeeId: Int = 0): ThingEntity = ThingEntity(name, employeeId, id)
