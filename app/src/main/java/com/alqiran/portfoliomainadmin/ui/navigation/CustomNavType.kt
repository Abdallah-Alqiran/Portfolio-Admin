package com.alqiran.portfoliomainadmin.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

    val topTitleAdminType = object: NavType<List<ContactAndAccountsUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<ContactAndAccountsUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<ContactAndAccountsUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<ContactAndAccountsUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<ContactAndAccountsUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val educationAdminType = object: NavType<List<EducationUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<EducationUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<EducationUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<EducationUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<EducationUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val technologiesAndToolsAdminType = object: NavType<List<TechnologyTitleUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<TechnologyTitleUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<TechnologyTitleUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<TechnologyTitleUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<TechnologyTitleUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val technologyAdminType = object: NavType<List<TechnologyUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<TechnologyUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<TechnologyUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<TechnologyUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<TechnologyUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }


    val skillsAdminType = object: NavType<List<SkillUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<SkillUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<SkillUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<SkillUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<SkillUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val projectsAdminType = object: NavType<List<ProjectUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<ProjectUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<ProjectUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<ProjectUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<ProjectUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }


    val coursesAdminType = object: NavType<List<CourseUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<CourseUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<CourseUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<CourseUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<CourseUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }


    val experienceAdminType = object: NavType<List<ExperienceUiModel>?>(
        isNullableAllowed = true
    ) {
        override fun get(
            bundle: Bundle,
            key: String
        ): List<ExperienceUiModel>? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): List<ExperienceUiModel>? {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<ExperienceUiModel>?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<ExperienceUiModel>?
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }



}