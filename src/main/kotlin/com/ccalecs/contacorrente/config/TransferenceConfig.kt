package com.ccalecs.contacorrente.config

import com.ccalecs.contacorrente.configuration.ModelsToBeCreated
import com.ccalecs.contacorrente.repository.TransferenceRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransferenceConfig {

    @Bean
    fun transferenceCommandLineRunner(transferenceRepository: TransferenceRepository): CommandLineRunner {

        return CommandLineRunner {

            transferenceRepository.saveAll(
                listOf(
                    ModelsToBeCreated.ObjectTransference.FIRST_TRANSFERENCE,
                    ModelsToBeCreated.ObjectTransference.SECOND_TRANSFERENCE,
                    ModelsToBeCreated.ObjectTransference.THIRD_TRANSFERENCE
                )
            )

        }
    }
}