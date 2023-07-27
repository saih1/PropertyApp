package com.example.propertyapp.view.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.propertyapp.R
import com.example.propertyapp.domain.model.PropertyEntity

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun GenericErrorComposable(
    onErrorRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 150.dp
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = R.drawable.baseline_error_24),
            contentDescription = "error icon",
            tint = MaterialTheme.colorScheme.outline
        )
        Text(
            text = "Oops! Something isn't right.",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
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
    isExpended: Boolean = false
) {
    Row(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            PropertyInformation(
                modifier = Modifier.weight(0.4f),
                property = property,
                isExpended = isExpended
            )
            AgentProfile(
                modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.Top),
                agentName = property.agentName,
                agentAvatarUrl = property.agentAvatar
            )
        }
    }
}

@Composable
fun PropertyInformation(
    property: PropertyEntity,
    modifier: Modifier = Modifier,
    isExpended: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Agent company
        Text(
            text = property.agentCompany,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1
        )
        // Address
        Text(
            text = property.propertyAddress,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 2,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.height(
                // Height is set to (line size * 2)
                // to stay consistent for both 1 line or 2 lines
                MaterialTheme.typography.labelLarge.lineHeight.value.dp * 2
            )
        )
        // Bedroom, Bathroom, Car space
        RoomSpaceQuantityRow(
            bedroomCount = property.bedroomCount,
            bathroomCount = property.bathroomCount,
            carspaceCount = property.carspaceCount
        )
        // Expended
        if (isExpended) {
            // Price
            Text(
                text = property.propertyPrice,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            // Auction date
            Text(
                text = "Auction date: ${property.auctionDate}",
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
            // Property type
            Text(
                text = "Property type: ${property.propertyType}",
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
            // Description
            Text(
                text = property.description,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun RoomSpaceQuantityRow(
    bedroomCount: Int,
    bathroomCount: Int,
    carspaceCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Bedrooms
        CustomPropertyIconText(
            text = bedroomCount.toString(),
            icon = R.drawable.round_bed_24
        )
        // Bathrooms
        CustomPropertyIconText(
            text = bathroomCount.toString(),
            icon = R.drawable.round_bathtub_24
        )
        // Carspaces
        CustomPropertyIconText(
            text = carspaceCount.toString(),
            icon = R.drawable.round_car_24
        )
    }
}

@Composable
fun AgentProfile(
    agentName: String,
    agentAvatarUrl: String,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 60.dp,
    horizontalAlignment: Alignment.Horizontal = Alignment.End
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .size(avatarSize)
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
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = agentName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun CustomPropertyIconText(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    iconSize: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "property icon",
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewGenericError() = GenericErrorComposable(
    onErrorRetryClick = {}
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyInformation() = PropertyInformation(
    property = PropertyEntity.DEFAULT,
    modifier = Modifier.padding(10.dp)
)

@Preview(showBackground = true)
@Composable
fun PreviewExpandedPrpoertyInformation() = PropertyInformation(
    property = PropertyEntity.DEFAULT,
    isExpended = true,
    modifier = Modifier.padding(10.dp)
)

@Preview(showBackground = true)
@Composable
fun PreviewExpandedPropertyContent() = PropertyContent(
    property = PropertyEntity.DEFAULT,
    isExpended = true,
    modifier = Modifier.padding(10.dp)
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyContent() = PropertyContent(
    property = PropertyEntity.DEFAULT,
    modifier = Modifier.padding(10.dp)
)

@Preview(showBackground = true)
@Composable
fun PreviewAgentProfile() = AgentProfile(
    agentName = "Agent Name",
    agentAvatarUrl = ""
)