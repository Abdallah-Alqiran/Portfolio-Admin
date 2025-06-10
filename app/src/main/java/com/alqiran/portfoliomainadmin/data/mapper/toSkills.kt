package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel

fun List<SkillUiModel>.toSkills(): List<Skill> {
    return this.map { item ->
        Skill(
            id = item.id,
            skillName = item.skillName
        )
    }
}