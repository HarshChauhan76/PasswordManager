package com.example.passwordmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository

    init {
        val dao = AccountDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(dao)
    }

    fun addAccount(type: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Account(type = type, username = username, password = encrypt(password)))
        }
    }

fun getAccountById(id: String) = liveData(Dispatchers.IO) {
    val account = repository.getAccountById(id)
    emit(account)
}

    fun deleteAccount(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

    private fun encrypt(data: String): String {
        // Implement encryption
        return data // Placeholder for actual encryption logic
    }

    private fun decrypt(data: String): String {
        // Implement decryption
        return data // Placeholder for actual decryption logic
    }
}