package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.runtime.Composable
import com.alqiran.portfoliomainadmin.ui.components.TwoItemsPerRow
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel

@Composable
fun SkillsSection(skills: List<SkillUiModel>) {
    TwoItemsPerRow(skills.map { it.skillName })
}