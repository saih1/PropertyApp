package com.example.propertyapp.view.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
                animationSpec = tween(500),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            this.slideOutOfContainer(
                animationSpec = tween(500),
                towards = AnimatedContentTransitionScope.SlideDirection.End
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
                animationSpec = tween(500),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            this.slideOutOfContainer(
                animationSpec = tween(500),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }
    ) {
        DetailScreen(
            vm = vm,
            onBackClick = onBackClick
        )
    }
}