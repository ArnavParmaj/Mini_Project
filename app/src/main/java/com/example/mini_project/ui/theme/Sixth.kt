package com.example.mini_project.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.border
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mini_project.R
@Composable
fun Sixth(navController: NavHostController) {
    val backgroundImage: Painter = painterResource(id = R.drawable.bgm) // Background image

    var description by remember {
        mutableStateOf(
            TextFieldValue(
                "GDSC is a global community of university students passionate about technology. " +
                        "Supported by Google, it provides opportunities for students to learn, build, and " +
                        "network in various tech areas through workshops, events, and projects."
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Buttons Row with Members, Quiz, and Posts
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate("seven") }) {
                    Text("Members")
                }
                Button(onClick = { navController.navigate("quiz") }) {
                    Text("Quiz")
                }
                Button(onClick = { navController.navigate("posts") }) {
                    Text("Posts")
                }
            }

            // Club Page Title
            Text(
                text = "Club Page",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Circular Image
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace with your image resource ID
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .padding(8.dp)
            )

            // GDSC Title
            Text(
                text = "GDSC",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Description Textbox
            BasicTextField(
                value = description,
                onValueChange = { newValue -> description = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
                    .padding(8.dp),
                enabled = false // Make it read-only
            )

            // Achievements Table
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.Gray
                )
                AchievementCard(year = "2024", achievement = "Best Club Award")
                AchievementCard(year = "2023", achievement = "Top Tech Community")
            }
        }
    }
}

@Composable
fun AchievementCard(year: String, achievement: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = year, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            Text(text = achievement, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSixth() {
    Sixth(navController = rememberNavController())
}
