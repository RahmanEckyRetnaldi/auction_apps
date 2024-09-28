package com.rer.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rer.auction.domain.entity.ChatEntity
import com.rer.auction.presentation.auction.AuctionState
import com.rer.auction.presentation.auction.ui.AuctionView
import com.rer.core.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AuctionView(
                    state = AuctionState().copy(
                        liveChats = listOf(
                            ChatEntity(
                                user = "Rer",
                                message = "Hello"
                            ),
                            ChatEntity(
                                user = "Rer",
                                message = "Hello"
                            ),
                            ChatEntity(
                                user = "Rer",
                                message = "Hello"
                            )
                        )
                    )
                )


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}