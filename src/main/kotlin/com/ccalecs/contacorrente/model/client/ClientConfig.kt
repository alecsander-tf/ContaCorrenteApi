package com.ccalecs.contacorrente.model.client

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class ClientConfig {

    @Bean
    fun commandLineRunner(clientRepository: ClientRepository): CommandLineRunner {

        return CommandLineRunner {

            val alecs = Client("Alecs", "1000", "alecs@gmail.com", LocalDate.now())
            val barbara = Client("Bárbara", "1000", "barbara@gmail.com", LocalDate.now())

            clientRepository.saveAll(listOf(alecs, barbara))

        }
    }
}