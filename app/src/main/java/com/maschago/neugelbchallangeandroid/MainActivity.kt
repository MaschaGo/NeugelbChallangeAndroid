package com.maschago.neugelbchallangeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.maschago.neugelbchallangeandroid.presentation.ui.components.CustomAppBar
import com.maschago.neugelbchallangeandroid.presentation.navigation.NavHost
import com.maschago.neugelbchallangeandroid.core.ui.theme.NeugelbChallangeAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeugelbChallangeAndroidTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        CustomAppBar(
                            title = stringResource(R.string.app_name),
                            navController = navController
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), color = MaterialTheme.colorScheme.surface
                    ) {
                        NavHost(navController = navController)
                    }
                }
            }
        }
    }
}
