package com.bisa.capacitacion.atm.business.repositories

import com.bisa.capacitacion.atm.domain.*
import com.bisa.commons.dsl.DomainException

interface EntityRepository {

    @Throws(CuentaNoEncontradoException::class)
    fun getByAccountNumber(accountNumber: String): Account?

    @Throws(DomainException::class)
    fun saveAccount(account: Account): Account

    @Throws(DomainException::class)
    fun saveAccountHistory(accountHistory: AccountHistory): AccountHistory

    @Throws(MonedaNoEncontradoException::class)
    fun getByIdCurrency(id: String): Currency?
}