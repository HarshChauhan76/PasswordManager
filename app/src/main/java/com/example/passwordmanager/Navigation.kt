package com.example.passwordmanager

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String = "home") {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") { HomeScreen(navController) }
        composable("add_new_account") { AddNewAccountScreen(navController) }
        composable("account_details/{accountId}") { backStackEntry ->
            val accountId = backStackEntry.arguments?.getString("accountId")
            accountId?.let { AccountDetailsScreen(navController, it) }
        }
    }
}

