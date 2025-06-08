package com.alqiran.portfoliomainadmin.ui.components.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.R


@Composable
fun BottomBar(onItemSelected: (Int) -> Unit, selectedIndex: Int) {
    val items: List<Pair<Int, String>> = listOf(
        R.drawable.profile to "Home",
        R.drawable.ic_project to "Projects",
        R.drawable.ic_courses to "Courses",
        R.drawable.ic_message to "Message"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val tintColor = if (index == selectedIndex) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                IconButton(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = { onItemSelected(index) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            tint = tintColor,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = item.second,
                            style = MaterialTheme.typography.labelSmall,
                            color = tintColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}