package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun CertificatesSection(certificates: List<CertificateUiModel>, onNavigate: (NavigationAction) -> Unit) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier
            .padding(vertical = 24.dp),
        state = listState,
        horizontalArrangement = Arrangement.Center
    ) {
        items(certificates) { certificate ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .width(240.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(bottom = 8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .width(240.dp)
                        .aspectRatio(16f / 9f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data(certificate.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Project Image",
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(id = R.drawable.ic_loading_project),
                        error = painterResource(id = R.drawable.ic_failed),
                        contentScale = ContentScale.FillBounds,
                    )
                }
                Text(
                    text = certificate.certificateName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier.width(240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DefaultButton(
                        "Show Details",
                        buttonType = ButtonType.ScreenNavigation(
                            navigationAction = NavigationAction.ToCertificate(certificate),
                            onNavigate = onNavigate
                        )
                    )
                }
            }
        }
    }
}