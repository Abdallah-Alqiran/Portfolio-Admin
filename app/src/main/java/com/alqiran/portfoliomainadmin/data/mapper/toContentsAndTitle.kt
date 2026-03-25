package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Content
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContentTitle
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel

fun List<ContentTitleUiModel>.toContentsAndTitle(): List<ContentTitle> {
    return this.map { item ->
        ContentTitle(
        id = item.id,
        contentTitle = item.contentTitle,
        contents = item.contents.map { content ->
            Content(
                id = content.id,
                contentDescription = content.contentDescription,
                contentUrl = content.contentUrl
            )
        },
        )
    }
}