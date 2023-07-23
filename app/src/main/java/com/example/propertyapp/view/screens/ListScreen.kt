@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    onRetryClick: () -> Unit
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
                onRetryClick = onRetryClick
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
    paddingValues: PaddingValues
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
fun PropertyContent(
    modifier: Modifier = Modifier,
    property: PropertyEntity
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)
        ) {
            PropertyInformation(
                modifier = Modifier.weight(0.4f),
                property = property
            )
            AgentProfile(
                modifier = Modifier.weight(0.1f),
                agentName = property.agentName,
                agentAvatarUrl = property.agentAvatar
            )
        }
    }
}

@Composable
fun PropertyInformation(
    modifier: Modifier = Modifier,
    property: PropertyEntity
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(5.dp)
    ) {
        // Agent company
        Text(
            text = property.agentCompany,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.padding(5.dp)
        )
        // Address
        Text(
            text = property.propertyAddress,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            maxLines = 2,
            modifier = Modifier.padding(5.dp)
        )

        RoomSpaceQuantityRow(
            bedroomCount = property.bedroomCount,
            bathroomCount = property.bathroomCount,
            carspaceCount = property.carspaceCount
        )
    }
}

@Composable
fun RoomSpaceQuantityRow(
    bedroomCount: Int,
    bathroomCount: Int,
    carspaceCount: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
    ) {
        // Bedrooms
        Text(
            text = bedroomCount.toString(),
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelSmall
        )
        Icon(
            painter = painterResource(id = R.drawable.round_bed_24),
            contentDescription = "bedroom icon",
            modifier = Modifier
                .size(20.dp)
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))

        //Bathrooms
        Text(
            text = bathroomCount.toString(),
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelSmall
        )
        Icon(
            painter = painterResource(id = R.drawable.round_bathtub_24),
            contentDescription = "bathroom icon",
            modifier = Modifier
                .size(20.dp)
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))

        // Car spaces
        Text(
            text = carspaceCount.toString(),
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelSmall
        )
        Icon(
            painter = painterResource(id = R.drawable.round_car_24),
            contentDescription = "carspace icon",
            modifier = Modifier
                .size(20.dp)
                .padding(2.dp)
        )
    }
}

@Composable
fun AgentProfile(
    agentName: String,
    agentAvatarUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .size(60.dp)
        ) {
            AsyncImage(
                model = agentAvatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier.clip(CircleShape),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_foreground)
            )
        }
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = agentName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
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
fun PreviewAgentProfile() = AgentProfile("John Smith", "")

@Preview(showBackground = true)
@Composable
fun PreviewPropertyInformation() = PropertyInformation(
    property = PropertyEntity.DEFAULT
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyItemComposable() = PropertyItemComposable(
    onClick = { },
    property = PropertyEntity.DEFAULT,
)