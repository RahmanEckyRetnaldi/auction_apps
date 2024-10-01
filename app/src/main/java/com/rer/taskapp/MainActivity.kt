package com.rer.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rer.core.ui.theme.AppTheme
import com.rer.core.utils.NavigationDestination
import com.rer.taskapp.navigation.AppNavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import dev.olshevski.navigation.reimagined.rememberNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController<NavigationDestination>(
                startDestination = NavigationDestination.Auction()
            )
            AppTheme {
                AppNavigationGraph(navController = navController)
            }
        }
    }
}