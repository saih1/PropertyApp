package com.example.propertyapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.propertyapp.view.navigation.NavigationSetUp
import com.example.propertyapp.view.theme.PropertyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertyAppTheme {
                navHostController = rememberNavController()
                NavigationSetUp(controller = navHostController)
            }
        }
    }
}