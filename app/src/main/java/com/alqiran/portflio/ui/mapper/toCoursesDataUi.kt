package com.alqiran.portflio.ui.mapper

import com.alqiran.portflio.data.datasourses.remote.model.Course
import com.alqiran.portflio.ui.model.CourseUiModel

fun List<Course>.toCoursesDataUi(): List<CourseUiModel> {
    return this.map { item ->
        CourseUiModel(
            id = item.id,
            courseName = item.courseName,
            courseDescription = item.courseDescription
        )
    }
}