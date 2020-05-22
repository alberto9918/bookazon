package com.santiagorodriguezalberto.bookazon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookazonApplication

fun main(args: Array<String>) {
	runApplication<BookazonApplication>(*args)
}
