package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Certificate
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import kotlin.String

fun List<CertificateUiModel>.toCertificates(): List<Certificate> {
    return this.map { item ->
        Certificate(
            id = item.id,
            certificateName = item.certificateName,
            imageUrl = item.imageUrl,
            description = item.description,
        )
    }
}