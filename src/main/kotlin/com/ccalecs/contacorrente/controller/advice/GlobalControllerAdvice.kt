package com.ccalecs.contacorrente.controller.advice

import com.ccalecs.contacorrente.Utils.getStringFromProperties
import com.ccalecs.contacorrente.exception.*
import com.ccalecs.contacorrente.model.ApiErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [ClientNotFoundException::class])
    protected fun handleClientNotFoundException(
        ex: ClientNotFoundException,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val stringProperty = messageSource?.getStringFromProperties(
            "client_was_not_found",
            request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)
        )

        val status = HttpStatus.NOT_FOUND.value()
        val instance = request.getDescription(false).replace("uri=", "")

        return ResponseEntity(
            ApiErrorResponse(
                title = stringProperty,
                status = status,
                detail = ex.message,
                instance = instance
            ),
            HttpHeaders(),
            status
        )
    }

    @ExceptionHandler(value = [TransferenceValueException::class])
    protected fun handleTransferValueException(
        ex: TransferenceValueException,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val stringProperty = messageSource?.getStringFromProperties(
            "invalid_transference_value",
            request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)
        )

        val status = HttpStatus.BAD_REQUEST.value()
        val instance = request.getDescription(false).replace("uri=", "")

        return ResponseEntity(
            ApiErrorResponse(
                title = stringProperty,
                status = status,
                detail = ex.message,
                instance = instance
            ),
            HttpHeaders(),
            status
        )
    }

    @ExceptionHandler(value = [TransferenceClientException::class])
    protected fun handleTransferenceClientException(
        ex: TransferenceClientException,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val stringProperty = messageSource?.getStringFromProperties(
            "invalid_transference_client",
            request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)
        )

        val status = HttpStatus.BAD_REQUEST.value()
        val instance = request.getDescription(false).replace("uri=", "")

        return ResponseEntity(
            ApiErrorResponse(
                title = stringProperty,
                status = status,
                detail = ex.message,
                instance = instance
            ),
            HttpHeaders(),
            status
        )
    }
}



