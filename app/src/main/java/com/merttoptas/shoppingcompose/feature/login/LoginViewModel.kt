package com.merttoptas.shoppingcompose.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merttoptas.shoppingcompose.core.common.Resource
import com.merttoptas.shoppingcompose.core.common.asResource
import com.merttoptas.shoppingcompose.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mertcantoptas on 29.05.2023
 */

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState = _uiState

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onLogin() {
        viewModelScope.launch {
            loginUseCase(_uiState.value.email, _uiState.value.password)
                .asResource()
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(loading = true)
                        }

                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(loading = false)
                        }

                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(loading = false)
                        }
                    }
                }.launchIn(this)
        }
    }
}

data class LoginUiState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = ""
)