package com.orion.templete.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.presentation.swipartscreen.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MyViewModel = hiltViewModel()) {
    Log.e("qwerty" , viewModel.artWorkData.value.toString())
    Scaffold(topBar = { TopAppBar(title = { Text(text = "main") }) }, content = {
        Text(text = "Hello Android", modifier = Modifier.padding(it))
    })
}