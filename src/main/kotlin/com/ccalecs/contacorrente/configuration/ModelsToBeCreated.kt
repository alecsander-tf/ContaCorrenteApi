package com.ccalecs.contacorrente.configuration

import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.model.Transference
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month

object ModelsToBeCreated {

    object ObjectClient {
        val ALECS_CLIENT = Client(
            name = "Alecs",
            balance = BigDecimal("2000.00"),
            email = "alecs@gmail.com",
            password = "123"
        )
        val BARBARA_CLIENT = Client(
            name = "Bárbara",
            balance = BigDecimal("5000.00"),
            email = "barbara@gmail.com",
            password = "12345"
        )
    }

    object ObjectTransference {
        val FIRST_TRANSFERENCE = Transference(
            clientSender = ObjectClient.ALECS_CLIENT,
            clientReceiver = ObjectClient.BARBARA_CLIENT,
            value = BigDecimal("10"),
            data = LocalDate.of(2023, Month.AUGUST, 9)
        )
        val SECOND_TRANSFERENCE = Transference(
            clientSender = ObjectClient.BARBARA_CLIENT,
            clientReceiver = ObjectClient.ALECS_CLIENT,
            value = BigDecimal("200"),
            data = LocalDate.of(2023, Month.APRIL, 20)
        )
        val THIRD_TRANSFERENCE = Transference(
            clientSender = ObjectClient.BARBARA_CLIENT,
            clientReceiver = ObjectClient.ALECS_CLIENT,
            value = BigDecimal("50"),
            data = LocalDate.of(2023, Month.DECEMBER, 20)
        )
    }
}