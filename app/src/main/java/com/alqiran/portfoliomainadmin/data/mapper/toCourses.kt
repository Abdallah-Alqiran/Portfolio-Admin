package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel

fun List<CourseUiModel>.toCourses(): List<Course> {
    return this.map { item ->
        Course(
            id = item.id,
            courseName = item.courseName,
            courseDescription = item.courseDescription
        )
    }
}