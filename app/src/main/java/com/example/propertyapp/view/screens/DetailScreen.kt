@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.propertyapp.R
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.view.PropertyViewModel

@Composable
fun DetailScreen(
    vm: PropertyViewModel,
    onBackClick: () -> Unit
) {
    val selectedProperty by vm.selectedProperty.collectAsState()
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { DetailTopAppBar(onBackClick = onBackClick) },
    ) { paddingValues ->
        Surface(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(scrollState)
            .wrapContentSize()
        ) {
            selectedProperty?.let {
                PropertyDetailComposable(
                    property = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun PropertyDetailComposable(
    property: PropertyEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = property.propertyImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        PropertyContent(
            property = property,
            isExpended = true
        )
    }
}

@Composable
fun DetailTopAppBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back icon"
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewDetailTopAppBar() = DetailTopAppBar {}

@Preview(showBackground = true)
@Composable
fun PreviewPropertyDetailComposable() = PropertyDetailComposable(
    property = PropertyEntity.DEFAULT,
    modifier = Modifier.padding(10.dp)
)