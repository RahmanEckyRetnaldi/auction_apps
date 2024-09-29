package com.rer.auction.presentation.auctiondetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rer.auction.R
import com.rer.auction.presentation.auctiondetail.AuctionDetailIntent
import com.rer.auction.presentation.auctiondetail.AuctionDetailState
import com.rer.auction.presentation.auctiondetail.ui.component.BidItemComponent
import com.rer.auction.presentation.auctiondetail.ui.component.CountdownTimer
import com.rer.core.composable.appbar.BaseAppBar
import com.rer.core.composable.appbar.BaseAppBarParams
import com.rer.core.composable.button.BaseButton
import com.rer.core.composable.button.BaseButtonParams
import com.rer.core.composable.image.ImageLoader
import com.rer.core.composable.input.GeneralInputData
import com.rer.core.composable.input.GeneralInputOutlined
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.scaffold.DesignScaffold
import com.rer.core.composable.spacer.Height
import com.rer.core.ui.theme.defaultPadding
import com.rer.core.ui.theme.primaryLight

@Composable
fun AuctionDetailView(
    state: AuctionDetailState,
    intent: (AuctionDetailIntent) -> Unit
) {
    DesignScaffold(
        showAppBar = true,
        appBar = {
            BaseAppBar(
                modifier = Modifier
                    .zIndex(1f)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)),
                title = {
                    CustomText(
                        text = state.auction.productName,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                params = BaseAppBarParams(
                    useSafeArea = true,
                    background = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                    contentColor = Color.White
                ),
                onBackPressed = {
                    intent.invoke(AuctionDetailIntent.OnBackPressed)
                }
            )
        },
        body = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                item {
                    Box(
                        modifier = Modifier.wrapContentSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        ImageLoader(
                            url = state.auction.imageUrl,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.3f)
                        )
                        CountdownTimer(targetDateStr = state.auction.auctionEndTime)
                    }

                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(defaultPadding),
                        verticalArrangement = Arrangement.Center
                    ) {
                        CustomText(
                            text = state.auction.productName,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 24.sp
                            ),
                            color = primaryLight,
                            fontWeight = FontWeight.Bold
                        )
                        8.Height()
                        CustomText(
                            text = state.auction.highestBidMYFormat(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                        10.Height()
                        CustomText(
                            text = state.auction.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                item {
                    CustomText(
                        text = stringResource(R.string.bid_history),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 18.sp
                        ),
                        color = primaryLight,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = defaultPadding)
                    )
                    8.Height()
                }
                items(state.auction.bids) { bid ->
                    BidItemComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = defaultPadding),
                        bid = bid
                    )
                    4.Height()
                }


            }
        },
        padding = 0.dp,
        bottomView = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .background(color = Color.White)
                    .padding(horizontal = defaultPadding)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.Bottom
            ) {
                GeneralInputOutlined(
                    modifier = Modifier.fillMaxWidth(),
                    data = GeneralInputData(
                        value = state.bidAmount,
                        digitOnly = true,
                        title = null,
                        placeholder = stringResource(R.string.input_amount_bid),
                        onValueChange = { bidAmount ->
                            intent(AuctionDetailIntent.OnBidAmountChanged(bidAmount))
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                10.Height()
                BaseButton(
                    baseButtonParams = BaseButtonParams(
                        text = stringResource(R.string.bid_now),
                        enabled = true,
                        height = 52.dp
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    intent.invoke(AuctionDetailIntent.OnBidNowClick)
                }
            }


        }
    )
}