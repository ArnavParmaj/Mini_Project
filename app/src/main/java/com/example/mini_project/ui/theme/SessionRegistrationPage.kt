package com.example.mini_project.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import com.example.mini_project.R

data class Session(
    val id: String,
    val name: String,
    val date: String,
    val duration: String,
    val room: String,
    val description: String,
    var totalTickets: Int = 100,
    var ticketsSold: Int = 0
) {
    val ticketsLeft: Int
        get() = totalTickets - ticketsSold
}

@Composable
fun SessionRegistrationPage() {
    val session = remember {
        Session(
            id = "1",
            name = "Tech Session",
            date = "2024-09-30",
            duration = "2 hours",
            room = "Room 101",
            description = "Join us for an engaging Tech Session where you will learn about the latest advancements in technology."
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bgm), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay the content
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Session Ticket Registration", style = MaterialTheme.typography.headlineLarge)
            SessionCard(session) {
                RegisterForEvent(session)
            }
        }
    }
}

@Composable
fun SessionCard(
    session: Session,
    onRegister: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(session.name, style = MaterialTheme.typography.titleMedium)
            Text("Date: ${session.date}", style = MaterialTheme.typography.bodyMedium)
            Text("Duration: ${session.duration}", style = MaterialTheme.typography.bodyMedium)
            Text("Room: ${session.room}", style = MaterialTheme.typography.bodyMedium)
            Text("Description: ${session.description}", style = MaterialTheme.typography.bodyMedium)
            Text("Tickets Left: ${session.ticketsLeft}", style = MaterialTheme.typography.bodyMedium)

            if (session.ticketsLeft > 0) {
                onRegister() // Render the composable action
            } else {
                Text("Tickets Sold Out", color = Color.Red)
            }
        }
    }
}

@Composable
fun RegisterForEvent(session: Session) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var moodleId by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var division by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var registrationMessage by remember { mutableStateOf("") }

    Button(onClick = { showDialog = true }) {
        Text("Register for Event")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Enter Your Information") },
            text = {
                Column {
                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("Name") },
                        isError = errorMessage.isNotEmpty() && userName.isBlank()
                    )
                    TextField(
                        value = moodleId,
                        onValueChange = { moodleId = it },
                        label = { Text("Moodle ID") },
                        isError = errorMessage.isNotEmpty() && moodleId.isBlank()
                    )
                    TextField(
                        value = userEmail,
                        onValueChange = { userEmail = it },
                        label = { Text("Email") },
                        isError = errorMessage.isNotEmpty() && userEmail.isBlank()
                    )
                    TextField(
                        value = division,
                        onValueChange = { division = it },
                        label = { Text("Division") },
                        isError = errorMessage.isNotEmpty() && division.isBlank()
                    )
                    TextField(
                        value = department,
                        onValueChange = { department = it },
                        label = { Text("Department") },
                        isError = errorMessage.isNotEmpty() && department.isBlank()
                    )
                    if (errorMessage.isNotEmpty()) {
                        Text(errorMessage, color = MaterialTheme.colorScheme.error)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (userName.isBlank() || moodleId.isBlank() || userEmail.isBlank() ||
                        division.isBlank() || department.isBlank()) {
                        errorMessage = "All fields are required."
                    } else {
                        if (session.ticketsLeft > 0) {
                            session.ticketsSold++
                            registrationMessage = "Registered for ${session.name}. Tickets left: ${session.ticketsLeft - 1}"
                            Toast.makeText(context, registrationMessage, Toast.LENGTH_SHORT).show()
                            showDialog = false
                        }
                    }
                }) {
                    Text("Submit")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (registrationMessage.isNotEmpty()) {
        Text(registrationMessage, color = Color.Green, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSessionRegistrationPage() {
    SessionRegistrationPage()
}
