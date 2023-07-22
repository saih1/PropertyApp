@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.propertyapp.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.propertyapp.R

@Composable
fun PropertyInformation() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(5.dp)
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
    agentName: String, agentAvatar: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "profile image",
                modifier = Modifier.clip(CircleShape)
            )
        }
        Text(
            text = agentName, textAlign = TextAlign.Center
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
fun PreviewAgentProfile() = AgentProfile(agentName = "John Smith", agentAvatar = "")

@Preview(showBackground = true)
@Composable
fun PreviewPropertyInformation() = PropertyInformation()