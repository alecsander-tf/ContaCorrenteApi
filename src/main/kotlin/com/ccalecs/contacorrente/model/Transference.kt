package com.ccalecs.contacorrente.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table
data class Transference(
    @ManyToOne
    @JoinColumn(name = "clientIdSender", referencedColumnName = "clientId")
    val clientSender: Client,

    @ManyToOne
    @JoinColumn(name = "clientIdReceiver", referencedColumnName = "clientId")
    val clientReceiver: Client,

    val value: BigDecimal, val data: LocalDate
) {

    @Id
    @SequenceGenerator(
        name = "transference_sequence",
        sequenceName = "transference_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transference_sequence"
    )
    var transferenceId: Long? = null

}