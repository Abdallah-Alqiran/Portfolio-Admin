//package com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.MainDispatcherRule
//import com.alqiran.portfoliomainadmin.repository.FirebaseRepositoryTesting
//import com.alqiran.portfoliomainadmin.ui.model.UserUiModel
//import com.google.common.truth.Truth.assertThat
//import junit.framework.TestCase.assertEquals
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class UserViewModelTest {
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @get:Rule
//    var mainDispatcherRule = MainDispatcherRule()
//
//    private lateinit var viewModel: UserViewModel
//
//    @Before
//    fun setup() {
//        viewModel = UserViewModel(FirebaseRepositoryTesting())
//    }
//
//
//    @Test
//    fun getAllUserDataReturnsData() = runTest {
//        assertEquals(UserState.None, viewModel.userState.value)
//        viewModel.fetchUserData()
//        advanceUntilIdle()
//        val userData = UserUiModel(
//            userName = "Abdallah"
//        )
//        assertThat(viewModel.userState.value).isEqualTo(UserState.Success(userData))
//    }
//
//}