@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.propertyapp.R
import com.example.propertyapp.common.Status
import com.example.propertyapp.domain.model.PropertyEntity
import com.example.propertyapp.view.PropertyViewModel

@Composable
fun PropertyListScreen(
    vm: PropertyViewModel,
    onItemClick: (PropertyEntity) -> Unit,
    onErrorRetryClick: () -> Unit,
    onRefreshClick: () -> Unit
) {
    val properties by vm.properties.collectAsState()
    Scaffold(
        topBar = {
            ListTopAppBar(
                onRefreshClick = onRefreshClick
            )
        }
    ) { paddingValues ->
        when (properties.status) {
            Status.SUCCESS -> {
                PropertyListComposable(
                    properties = properties.data ?: emptyList(),
                    onItemClick = { onItemClick(it) },
                    paddingValues = paddingValues
                )
            }
            Status.ERROR -> {
                GenericErrorComposable(
                    onErrorRetryClick = onErrorRetryClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Status.LOADING -> {
                ProgressBar(
                    modifier = Modifier.fillMaxSize()
                )
            }
            Status.IDLE -> {
                // Do nothing
            }
        }
    }
}

@Composable
fun PropertyListComposable(
    properties: List<PropertyEntity>,
    onItemClick: (PropertyEntity) -> Unit,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
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
        modifier = modifier
    ) {
        AsyncImage(
            model = property.propertyImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = CardDefaults.shape)
                .aspectRatio(ratio = 16 / 9f)
                .fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        PropertyContent(
            property = property,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun ListTopAppBar(
    onRefreshClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(
                onClick = onRefreshClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "refresh icon"
                )
            }
        }
    )
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewListTopAppBar() = ListTopAppBar {}

@Preview(showBackground = true)
@Composable
fun PreviewPropertyItemComposable() = PropertyItemComposable(
    onClick = {},
    property = PropertyEntity.DEFAULT
)