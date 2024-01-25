package com.bisa.capacitacion.atm.transport.controller

import com.bisa.capacitacion.atm.business.interactors.AccountInteractor
import com.bisa.capacitacion.atm.transport.CreateAccountIn
import com.bisa.capacitacion.atm.transport.CreateAccountOut
import com.bisa.capacitacion.atm.transport.GetBalanceOut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerAccountAPI : AccountAPI{

    @Autowired
    private lateinit var accountInteractor: AccountInteractor

    override fun createAccount(createAccountIn: CreateAccountIn): CreateAccountOut? {
        return accountInteractor?.createAccount(createAccountIn)
    }

    override fun getAccount(accountNumber: String): GetBalanceOut? {
        return accountInteractor?.getAccount(accountNumber)
    }
}