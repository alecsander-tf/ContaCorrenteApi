package com.ccalecs.contacorrente.model.client

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

    fun findClientByEmail(email: String): Optional<Client>
    fun findClientById(id: Long): Optional<Client>

}