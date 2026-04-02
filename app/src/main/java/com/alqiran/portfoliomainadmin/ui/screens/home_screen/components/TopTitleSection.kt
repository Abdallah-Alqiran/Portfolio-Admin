package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.ui.helper.isValidUrl
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel

@Composable
fun TopTitleSection(
    userName: String,
    userImage: String?,
    jobTitle: String,
    accounts: List<ContactAndAccountsUiModel>?,
    context: Context,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp) 
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.outline) 
                .padding(2.dp) 
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(userImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
                placeholder = painterResource(id = R.drawable.profile),
                error = painterResource(id = R.drawable.ic_failed)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = userName,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = jobTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (accounts != null && accounts.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                accounts.forEach { item ->
                    val imageRes: Int? = getSocialIcon(item.webName)
                    if (imageRes != null) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = item.webName,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    if (item.url.isValidUrl()) {
                                        val intent = Intent(Intent.ACTION_VIEW, item.url.toUri())
                                        context.startActivity(intent)
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}

private fun getSocialIcon(name: String): Int? {
    return when (name.lowercase()) {
        "facebook" -> R.drawable.ic_facebook
        "linkedin" -> R.drawable.ic_linkedin
        "whatsapp" -> R.drawable.ic_whatsapp
        "instagram" -> R.drawable.ic_instagram
        "x" -> R.drawable.ic_x
        "github" -> R.drawable.ic_github
        "codeforces" -> R.drawable.codeforces
        "leetcode" -> R.drawable.leetcode
        "stackoverflow" -> R.drawable.ic_stackoverflow
        "notion" -> R.drawable.ic_notion
        else -> null
    }
}