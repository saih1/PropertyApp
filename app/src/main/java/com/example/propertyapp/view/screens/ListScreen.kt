@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.propertyapp.view.navigation.Destination
import com.example.propertyapp.common.Status
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.view.PropertyViewModel

fun NavGraphBuilder.listNavGraph(
    vm: PropertyViewModel,
    onItemClick: (PropertyEntity) -> Unit,
    onErrorRetryClick: () -> Unit
) {
    composable(route = Destination.LIST_SCREEN.name) {
        PropertyListScreen(
            vm = vm,
            onItemClick = { onItemClick(it) },
            onErrorRetryClick = onErrorRetryClick
        )
    }
}

@Composable
fun PropertyListScreen(
    vm: PropertyViewModel,
    onItemClick: (PropertyEntity) -> Unit,
    onErrorRetryClick: () -> Unit
) {
    val properties by vm.properties.collectAsState()
    Scaffold(
        topBar = { ListTopAppBar() }
    ) { paddingValues ->
        when (properties.status) {
            Status.SUCCESS -> {
                PropertyListComposable(
                    properties = properties.data ?: emptyList(),
                    onItemClick = { onItemClick(it) },
                    paddingValues = paddingValues
                )
            }
            Status.ERROR -> GenericErrorComposable(
                onErrorRetryClick = onErrorRetryClick,
                modifier = Modifier.fillMaxSize()
            )
            Status.LOADING -> ProgressBar()
            else -> {}
        }
    }
}

@Composable
fun PropertyListComposable(
    properties: List<PropertyEntity>,
    onItemClick: (PropertyEntity) -> Unit,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(contentPadding = paddingValues) {
        items(
            items = properties,
            key = { it.id }
        ) {
            PropertyItemComposable(
                property = it,
                onClick = { onItemClick(it) }
            )
        }
    }
}

@Composable
fun PropertyItemComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    property: PropertyEntity
) {
    Card(
        onClick = { onClick() },
        modifier = modifier.padding(15.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        AsyncImage(
            model = property.propertyImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(25.dp)),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        PropertyContent(
            modifier = Modifier.padding(horizontal = 15.dp),
            property = property
        )
    }
}

@Composable
fun ListTopAppBar() {
    TopAppBar(title = {
        Text(
            text = "Property App",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold
        )
    })
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewListTopAppBar() = ListTopAppBar()

@Preview(showBackground = true)
@Composable
fun PreviewPropertyItemComposable() = PropertyItemComposable(
    onClick = {},
    property = PropertyEntity.DEFAULT
)