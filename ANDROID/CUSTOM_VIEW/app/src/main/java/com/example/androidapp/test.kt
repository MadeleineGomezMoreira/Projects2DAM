package com.example.androidapp

class Person(val name: String, val age: Int)

//si dentro de este constructor queremos hacer algo, nos bastará con construir un bloque de init

fun main() {
    val p = Person("Jhon", 20)
    print(p.name)
}
