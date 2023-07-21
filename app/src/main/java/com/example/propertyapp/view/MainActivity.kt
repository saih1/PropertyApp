package com.example.propertyapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.propertyapp.common.RequestState
import com.example.propertyapp.common.Status
import com.example.propertyapp.data.remote.dto.PropertyDto
import com.example.propertyapp.ui.theme.PropertyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: PropertyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TODO: Need to find a better way
                    // This is only for testing
                    val properties: RequestState<PropertyDto> by vm.properties.collectAsState()

                    Column(
                        Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                    ) {
                        Button(
                            onClick = vm::fetchProperties
                        ) {
                            Text(text = "CLICK ME")
                        }

                        when (properties.status) {
                            Status.SUCCESS -> {
                                LazyColumn {
                                    items(items = properties.data?.data ?: emptyList(), key = { it.id })
                                    { property ->
                                        Text(
                                            modifier = Modifier.padding(5.dp),
                                            text = "🚀 ${property.description}"
                                        )
                                        Divider(modifier = Modifier.padding(vertical = 5.dp))
                                    }
                                }
                            }
                            Status.ERROR -> {
                                Text(text = "ERROR")
                            }
                            Status.IDLE -> {
                                Text(text = "ERROR")
                            }
                            Status.LOADING -> {
                                CircularProgressIndicator()
                            }
                        }


                    }
                }
            }
        }
    }
}