package com.ccalecs.contacorrente

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.annotation.Bean
import org.springframework.web.context.request.WebRequest

@SpringBootApplication
class ContacorrenteApplication

fun main(args: Array<String>) {
    runApplication<ContacorrenteApplication>(*args)
}
