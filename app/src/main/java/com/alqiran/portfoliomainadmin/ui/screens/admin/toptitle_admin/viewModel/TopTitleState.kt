package com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel

sealed class TopTitleState {

    object None: TopTitleState()
    object Loading: TopTitleState()

    object Success: TopTitleState()

    data class Error(val error: String): TopTitleState()

}