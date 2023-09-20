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

    fun transfer(clientIdSender: Long, clientIdReceiver: Long, value: Double): StatusResponse {

        val optionalClientSender = clientRepository.findClientByClientId(clientIdSender)
        val optionalClientReceiver = clientRepository.findClientByClientId(clientIdReceiver)

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
        value: Double
    ) {

        if (value <= 0) throw IllegalArgumentException("Value must not be zero or negative")

        optionalClientSender.orElseThrow {
            throw IllegalStateException("Client sender id does not exist")
        }.run {
            if (value > balance) throw IllegalStateException("Client does not have balance to make transfer")
        }

        optionalClientReceiver.orElseThrow {
            throw IllegalStateException("Client receiver id does not exist")
        }
    }

}