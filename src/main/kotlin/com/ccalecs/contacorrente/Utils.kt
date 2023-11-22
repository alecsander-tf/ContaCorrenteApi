package com.ccalecs.contacorrente

import org.springframework.context.MessageSource
import java.util.Locale

object Utils {
    fun MessageSource.getStringFromProperties(variable: String, localeString: String?): String = getMessage(
        variable, null, Locale.of(if (localeString.isNullOrEmpty()) "en_US" else localeString)
    )
}