package com.alqiran.portfoliomainadmin.ui.utils

import android.content.Context
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction

sealed class ButtonType {

    data class IntentNavigation(val url: String, val context: Context): ButtonType()

    data class ScreenNavigation(val onNavigate: (NavigationAction) -> Unit, val navigationAction: NavigationAction): ButtonType()

    data class UploadOnClick(val onUpload:()->Unit): ButtonType()

}