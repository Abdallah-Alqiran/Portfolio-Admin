package com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alqiran.portfoliomainadmin.repository.FirebaseRepositoryTesting
import org.junit.Before
import org.junit.Rule

class UserViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        viewModel = UserViewModel(FirebaseRepositoryTesting())
    }

}