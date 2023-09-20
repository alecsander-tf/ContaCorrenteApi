package com.ccalecs.contacorrente.config

import com.ccalecs.contacorrente.configuration.ModelsToBeCreated
import com.ccalecs.contacorrente.repository.ClientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ClientConfig {

    @Bean
    fun clientCommandLineRunner(clientRepository: ClientRepository): CommandLineRunner {

        return CommandLineRunner {

            clientRepository.saveAll(
                listOf(
                    ModelsToBeCreated.ObjectClient.ALECS_CLIENT,
                    ModelsToBeCreated.ObjectClient.BARBARA_CLIENT
                )
            )

        }
    }
}