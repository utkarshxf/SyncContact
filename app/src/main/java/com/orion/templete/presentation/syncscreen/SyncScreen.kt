package com.orion.templete.presentation.syncscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SyncScreen(modifier: Modifier = Modifier , viewModel: SyncScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val contactData = viewModel.contactData.value
    LaunchedEffect(contactData.error) {
        contactData.error?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { viewModel.fetchContacts() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Sync Contacts")
        }

        Text("Syncing Progress: ${viewModel.syncProgress.value}%")
    }
}