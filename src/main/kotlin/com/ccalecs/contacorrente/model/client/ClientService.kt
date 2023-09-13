package com.ccalecs.contacorrente.model.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import java.lang.IllegalStateException

@Service
class ClientService @Autowired constructor(val clientRepository: ClientRepository) {

    @GetMapping
    fun getClient(): List<Client> = clientRepository.findAll()

    @GetMapping
    fun getClient(email: String): Client {

        clientRepository.findClientByEmail(email).let {
            if (it.isPresent) return it.get()
        }

        throw IllegalStateException("Client does not exist")
    }

    @GetMapping
    fun getClient(id: Long): Client {

        clientRepository.findClientById(id).let {
            if (it.isPresent) return it.get()
        }

        throw IllegalStateException("Client does not exist")
    }

    fun login(email: String, password: String) {

        clientRepository.findClientByEmail(email).let {
            if (!it.isPresent) throw IllegalStateException("Client does not exist")
        }
    }

}