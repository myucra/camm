package com.bisa.capacitacion.atm.business.interactors

import com.bisa.capacitacion.atm.business.repositories.EntityRepository
import com.bisa.capacitacion.atm.domain.Account
import com.bisa.capacitacion.atm.domain.AccountHistory
import com.bisa.capacitacion.atm.domain.Currency
import com.bisa.capacitacion.atm.transport.CreateAccountIn
import com.bisa.capacitacion.atm.transport.CreateAccountOut
import com.bisa.capacitacion.atm.transport.GetBalanceOut
import com.bisa.commons.dsl.DomainException
import com.bisa.commons.lang.qualifiers.UseCase
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@Service
class AccountInteractor (
    private val entityRepository: EntityRepository
){
    @UseCase
    @Throws(DomainException::class)
    fun getAccount(accountNumber: String): GetBalanceOut? {

        val account: Account? = entityRepository.getByAccountNumber( accountNumber )
        if (account!=null){
            val currency: Currency? = account?.let { entityRepository.getByIdCurrency(it.idcurrency) }
            if (currency != null) {
                return account?.state?.let { GetBalanceOut(account.balance, currency.abbreviation, it) }
            }
        }
        else{
            throw DomainException("Account number does not exist")
        }
        return null
    }

    @UseCase
    @Throws(DomainException::class)
    fun createAccount(createAccountIn: CreateAccountIn): CreateAccountOut{
        val varBal = 0.0
        val varIdAccount: String = UUID.randomUUID().toString()
        val varDate: LocalDateTime = LocalDateTime.now()
        val varAccountNumber = generateAccountNumber()
        val account = Account(
            varIdAccount,
            createAccountIn.idCustomer.toString(),
            createAccountIn.idCurrency.toString(),
            varAccountNumber,
            createAccountIn.accountType.toString(),
            varBal.toBigDecimal(),
            "Active",
            varDate,
            varDate,
            System.getProperty("user.name"),
            InetAddress.getLocalHost().hostAddress,
            "I",
            "createAccount"
        )
        val accountHistory = AccountHistory(
            varIdAccount,
            createAccountIn.idCustomer.toString(),
            createAccountIn.idCurrency.toString(),
            varAccountNumber,
            createAccountIn.accountType.toString(),
            varBal.toBigDecimal(),
            "Active",
            varDate,
            varDate,
            System.getProperty("user.name"),
            InetAddress.getLocalHost().hostAddress,
            "I",
            "createAccount"
        )
        entityRepository.saveAccountHistory(accountHistory)

        return CreateAccountOut(entityRepository.saveAccount(account).accountnumber)
    }

    private fun generateAccountNumber (): String {
        val bankCode = "001"
        val branchCode = "0001"
        val accountType = "01"
        val randomDigits = Random.nextInt(1000, 9999).toString() // Números aleatorios simulados

        return "$bankCode$branchCode$accountType$randomDigits"

    }
}