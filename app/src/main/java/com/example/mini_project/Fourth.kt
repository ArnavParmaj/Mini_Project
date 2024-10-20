package com.example.mini_project

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mini_project.ui.theme.Mini_ProjectTheme

@Composable
fun Fourth(navigationToFirst: () -> Unit, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Main content without background image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Row for Profile Icon and User Greeting
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello, Arnav",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Top section with Help, FAQ, Sign In, Sign Up buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Add buttons here if needed
            }

            // Center section with Head Council and Clubs buttons
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp), // Adds space above the center section
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate("fifth") }, // Navigate to Fifth screen
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("Clubs")
                    }
                    Button(
                        onClick = { navController.navigate("help") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("Help")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFourth() {
    Mini_ProjectTheme {
        val navController = rememberNavController()

        Fourth(
            navigationToFirst = { /* No-op for preview */ },
            navController = navController
        )
    }
}
