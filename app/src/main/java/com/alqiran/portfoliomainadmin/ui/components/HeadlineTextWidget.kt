package com.alqiran.portfoliomainadmin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme

@Composable
fun HeadlineTextWidget(
    text: String,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.wrapContentWidth()
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(lineColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
    PortfolioMainTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            HeadlineTextWidget(text = "Section Title")
            Spacer(modifier = Modifier.height(16.dp))
            HeadlineTextWidget(text = "A Very Long Section Title That Should Ellipsize Instead of Wrapping")
        }
    }
}
