package com.ccalecs.contacorrente.model

import jakarta.persistence.*

@Entity
@Table
data class Client(
    val name: String,
    var balance: Double,
    val email: String,
    val password: String
) {
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
    var clientId: Long? = null

}