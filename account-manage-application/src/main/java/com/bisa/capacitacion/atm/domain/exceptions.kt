package com.bisa.capacitacion.atm.domain

import com.bisa.commons.dsl.DomainException

class CuentaNoEncontradoException(): DomainException("Number account not exist")
class MonedaNoEncontradoException(): DomainException("Currency not exist")