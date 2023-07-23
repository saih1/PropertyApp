@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.propertyapp.R
import com.example.propertyapp.common.Destination
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.view.PropertyViewModel

fun NavGraphBuilder.detailNavGraph(
    vm: PropertyViewModel,
    onBackClick: () -> Unit
) {
    composable(route = Destination.DETAIL_SCREEN.name) {
        DetailScreen(
            vm = vm,
            onBackClick = onBackClick
        )
    }
}

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
                PropertyDetailComposable(property = it)
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
        modifier = modifier.padding(15.dp),
    ) {
        AsyncImage(
            model = property.propertyImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .height(300.dp),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        PropertyContent(
            modifier = Modifier.padding(horizontal = 15.dp),
            property = property,
            isDetailView = true
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
                text = "Property App",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold
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
    property = PropertyEntity.DEFAULT
)