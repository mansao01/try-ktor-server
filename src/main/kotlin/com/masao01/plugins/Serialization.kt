package com.masao01.plugins

import com.masao01.models.Employee
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    val employees = mutableListOf<Employee>()
    routing {
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }

        post("/employee") {
            val requestBody = call.receive<Employee>()
            employees.add(requestBody)
            call.respond(requestBody)
        }

        get("/employees"){
            call.respond(employees)
        }

    }
}
