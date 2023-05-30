package com.merttoptas.shoppingcompose.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mertcantoptas on 30.05.2023
 */

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState())
    val uiState = _uiState

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            delay(500)
            // check firebase current user
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                _uiState.value = _uiState.value.copy(navigateToHome = true)
            } else {
                _uiState.value = _uiState.value.copy(navigateToLogin = true)
            }
        }
    }
}

data class SplashUiState(
    val loading: Boolean = false,
    val navigateToHome: Boolean = false,
    val navigateToLogin: Boolean = false
)