package com.rer.auction.presentation.auctiondetail.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rer.auction.R
import com.rer.core.composable.label.CustomText
import com.rer.core.utils.calculateRemainingTimeInSeconds
import com.rer.core.utils.formatRemainingTime
import com.rer.core.utils.parseTargetDate
import kotlinx.coroutines.delay

@Suppress("kotlin:S6619")
@Composable
fun CountdownTimer(targetDateStr: String) {
    val targetDate = remember(targetDateStr) {
        parseTargetDate(targetDateStr)
    }
    if (targetDate != null) {
        var remainingTime by remember {
            mutableLongStateOf(
                calculateRemainingTimeInSeconds(
                    targetDate
                )
            )
        }
        var isFinished by remember { mutableStateOf(false) }

        LaunchedEffect(remainingTime) {
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime = calculateRemainingTimeInSeconds(targetDate)
            }
            isFinished = true
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.error.copy(
                        alpha = 0.3f
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            if (isFinished) {
                CustomText(
                    text = stringResource(R.string.bid_time_is_over),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp
                    ),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
            } else {
                val formattedTime = formatRemainingTime(remainingTime)
                CustomText(
                    text = formattedTime, style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp
                    ),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }

    }


}