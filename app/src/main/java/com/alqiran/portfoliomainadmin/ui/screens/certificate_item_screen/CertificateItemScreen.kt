package com.alqiran.portfoliomainadmin.ui.screens.certificate_item_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.ui.components.HeadlineTextWidget
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType


@Composable
fun CertificateItemScreen(certificate: CertificateUiModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(certificate.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "certificate Image",
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.ic_loading_project),
                error = painterResource(id = R.drawable.ic_failed),
                contentScale = ContentScale.FillBounds,
            )
        }

        Box(Modifier.padding(8.dp))

        Text(
            text = certificate.certificateName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

        HeadlineTextWidget("Description")

        Text(
            certificate.description,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}