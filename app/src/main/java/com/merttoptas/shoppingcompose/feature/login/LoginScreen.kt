package com.merttoptas.shoppingcompose.feature.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.merttoptas.shoppingcompose.R
import com.merttoptas.shoppingcompose.ui.theme.ShoppingComposeTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * Created by mertcantoptas on 29.05.2023
 */

@Composable
internal fun LoginScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val token = stringResource(id = R.string.default_web_client_id)
    var user by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser) }
    val context = LocalContext.current
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
            Toast.makeText(context, "Welcome ${user?.displayName}", Toast.LENGTH_SHORT).show()
        },
        onError = {
            user = null
            Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
        }
    )

    LoginScreen(
        modifier = modifier, loginUiState = loginUiState,
        onEmailValueChange = viewModel::onEmailChange,
        onPasswordValueChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLogin,
        onGoogleSignInClick = {
            val googleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(context, googleSignIn)
            launcher.launch(client.signInIntent)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginUiState: LoginUiState,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleSignInClick: () -> Unit
) {
    Scaffold {
        Content(
            modifier = modifier.padding(it),
            loginUiState = loginUiState,
            onEmailValueChange = onEmailValueChange,
            onPasswordValueChange = onPasswordValueChange,
            onLoginClick = onLoginClick,
            onGoogleSignInClick = onGoogleSignInClick
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier, loginUiState: LoginUiState,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleSignInClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = loginUiState.loading) {
            CircularProgressIndicator()
        }
        Text(text = "Login Screen")
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            label = {
                Text(text = "Email")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email"
                )
            },

            value = loginUiState.email,
            onValueChange = onEmailValueChange
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            value = loginUiState.password,
            onValueChange = onPasswordValueChange
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onLoginClick) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(onClick = onGoogleSignInClick) {
            Text(text = "Google Sign In")
        }

    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onError: (Exception) -> Unit
): ManagedActivityResultLauncher<Intent, androidx.activity.result.ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account =
                task.getResult(ApiException::class.java) ?: throw Exception("Google Sign In Failed")
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            scope.launch {
                val authResult = FirebaseAuth.getInstance().signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ShoppingComposeTheme {
        Content(
            loginUiState = LoginUiState(password = "123", email = "compose"),
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onGoogleSignInClick = {}, onLoginClick = {})
    }
}