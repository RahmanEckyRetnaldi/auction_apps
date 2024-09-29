package com.rer.auction.presentation.auction.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rer.auction.R
import com.rer.auction.domain.entity.ChatEntity
import com.rer.auction.presentation.auction.AuctionState
import com.rer.core.composable.input.GeneralInputData
import com.rer.core.composable.input.GeneralInputOutlined
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Height
import com.rer.core.composable.spacer.Width
import kotlinx.coroutines.launch

@Composable
fun ChatsScreenComponent(
    modifier: Modifier = Modifier,
    auctionState: AuctionState,
    onNewMessageChanged: (newMessage: String) -> Unit,
    onMessageSend: () -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(auctionState.liveChats.size) {
        if(auctionState.liveChats.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(auctionState.liveChats.size - 1)
            }
        }
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(auctionState.liveChats) { chat ->
                ChatItem(chat = chat, modifier = Modifier.fillMaxWidth())
            }
        }
        16.Height()
        MessageField(
            modifier = Modifier.fillMaxWidth(),
            chatMessage = auctionState.chatMessage,
            onNewMessageChanged = onNewMessageChanged,
            onMessageSend = onMessageSend
        )

    }

}

@Composable
private fun MessageField(
    modifier: Modifier = Modifier,
    chatMessage: String,
    onNewMessageChanged: (newMessage: String) -> Unit,
    onMessageSend: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GeneralInputOutlined(
            modifier = Modifier.weight(1f),
            data = GeneralInputData(
                value = chatMessage,
                title = null,
                placeholder = stringResource(R.string.type_your_message_here),
                onValueChange = { newMessage ->
                    onNewMessageChanged.invoke(newMessage)
                }
            )
        )
        10.Width()
        IconButton(
            onClick = {
                if (chatMessage.isNotBlank()) {
                    onMessageSend.invoke()
                }
            },
            modifier = Modifier
                .clip(CircleShape)
                .size(
                    52.dp
                ),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ChatItem(modifier: Modifier = Modifier, chat: ChatEntity) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.rer.core.R.drawable.profile_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        8.Width()
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = chat.user, style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
            CustomText(
                text = chat.message, style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                )
            )
        }
    }
}