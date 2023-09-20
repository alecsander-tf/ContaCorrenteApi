package com.ccalecs.contacorrente.service

import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.repository.ClientRepository
import com.ccalecs.contacorrente.response.StatusResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.lang.IllegalStateException
import kotlin.jvm.optionals.getOrElse

@Service
class ClientService @Autowired constructor(val clientRepository: ClientRepository) {

    @GetMapping
    fun getClient(): List<Client> = clientRepository.findAll()

    @GetMapping
    fun getClientByEmail(email: String): Client = clientRepository.findClientByEmail(email).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND, "Client does not exist")
    }

    @GetMapping
    fun getClientById(id: Long): Client = clientRepository.findClientByClientId(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND, "Client does not exist")
    }

    fun login(email: String, password: String): StatusResponse {

        val client = clientRepository.findClientByEmail(email).orElseThrow {
            throw IllegalStateException("Invalid Email or Password")
        }

        if (client.password != password) throw IllegalStateException("Invalid Email or Password")

        return StatusResponse(true, "")
    }

}