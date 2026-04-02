package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun ProjectsSection(projects: List<ProjectUiModel>, onNavigate: (NavigationAction) -> Unit) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        state = listState,
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            Column(
                modifier = Modifier
                    .width(260.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                    .padding(bottom = 12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data(project.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Project Image",
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(id = R.drawable.ic_loading_project),
                        error = painterResource(id = R.drawable.ic_failed),
                        contentScale = ContentScale.Crop,
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = project.projectName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Box(
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    DefaultButton(
                        "Details",
                        buttonType = ButtonType.ScreenNavigation(
                            navigationAction = NavigationAction.ToProject(project),
                            onNavigate = onNavigate
                        )
                    )
                }
            }
        }
    }
}