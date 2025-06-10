package com.alqiran.portfoliomainadmin.ui.screens.admin.courses_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.courses_admin.viewModel.CoursesAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun CoursesAdminScreen(allCourses: List<CourseUiModel>?) {

    val coursesAdminViewModel: CoursesAdminViewModel = hiltViewModel()
    val coursesState by coursesAdminViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (coursesState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (coursesState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            coursesAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            coursesAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var courses by remember { mutableStateOf(allCourses) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(courses?: emptyList()) { course ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = course.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter course id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: course.id
                    courses = courses?.map { a ->
                        if (a == course) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = course.courseName,
                    textLabel = "Course Name",
                    placeHolderLabel = "Enter Course Name",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    courses = courses?.map { a ->
                        if (a == course) a.copy(courseName = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = course.courseDescription,
                textLabel = "Course Description",
                placeHolderLabel = "Enter Course Description"
            ) {
                courses = courses?.map { a ->
                    if (a == course) a.copy(courseDescription = it) else a
                }
            }
            DeleteItemTextButton {
                coursesAdminViewModel.deleteCourse(course)
                courses = courses !!- course
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                courses = (courses?: emptyList()) + CourseUiModel(id = (courses?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Courses",
                buttonType = ButtonType.UploadOnClick {
                    coursesAdminViewModel.uploadEducationData(courses?: emptyList())
                }
            )
        }
    }
}