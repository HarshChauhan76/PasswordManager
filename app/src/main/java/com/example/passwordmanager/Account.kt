package com.example.passwordmanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert

import androidx.room.Query
import java.util.UUID

@Entity
data class Account(
    val id: String = UUID.randomUUID().toString(),
    val type: String,
    val username: String,
    val password: String
)

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: Account)

    @Query("SELECT * FROM account WHERE id = :id")
    fun getAccountById(id: String): Account?

    @Delete
    suspend fun delete(account: Account)
    abstract fun delete(account: String)
}

