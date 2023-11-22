package com.ccalecs.contacorrente.exception

data class ClientNotFoundException(
    override val message: String
) : IllegalStateException()

data class TransferenceValueException(
    override val message: String
) : IllegalStateException()

data class TransferenceClientException(
    override val message: String
) : IllegalStateException()