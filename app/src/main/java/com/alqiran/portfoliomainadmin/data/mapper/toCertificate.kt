package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Certificate
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import kotlin.String

fun CertificateUiModel.toCertificate(): Certificate {
    return Certificate(
        id = this.id,
        certificateName = this.certificateName,
        imageUrl = this.imageUrl,
        description = this.description,
    )

}