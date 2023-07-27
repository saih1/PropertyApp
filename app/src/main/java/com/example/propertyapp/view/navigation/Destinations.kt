package com.example.propertyapp.view.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.view.PropertyViewModel
import com.example.propertyapp.view.screens.DetailScreen
import com.example.propertyapp.view.screens.PropertyListScreen

enum class Destination {
    LIST_SCREEN,
    DETAIL_SCREEN
}

fun NavGraphBuilder.listNavGraph(
    vm: PropertyViewModel,
    onItemClick: (PropertyEntity) -> Unit,
    onErrorRetryClick: () -> Unit,
    onRefreshClick: () -> Unit
) {
    composable(
        route = Destination.LIST_SCREEN.name,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(durationMillis = 500),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            scaleOut(
                animationSpec = tween(
                    durationMillis = 700,
                    delayMillis = 200
                ),
                targetScale = 0.8f
            )
        }
    ) {
        PropertyListScreen(
            vm = vm,
            onItemClick = { onItemClick(it) },
            onErrorRetryClick = onErrorRetryClick,
            onRefreshClick = onRefreshClick
        )
    }
}

fun NavGraphBuilder.detailNavGraph(
    vm: PropertyViewModel,
    onBackClick: () -> Unit
) {
    composable(
        route = Destination.DETAIL_SCREEN.name,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(durationMillis = 500),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(
                    durationMillis = 700,
                    delayMillis = 200
                ),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        },
        popEnterTransition = {
            scaleIn(
                animationSpec = tween(durationMillis = 700),
                initialScale = 0.8f
            )
        }
    ) {
        DetailScreen(
            vm = vm,
            onBackClick = onBackClick
        )
    }
}