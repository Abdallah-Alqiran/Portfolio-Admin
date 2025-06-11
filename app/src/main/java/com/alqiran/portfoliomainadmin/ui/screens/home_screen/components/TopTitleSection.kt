package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .shadow(
                    elevation = 30.dp,
                    shape = CircleShape,
                    ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    spotColor = MaterialTheme.colorScheme.primary
                )
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
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
                placeholder = painterResource(id = R.drawable.profile),
                error = painterResource(id = R.drawable.ic_failed)
            )
        }

        Text(
            text = userName,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = jobTitle,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.tertiary
        )

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            accounts?.forEach { item ->
                val imageRes: Int? = when (item.webName) {
                    "facebook" -> R.drawable.ic_facebook
                    "linkedin" -> R.drawable.ic_linkedin
                    "whatsapp" -> R.drawable.ic_whatsapp
                    "instagram" -> R.drawable.ic_instagram
                    "x" -> R.drawable.ic_x
                    else -> {
                        null
                    }
                }
                if (imageRes != null) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "icon image",
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .height(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f))
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


        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            accounts?.forEach { item ->
                val imageRes: Int? = when (item.webName) {
                    "github" -> R.drawable.ic_github
                    "codeforces" -> R.drawable.codeforces
                    "leetcode" -> R.drawable.leetcode
                    "stackoverflow" -> R.drawable.ic_stackoverflow
                    else -> {
                        null
                    }
                }
                if (imageRes != null) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "icon image",
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f))
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