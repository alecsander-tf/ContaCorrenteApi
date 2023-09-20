package com.ccalecs.contacorrente.response

import java.time.LocalDate

data class TransferenceResponse(
    val transferenceId: Long?,
    val clientIdSender: Long?,
    val clientIdReceiver: Long?,
    val value: Double,
    val data: LocalDate
)