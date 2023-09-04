package com.example.myapplication.custom

data class Person(val name: String, val age: Int, val address: String)

class PersonBuilder {
    var name: String = ""
    var age: Int = 0
    var address: String = ""
    fun build(): Person {
        return Person(name = name, age = age, address = address)
    }
}
val person = PersonBuilder()
    .apply { name = "John" }
    .apply { age = 30 }
    .apply { address = "123 Main St." }
    .build()