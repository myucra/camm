package com.bisa.capacitacion.atm.datasources.postgres

import com.bisa.capacitacion.atm.business.repositories.EntityRepository
import com.bisa.capacitacion.atm.domain.Account
import com.bisa.capacitacion.atm.domain.AccountHistory
import com.bisa.capacitacion.atm.domain.Currency
import org.springframework.jdbc.core.JdbcTemplate

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class PostgresRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): EntityRepository {

    override fun saveAccount(account: Account): Account {
        val sql = """
            INSERT INTO Account (
                id, idCustomer, idCurrency, accountNumber, accountType, balance, state,
                createDate, audDate, audUser, audIp, audTypeAction, audModule
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        jdbcTemplate.update(
            sql,
            account.id,
            account.idcustomer,
            account.idcurrency,
            account.accountnumber,
            account.accounttype,
            account.balance,
            account.state,
            account.createdate,
            account.auddate,
            account.auduser,
            account.audip,
            account.audtypeaction,
            account.audmodule
        )
        return account
    }

    override fun saveAccountHistory(accountHistory: AccountHistory): AccountHistory {
        val sql = """
            INSERT INTO AccountHistory (
                id, idCustomer, idCurrency, accountNumber, accountType, balance, state,
                createDate, audDate, audUser, audIp, audTypeAction, audModule
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        jdbcTemplate.update(
            sql,
            accountHistory.id,
            accountHistory.idcustomer,
            accountHistory.idcurrency,
            accountHistory.accountnumber,
            accountHistory.accounttype,
            accountHistory.balance,
            accountHistory.state,
            accountHistory.createdate,
            accountHistory.auddate,
            accountHistory.auduser,
            accountHistory.audip,
            accountHistory.audtypeaction,
            accountHistory.audmodule
        )
        return accountHistory
    }

    override fun getByAccountNumber(accountNumber: String): Account? {
        val sql = "SELECT * FROM account WHERE accountnumber = ?"
        return jdbcTemplate.query(sql, arrayOf(accountNumber)) { rs, _ ->
            Account(
                rs.getString("id"),
                rs.getString("idCustomer"),
                rs.getString("idCurrency"),
                rs.getString("accountNumber"),
                rs.getString("accountType"),
                rs.getBigDecimal("balance"),
                rs.getString("state"),
                rs.getTimestamp("createDate").toLocalDateTime(),
                rs.getTimestamp("audDate").toLocalDateTime(),
                rs.getString("audUser"),
                rs.getString("audIp"),
                rs.getString("audTypeAction"),
                rs.getString("audModule")
            )
        }.firstOrNull()
    }

    override fun getByIdCurrency(id: String): Currency? {
        val sql = "SELECT * FROM currency WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Currency(
                rs.getString("id"),
                rs.getString("abbreviation"),
                rs.getString("currency"),
                rs.getString("country"),
                rs.getTimestamp("audDate").toLocalDateTime(),
                rs.getString("audUser"),
                rs.getString("audIp"),
                rs.getString("audTypeAction"),
                rs.getString("audModule")
            )
        }.firstOrNull()
    }
}
