package com.ccalecs.contacorrente.response

import java.math.BigDecimal
import java.time.LocalDate

data class TransferenceResponse(
    val transferenceId: Long?,
    val clientIdSender: Long?,
    val clientIdReceiver: Long?,
    val value: BigDecimal,
    val data: LocalDate
)