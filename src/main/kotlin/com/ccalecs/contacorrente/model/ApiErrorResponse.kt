package com.ccalecs.contacorrente.model

import java.sql.Timestamp
import java.time.Instant

/**
 * Classe para response de erros
 *
 * @param type a URI identifier that categorizes the error
 * @param title a brief, human-readable message about the error
 * @param status the HTTP response code (optional)
 * @param detail a human-readable explanation of the error
 * @param instance a URI that identifies the specific occurrence of the error
 * @param timestamp timestamp of request
 *
 * Note that the type field categorizes the type of error, while instance identifies a specific
 * occurrence of the error in a similar fashion to classes and objects, respectively.
 *
 * @see <a href="https://www.baeldung.com/rest-api-error-handling-best-practices#4-standardized-response-bodies">
 * Standardized Response Bodies</a>
 */
data class ApiErrorResponse(
    val type: String? = "",
    val title: String? = "",
    val status: Int,
    val detail: String,
    val instance: String,
    val timestamp: Timestamp = Timestamp.from(Instant.now()),
)