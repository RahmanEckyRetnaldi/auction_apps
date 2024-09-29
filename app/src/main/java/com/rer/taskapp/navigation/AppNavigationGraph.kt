package com.rer.taskapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import com.rer.auction.presentation.isAuctionNavDestination
import com.rer.core.utils.NavigationDestination
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavController

@Composable
fun AppNavigationGraph(
    navController: NavController<NavigationDestination>
) {
    NavBackHandler(controller = navController)
    AnimatedNavHost(
        controller = navController,
        transitionSpec = { action, _, _ ->
            val direction =
                if (action == NavAction.Pop) {
                    AnimatedContentTransitionScope.SlideDirection.End
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Start
                }
            slideIntoContainer(direction) togetherWith slideOutOfContainer(direction)
        }

    ) { navDestination ->
        when {
            navDestination.isAuctionNavDestination(navController = navController) ->
                return@AnimatedNavHost
        }
    }
}