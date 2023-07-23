package com.example.propertyapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.propertyapp.view.screens.PropertyListScreen
import com.example.propertyapp.view.theme.PropertyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: PropertyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertyAppTheme {
                PropertyListScreen(
                    vm = vm,
                    onItemClick = {
                        // TODO: Navigate to Detail screen
                    },
                    onRetryClick = {
                        vm.fetchProperties()
                    }
                )
            }
        }
    }
}