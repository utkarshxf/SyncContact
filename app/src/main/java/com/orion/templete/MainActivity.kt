package com.orion.templete

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.orion.templete.presentation.syncscreen.SyncScreen
import com.orion.templete.presentation.ui.theme.TempleteTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
const val PERMISSIONS_REQUEST = 1


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        val allPermissions = listOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
        )
        val necessaryPermissions = mutableListOf<String>()
        allPermissions.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                necessaryPermissions.add(it)
            }
        }
        if (necessaryPermissions.any()) {
            ActivityCompat.requestPermissions(
                this, necessaryPermissions.toTypedArray(), PERMISSIONS_REQUEST
            )
        }
        super.onCreate(savedInstanceState)
        setContent {
            TempleteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SyncScreen()
                }
            }
        }
    }
}
