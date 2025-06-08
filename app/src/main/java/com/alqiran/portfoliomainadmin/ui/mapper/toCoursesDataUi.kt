package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel

fun List<Course>.toCoursesDataUi(): List<CourseUiModel> {
    return this.map { item ->
        CourseUiModel(
            id = item.id,
            courseName = item.courseName,
            courseDescription = item.courseDescription
        )
    }
}