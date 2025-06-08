package com.alqiran.portfoliomainadmin.ui.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onClick:() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (title != "Home") {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "back",
                modifier = Modifier
                    .size(27.dp)
                    .clip(CircleShape)
                    .clickable { onClick() },
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge
            )
        }

        if (title != "Home") {
            Spacer(modifier = Modifier.width(43.dp))
        }
    }
}

@Preview
@Composable
private fun Prev() {
    PortfolioMainTheme {
        TopBar("Home", onClick = {})
    }
}