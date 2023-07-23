package com.example.propertyapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.example.propertyapp.view.navigation.NavigationSetUp
import com.example.propertyapp.view.theme.PropertyAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertyAppTheme {
                // com.google.accompanist.navigation.animation
                // soon replaced by androidx.navigation.compose
                navHostController = rememberAnimatedNavController()
                NavigationSetUp(controller = navHostController)
            }
        }
    }
}