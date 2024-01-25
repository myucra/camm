package com.bisa.capacitacion.atm.transport.controller

import com.bisa.capacitacion.atm.transport.AcknowledgementDto
import com.bisa.capacitacion.atm.transport.CreateAccountIn
import com.bisa.capacitacion.atm.transport.CreateAccountOut
import com.bisa.capacitacion.atm.transport.GetBalanceOut
import com.bisa.commons.spring.Error
import com.bisa.commons.spring.rest.RestControllerStub
import com.bisa.commons.spring.rest.validators.request.PayloadCheck
import com.bisa.commons.spring.rest.validators.request.RequestValidation
import com.bisa.commons.spring.rest.validators.request.json.JsonSchema
import com.bisa.commons.spring.rest.validators.request.params.PathVariableCheck
import com.bisa.commons.tracing.Traceable
import com.bisa.commons.tracing.interceptor.TraceSpan
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/account")
@OpenAPIDefinition(
    info = Info(
        title = "Account Manager",
        description = "Bank Account Manager",
        version = "1.0.0",
        contact = Contact(name = "Banco", url = "")
    )
)
@Traceable
open interface AccountAPI : RestControllerStub {
    @PostMapping("/V1/create")
    @TraceSpan
    @Operation(
        tags = ["Account"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Account created successfully",
            content = [Content(schema = Schema(implementation = AcknowledgementDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid data",
            content = [Content(schema = Schema(implementation = Error::class))]
        ), ApiResponse(
            responseCode = "422",
            description = "The account could not be created",
            content = [Content(schema = Schema(implementation = Error::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal error in the service",
            content = [Content(schema = Schema(implementation = Error::class))]
        )]
    )
    //Validation for jsonschema
    @RequestValidation(
        payload = PayloadCheck(
            jsonSchema = JsonSchema("classpath:/schemas/json/createAccount.json")
        )
    )
    fun createAccount(@RequestBody createAccountIn: CreateAccountIn): CreateAccountOut?

    @GetMapping("/V1/{accountNumber}/balance")
    @TraceSpan
    @Operation(
        tags = ["Account"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Account created successfully",
            content = [Content(schema = Schema(implementation = AcknowledgementDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid data",
            content = [Content(schema = Schema(implementation = Error::class))]
        ), ApiResponse(
            responseCode = "422",
            description = "The account could not be created",
            content = [Content(schema = Schema(implementation = Error::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal error in the service",
            content = [Content(schema = Schema(implementation = Error::class))]
        )]
    )

    @RequestValidation(pathVariables = [PathVariableCheck(
        name = "accountNumber",
        regularExpression = "^\\d{3}\\d{4}01[1-9]\\d{3}\$")]
    )
    fun getAccount(@PathVariable accountNumber: String): GetBalanceOut?
}