package com.ccalecs.contacorrente.repository

import com.ccalecs.contacorrente.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

    fun findClientByEmail(email: String): Optional<Client>
    fun findClientByClientId(id: Long): Optional<Client>

}