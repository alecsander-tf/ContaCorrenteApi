package com.ccalecs.contacorrente.model.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/client"])
class ClientController @Autowired constructor(val clientService: ClientService) {

    @GetMapping
    fun getAllClients(): List<Client> {
        return clientService.getClient()
    }

    @GetMapping(path = ["get-user"], params = ["id"])
    fun getClientById(@RequestParam id: Long): Client {
        return clientService.getClient(id)
    }

    @GetMapping(path = ["get-user"], params = ["email"])
    fun getClientByEmail(@RequestParam email: String): Client {
         return clientService.getClient(email)
    }

    @PostMapping(path = ["check-login"])
    fun login(@RequestPart email: String, @RequestPart password: String) {
        clientService.login(email, password)
    }

}