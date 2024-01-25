package com.bisa.capacitacion.atm.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Account(
    val id: String,
    val idcustomer: String,
    val idcurrency: String,
    val accountnumber: String,
    val accounttype: String,
    val balance: BigDecimal,
    val state: String?,
    val createdate: LocalDateTime = LocalDateTime.now(),
    val auddate: LocalDateTime = LocalDateTime.now(),
    val auduser: String,
    val audip: String,
    val audtypeaction: String,
    val audmodule: String
)

 data class AccountHistory(
    val id: String,
    val idcustomer: String,
    val idcurrency: String,
    val accountnumber: String,
    val accounttype: String,
    val balance: BigDecimal,
    val state: String?,
    val createdate: LocalDateTime = LocalDateTime.now(),
    val auddate: LocalDateTime = LocalDateTime.now(),
    val auduser: String,
    val audip: String,
    val audtypeaction: String,
    val audmodule: String
)

data class Currency(
    val id: String,
    val abbreviation: String,
    val currency: String,
    val country: String,
    val audDate: LocalDateTime = LocalDateTime.now(),
    val audUser: String,
    val audIp: String,
    val audTypeAction: String,
    val audModule: String
)