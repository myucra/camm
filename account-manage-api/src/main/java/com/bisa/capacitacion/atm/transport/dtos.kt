package com.bisa.capacitacion.atm.transport

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class CreateAccountIn (
    @Schema(description = "Account type, D=Debit or C=Credit")
    val accountType: String?,
    @Schema(description = "Customer id to create account")
    val idCustomer: String?,
    @Schema(description = "currency id to create an account")
    val idCurrency: String?
)

data class CreateAccountOut(
    @Schema(description = "Account number create")
    val accountNumber: String
)

data class GetBalanceIn(
    @Schema(description = "Account number for get balance")
    val accountNumber: String
)

data class GetBalanceOut(
    @Schema(description = "Balance of account")
    val balance: BigDecimal,
    @Schema(description = "Currency abbreviation")
    val currency: String,
    @Schema(description = "account status")
    val state: String
)