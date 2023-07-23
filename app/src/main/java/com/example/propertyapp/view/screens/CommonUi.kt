package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.propertyapp.R
import com.example.propertyapp.domain.model.PropertyEntity

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun GenericErrorComposable(
    onErrorRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.baseline_error_24),
            contentDescription = "error icon",
            tint = MaterialTheme.colorScheme.outline
        )
        Text(
            text = "Oops!",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        TextButton(onClick = onErrorRetryClick) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun PropertyContent(
    modifier: Modifier = Modifier,
    property: PropertyEntity,
    isDetailView: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)
        ) {
            PropertyInformation(
                modifier = Modifier.weight(0.4f),
                property = property,
                isDetailView = isDetailView
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
    property: PropertyEntity,
    isDetailView: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(5.dp)
    ) {
        if (isDetailView) {
            // Display price
            Text(
                text = property.propertyPrice,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                modifier = Modifier.padding(5.dp)
            )
            // Auction date
            Text(
                text = "Auction date: ${property.auctionDate}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                modifier = Modifier.padding(5.dp)
            )
        }
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
        if (isDetailView) {
            // Property Type
            Text(
                text = "Property type: ${property.propertyType}",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(5.dp)
            )
            // Description
            Text(
                text = property.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(5.dp)
            )
        }
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

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() = GenericErrorComposable(
    modifier = Modifier.wrapContentSize(),
    onErrorRetryClick = {}
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyContentDetail() = PropertyContent(
    property = PropertyEntity.DEFAULT,
    isDetailView = true
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyContentDefault() = PropertyContent(
    property = PropertyEntity.DEFAULT,
    isDetailView = false
)

@Preview(showBackground = true)
@Composable
fun PreviewAgentProfile() = AgentProfile(
    agentName = "Agent Name",
    agentAvatarUrl = ""
)

@Preview(showBackground = true)
@Composable
fun PreviewRoomSpaceQuantity() = RoomSpaceQuantityRow(
    bedroomCount = 3,
    bathroomCount = 2,
    carspaceCount = 1
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyInformation() = PropertyInformation(property = PropertyEntity.DEFAULT)