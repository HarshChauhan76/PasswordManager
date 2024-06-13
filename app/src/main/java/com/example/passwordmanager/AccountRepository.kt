package com.example.passwordmanager

class AccountRepository(private val accountDao: AccountDao) {
    suspend fun insert(account: Account) {
        accountDao.insert(account)
    }

    fun getAccountById(id: String): Account? {
        return accountDao.getAccountById(id)
    }

    suspend fun delete(id: String) {
        accountDao.delete(id)
    }
}
