package com.alqiran.portflio.ui.screens.home_screen.components

import androidx.compose.runtime.Composable
import com.alqiran.portflio.ui.components.TwoItemsPerRow
import com.alqiran.portflio.ui.model.SkillUiModel

@Composable
fun SkillsSection(skills: List<SkillUiModel>) {
    TwoItemsPerRow(skills.map { it.skillName })
}