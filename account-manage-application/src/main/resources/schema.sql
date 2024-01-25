CREATE TABLE IF NOT EXISTS Account (
    id TEXT NOT NULL PRIMARY KEY,
    idCustomer TEXT NOT NULL,
    idCurrency TEXT NOT NULL,
    accountNumber VARCHAR(255) NOT NULL,
    accountType VARCHAR(1) NOT NULL, --D=Debit, C = Credit
    balance DECIMAL(10, 2) NOT NULL,
    state VARCHAR(20) NOT NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- auditory
    audDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    audUser VARCHAR(50) NOT NULL,
    audIp VARCHAR(50) NOT NULL,
    audTypeAction VARCHAR(1) NOT NULL, -- I=insert, U=update, D=Delete
    audModule VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS AccountHistory (
    id TEXT NOT NULL PRIMARY KEY,
    idCustomer TEXT NOT NULL,
    idCurrency TEXT NOT NULL,
    accountNumber VARCHAR(255) NOT NULL,
    accountType VARCHAR(1) NOT NULL, --D=Debit, C = Credit
    balance DECIMAL(10, 2) NOT NULL,
    state VARCHAR(20) NOT NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- auditory
    audDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    audUser VARCHAR(50) NOT NULL,
    audIp VARCHAR(50) NOT NULL,
    audTypeAction VARCHAR(1) NOT NULL, -- I=insert, U=update, D=Delete
    audModule VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Currency (
    id TEXT NOT NULL PRIMARY KEY,
    abbreviation VARCHAR(10) NOT NULL,
    currency VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    -- auditory
    audDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    audUser VARCHAR(50) NOT NULL,
    audIp VARCHAR(50) NOT NULL,
    audTypeAction VARCHAR(1) NOT NULL, -- I=insert, U=update, D=Delete
    audModule VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS CurrencyHistory (
    id TEXT NOT NULL PRIMARY KEY,
    abbreviation VARCHAR(10) NOT NULL,
    currency VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    -- auditory
    audDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    audUser VARCHAR(50) NOT NULL,
    audIp VARCHAR(50) NOT NULL,
    audTypeAction VARCHAR(1) NOT NULL, -- I=insert, U=update, D=Delete
    audModule VARCHAR(50) NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
/*INICIALIZAMOS LA TABLA DE MONEDAS*/
/*
insert into Currency (id, abbreviation, currency, country, audDate, audUser, audIp, audTypeAction, audModule ) values ('4f3b58f8-2f92-4897-8510-91bcd673642c', 'BS', 'Bolivianos', 'Bolivia', now(), current_user, inet_client_addr(), 'I', 'sql-inicial');
insert into Currency (id, abbreviation, currency, country, audDate, audUser, audIp, audTypeAction, audModule ) values ('6e1a6ce2-684d-44e2-849b-c4b6455031a7', '$US', 'Dolares americanos', 'Estados Unidos', now(), current_user, inet_client_addr(), 'I', 'sql-inicial');
insert into CurrencyHistory select * from Currency;
*/