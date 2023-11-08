package com.ccalecs.contacorrente.service

import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.model.Transference
import com.ccalecs.contacorrente.repository.ClientRepository
import com.ccalecs.contacorrente.repository.TransferenceRepository
import com.ccalecs.contacorrente.response.StatusResponse
import com.ccalecs.contacorrente.response.TransferenceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Service
class TransferenceService @Autowired constructor(
    private val transferenceRepository: TransferenceRepository,
    private val clientRepository: ClientRepository
) {

    fun findTransferenceListByClientId(clientId: Long): List<TransferenceResponse> {
        return transferenceRepository.findTransferenceListByClientId(clientId).orElseGet {
            emptyList()
        }.map {
            TransferenceResponse(
                transferenceId = it.transferenceId,
                clientIdSender = it.clientSender.clientId,
                clientIdReceiver = it.clientReceiver.clientId,
                value = it.value,
                data = it.data
            )
        }
    }

    fun transfer(clientIdSender: Long, clientEmail: String, stringValue: String): StatusResponse {

        val optionalClientSender = clientRepository.findClientByClientId(clientIdSender)
        val optionalClientReceiver = clientRepository.findClientByEmail(clientEmail)

        val value = BigDecimal(stringValue)

        validateTransfer(optionalClientSender, optionalClientReceiver, value)

        val clientSender = optionalClientSender.get().apply {
            balance -= value
            clientRepository.save(this)
        }

        val clientReceiver = optionalClientReceiver.get().apply {
            balance += value
            clientRepository.save(this)
        }

        transferenceRepository.save(
            Transference(
                clientSender, clientReceiver, value, LocalDate.now()
            )
        )

        return StatusResponse(true, "")
    }

    fun transfer(clientIdSender: Long, clientIdReceiver: Long, stringValue: String): StatusResponse {

        val optionalClientSender = clientRepository.findClientByClientId(clientIdSender)
        val optionalClientReceiver = clientRepository.findClientByClientId(clientIdReceiver)

        val value = BigDecimal(stringValue)

        validateTransfer(optionalClientSender, optionalClientReceiver, value)

        val clientSender = optionalClientSender.get().apply {
            balance -= value
            clientRepository.save(this)
        }

        val clientReceiver = optionalClientReceiver.get().apply {
            balance += value
            clientRepository.save(this)
        }

        transferenceRepository.save(
            Transference(
                clientSender, clientReceiver, value, LocalDate.now()
            )
        )

        return StatusResponse(true, "")
    }

    private fun validateTransfer(
        optionalClientSender: Optional<Client>,
        optionalClientReceiver: Optional<Client>,
        value: BigDecimal
    ) {

        if (value <= BigDecimal(0)) throw IllegalArgumentException("Value must not be zero or negative")

        val clientReceiver = optionalClientReceiver.orElseThrow {
            throw IllegalStateException("Client receiver email does not exist")
        }

        val clientSender = optionalClientSender.orElseThrow {
            throw IllegalStateException("Client sender id does not exist")
        }

        if (clientSender.clientId == clientReceiver.clientId) throw IllegalStateException("Client receiver cannot be the same as client sender")
        if (value > clientSender.balance) throw IllegalStateException("Client does not have balance to make transfer")


    }

}