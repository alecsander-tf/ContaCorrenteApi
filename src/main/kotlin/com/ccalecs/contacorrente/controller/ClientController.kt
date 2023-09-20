package com.ccalecs.contacorrente.controller

import com.ccalecs.contacorrente.model.Client
import com.ccalecs.contacorrente.response.StatusResponse
import com.ccalecs.contacorrente.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/client"])
class ClientController @Autowired constructor(val clientService: ClientService) {

    @GetMapping
    fun getAllClients(): List<Client> {
        return clientService.getClient()
    }

    @GetMapping(path = ["get-client"], params = ["id"])
    fun getClientById(@RequestParam id: Long): Client {
        return clientService.getClientById(id)
    }

    @GetMapping(path = ["get-client"], params = ["email"])
    fun getClientByEmail(@RequestParam email: String): Client {
        return clientService.getClientByEmail(email)
    }

    @PostMapping(path = ["check-login"])
    fun login(@RequestPart email: String, @RequestPart password: String): StatusResponse {
        return clientService.login(email, password)
    }

}