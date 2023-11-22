package com.ccalecs.contacorrente.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*


@Configuration
class LanguageConfig : WebMvcConfigurer {

    @Bean
    fun localeResolver(): LocaleResolver {

        return AcceptHeaderLocaleResolver().apply {
            supportedLocales = listOf(
                Locale.of("pt_BR"),
                Locale.of("en_US")
            )
            setDefaultLocale(Locale.of("en_US"))
        }
    }

    @Bean
    fun messageSource(): MessageSource {
        return ResourceBundleMessageSource().apply {
            setDefaultEncoding("UTF-8")
            setBasename("messages")
            setDefaultLocale(Locale.ENGLISH)
        }
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "lang"
        return localeChangeInterceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

}

@Component
class Interceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("Requisição interceptada: " + request.getHeader("Authorization"))
        return true
    }
}