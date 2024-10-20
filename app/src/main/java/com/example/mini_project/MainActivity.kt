package com.example.mini_project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.mini_project.ui.theme.Mini_ProjectTheme
import com.example.mini_project.ui.theme.Fifth
import com.example.mini_project.ui.theme.First
import com.example.mini_project.ui.theme.SessionRegistrationPage
import com.example.mini_project.ui.theme.Seven
import com.example.mini_project.ui.theme.Sixth

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Mini_ProjectTheme {
                val navController = rememberNavController()


                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Set up the NavHost to handle the navigation between different screens
                    NavHost(
                        navController = navController,
                        startDestination = "first", // Set the starting screen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Composable for the First screen (Welcome Page)
                        composable("first") {
                            First(
                                navigationToSecond = { navController.navigate("second") }, // Navigate to login
                                navigationToThird = {navController.navigate("third")},
                                navigationToFourth = { /* Add navigation to guest here */ },
                            )
                        }
                        // Composable for the Second screen (Login Page)
                        composable("second") {
                            Second(
                                navigationToFirst = { navController.popBackStack() }, // Go back to first screen
                                navigationToSignUp = { navController.navigate("third") },
                                navigationToForgotPassword = { /* Navigate to forgot password */ },
                                navigationToFourth = { navController.navigate("fourth") } // Navigate to home
                            )
                        }
                        // Composable for the Fourth screen (Home Page)
                        composable("fourth") {
                            Fourth(
                                navigationToFirst = { navController.popBackStack("first", false) },
                                navController = navController // Go back to first screen
                            )
                        }
                        composable("fifth") {
                            Fifth(
                                navController = navController,
                                userId = "Arnav",
                                onLogout = {
                                    navController.popBackStack("first", false)
                                }
                            )
                        }
                        // Composable for the Sixth screen (GDSC Page)
                        composable("sixth") {
                            Sixth(
                                navController = navController
                            )
                        }
                        composable("seven"){
                            Seven()
                        }
                        composable("tickets") {
                            SessionRegistrationPage()
                        }
                        composable("help") {
                            HelpPage()
                        }
                    }
                }
            }
        }
    }
}
