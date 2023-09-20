package com.ccalecs.contacorrente.configuration

import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyCustomErrorController(errorAttributes: ErrorAttributes?) : AbstractErrorController(errorAttributes) {

    @RequestMapping(value = [ERROR_PATH], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handleError(request: HttpServletRequest?): Map<String, Any> {
        return super.getErrorAttributes(
            request, ErrorAttributeOptions.of(
                listOf(
                    ErrorAttributeOptions.Include.MESSAGE
                )
            )
        )
    }

    companion object {
        const val ERROR_PATH: String = "/error"
    }
}
