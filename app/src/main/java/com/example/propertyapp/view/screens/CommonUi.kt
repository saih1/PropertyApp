package com.example.propertyapp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.propertyapp.R

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
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
        TextButton(onClick = onRetryClick) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() = GenericErrorComposable {}