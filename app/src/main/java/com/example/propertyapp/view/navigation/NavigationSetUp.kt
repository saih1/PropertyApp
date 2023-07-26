@file:OptIn(ExperimentalAnimationApi::class)

package com.example.propertyapp.view.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.propertyapp.view.PropertyViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost

@Composable
fun NavigationSetUp(controller: NavHostController) {
    val vm: PropertyViewModel = viewModel()
    AnimatedNavHost(
        navController = controller,
        startDestination = Destination.LIST_SCREEN.name
    ) {
        listNavGraph(
            vm = vm,
            onItemClick = {
                vm.selectProperty(it)
                controller.navigate(Destination.DETAIL_SCREEN.name)
            },
            onErrorRetryClick = { vm.fetchProperties() },
            onRefreshClick = { vm.fetchProperties() }
        )
        detailNavGraph(
            vm = vm,
            onBackClick = {
                vm.clearPropertySelection()
                controller.navigate(Destination.LIST_SCREEN.name) {
                    // Clear the back stack when navigating to the List screen
                    popUpTo(controller.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}