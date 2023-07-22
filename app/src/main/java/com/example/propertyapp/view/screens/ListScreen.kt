@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

@Composable
fun PropertyItemComposable(
    modifier: Modifier = Modifier, onClick: () -> Unit
    // TODO: View data as arg
) {
    Card(
        onClick = { onClick() },
        modifier = modifier.padding(15.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        AsyncImage(
            model = "https://images.pexels.com/photos/2119713/pexels-photo-2119713.jpeg",
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(25.dp)),
            // TODO: Need to replace placeholder resource
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background)
        )
        PropertyContent(modifier = Modifier.padding(horizontal = 15.dp))
    }
}

@Composable
fun PropertyContent(
    modifier: Modifier = Modifier
    //TODO: Args
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)
        ) {
            PropertyInformation(
                modifier = Modifier.weight(0.4f)
            )
            AgentProfile(
                modifier = Modifier.weight(0.1f),
                agentName = "John Smith",
                agentAvatarUrl = "https://images.pexels.com/photos/937481/pexels-photo-937481.jpeg"
            )
        }
    }
}

@Composable
fun PropertyInformation(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(5.dp)
    ) {
        // Agent company
        Text(
            text = "Ray White",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.padding(5.dp)
        )
        // Address
        Text(
            text = "206/64-68 Gladesville Road Hunter Hill, 2110",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            maxLines = 2,
            modifier = Modifier.padding(5.dp)
        )

        RoomSpaceQuantityRow(
            bedroomCount = 3, bathroomCount = 2, carspaceCount = 1
        )
    }
}

@Composable
fun RoomSpaceQuantityRow(
    bedroomCount: Int, bathroomCount: Int, carspaceCount: Int
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
            textAlign = TextAlign.Start
        )
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "one",
            modifier = Modifier.padding(2.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))

        //Bathrooms
        Text(
            text = bathroomCount.toString(),
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start
        )
        Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "one",
            modifier = Modifier.padding(2.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))

        // Car spaces
        Text(
            text = carspaceCount.toString(),
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Start
        )
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "one",
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun AgentProfile(
    agentName: String, agentAvatarUrl: String, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(5.dp)
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
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background)
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

@Preview(showBackground = true)
@Composable
fun PreviewListTopAppBar() = ListTopAppBar()

@Preview(showBackground = true)
@Composable
fun PreviewAgentProfile() = AgentProfile(
    agentName = "John Smith", agentAvatarUrl = ""
)

@Preview(showBackground = true)
@Composable
fun PreviewPropertyInformation() = PropertyInformation()

@Preview(showBackground = true)
@Composable
fun PreviewPropertyItemComposable() = PropertyItemComposable {}