package com.ccalecs.contacorrente.repository

import com.ccalecs.contacorrente.model.Transference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.List

@Repository
interface TransferenceRepository : JpaRepository<Transference, Long> {

    @Query("SELECT t FROM Transference t WHERE t.clientSender.clientId = ?1 OR t.clientReceiver.clientId = ?1")
    fun findTransferenceListByClientId(id: Long): Optional<List<Transference>>

}