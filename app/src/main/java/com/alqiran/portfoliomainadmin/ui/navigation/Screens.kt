package com.alqiran.portfoliomainadmin.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data object SplashScreenRoute

@Serializable
data object HomeScreenRoute

@Serializable
data class ProjectItemRoute(val project: ProjectUiModel)

@Serializable
data class ProjectsScreenRoute(val projects: List<ProjectUiModel>)

@Serializable
data class CoursesScreenRoute(val courses: List<CourseUiModel>)

@Serializable
data object MessageScreenRoute

object CustomNavType {
    val projectItemType = object : NavType<ProjectUiModel>(
        isNullableAllowed = false
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): ProjectUiModel? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): ProjectUiModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: ProjectUiModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: ProjectUiModel
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val projectsType = object : NavType<List<ProjectUiModel>>(
        isNullableAllowed = false
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<ProjectUiModel>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<ProjectUiModel> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<ProjectUiModel>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<ProjectUiModel>
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val coursesType = object : NavType<List<CourseUiModel>>(
        isNullableAllowed = false
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<CourseUiModel>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<CourseUiModel> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<CourseUiModel>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<CourseUiModel>
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }


}