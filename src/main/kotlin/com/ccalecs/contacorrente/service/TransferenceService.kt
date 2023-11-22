package com.ccalecs.contacorrente.service

import com.ccalecs.contacorrente.exception.ClientNotFoundException
import com.ccalecs.contacorrente.exception.TransferenceClientException
import com.ccalecs.contacorrente.exception.TransferenceValueException
import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.model.Transference
import com.ccalecs.contacorrente.repository.ClientRepository
import com.ccalecs.contacorrente.repository.TransferenceRepository
import com.ccalecs.contacorrente.response.StatusResponse
import com.ccalecs.contacorrente.response.TransferenceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

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

    fun transfer(clientIdSender: Long, clientReceiverEmail: String, stringValue: String): StatusResponse {

        val optionalClientSender = clientRepository.findClientByClientId(clientIdSender)
        val optionalClientReceiver = clientRepository.findClientByEmail(clientReceiverEmail)

        val clientSender = optionalClientSender.orElseThrow {
            ClientNotFoundException(message = "Client sender id $clientIdSender does not exist")
        }

        val clientReceiver = optionalClientReceiver.orElseThrow {
            ClientNotFoundException(message = "Client receiver email $clientReceiverEmail does not exist")
        }

        val value = BigDecimal(stringValue)

        validateTransfer(clientSender, clientReceiver, value)

        clientSender.apply {
            balance -= value
            clientRepository.save(this)
        }

        clientReceiver.apply {
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

        val clientSender = optionalClientSender.orElseThrow {
            ClientNotFoundException(message = "Client sender id $clientIdSender does not exist")
        }

        val clientReceiver = optionalClientReceiver.orElseThrow {
            ClientNotFoundException(message = "Client receiver id $clientIdReceiver does not exist")
        }

        val value = BigDecimal(stringValue)

        validateTransfer(clientSender, clientReceiver, value)

        clientSender.apply {
            balance -= value
            clientRepository.save(this)
        }

        clientReceiver.apply {
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
        clientSender: Client,
        clientReceiver: Client,
        value: BigDecimal
    ) {
        if (value <= BigDecimal(0)) throw TransferenceValueException("Value must not be zero or negative")
        if (clientSender.clientId == clientReceiver.clientId) throw TransferenceClientException("Client receiver cannot be the same as client sender")
        if (value > clientSender.balance) throw TransferenceClientException("Client does not have balance to make transfer")
    }

}