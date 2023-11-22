package com.ccalecs.contacorrente.service

import com.ccalecs.contacorrente.exception.ClientNotFoundException
import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.repository.ClientRepository
import com.ccalecs.contacorrente.response.StatusResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class ClientService @Autowired constructor(val clientRepository: ClientRepository) {

    @GetMapping
    fun getClient(): List<Client> = clientRepository.findAll()

    @GetMapping
    fun getClientByEmail(email: String): Client = clientRepository.findClientByEmail(email).orElseThrow {
        ClientNotFoundException(message = "Client with email $email was not found")
    }

    @GetMapping
    fun getClientById(id: Long): Client = clientRepository.findClientByClientId(id).orElseThrow {
        ClientNotFoundException(message = "Client with id $id was not found")
    }

    fun login(email: String, password: String): StatusResponse {

        val clientNotFoundException = ClientNotFoundException(message = "Invalid Email or password")

        val client = clientRepository.findClientByEmail(email).orElseThrow {
            clientNotFoundException
        }

        if (client.password != password) throw clientNotFoundException

        return StatusResponse(true, "")
    }

}