package com.example.passwordmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AccountDetailsScreen(navController: NavController, accountId: String, viewModel: AccountViewModel = viewModel()) {
    val account = viewModel.getAccountById(accountId).observeAsState()

    account.value?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Account Type: ${it.type}", style = MaterialTheme.typography.h6)
            Text("Username/Email: ${it.username}", style = MaterialTheme.typography.body1)
            Text("Password: ${it.password}", style = MaterialTheme.typography.body1)
            Row {
                Button(onClick = { /* Implement edit functionality */ }) {
                    Text("Edit")
                }
                Button(
                    onClick = {
                        viewModel.deleteAccount(accountId)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Delete")
                }
            }
        }
    } ?: run {
        // Handle the case when account is null (not found)
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Account not found", style = MaterialTheme.typography.h6)
        }
    }
}
