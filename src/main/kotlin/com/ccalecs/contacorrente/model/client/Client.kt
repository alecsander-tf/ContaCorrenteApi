package com.ccalecs.contacorrente.model.client

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table
data class Client(val name: String, val balance: String, val email: String, val dob: LocalDate) {

    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private var id: Long? = null

}