package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel

fun CourseUiModel.toCourse(): Course {
    return Course(
        id = this.id,
        courseName = this.courseName,
        courseDescription = this.courseDescription,
    )

}