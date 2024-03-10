package com.example.bhandara.screens

import android.text.InputType
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.bhandara.R
import com.example.bhandara.navigation.Login
import com.example.bhandara.viewmodels.SignUpViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScren(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    //val navigator = LocalNavigator.currentOrThrow
    val isError = remember { mutableStateOf(false) }

    val name = rememberSaveable {
        mutableStateOf("")
    }
    val mobile = rememberSaveable {
        mutableStateOf("")
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sign_up), contentDescription = "signup",
            modifier = Modifier
                .fillMaxWidth(1F)
                .size(200.dp)
        )
        Column(modifier = Modifier.padding(24.dp)) {
            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "name")
                },
                placeholder = {
                    Text(text = "Name")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = mobile.value,
                onValueChange = {
                    mobile.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Phone, contentDescription = "mobile")
                },
                placeholder = {
                    Text(text = "Mobile")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            )
            OutlinedTextField(
                value = email.value,
                textStyle = if (isError.value) {
                    LocalTextStyle.current.copy(color = Color.Red)
                } else {
                    LocalTextStyle.current
                },
                onValueChange = {
                    email.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon")
                },
                placeholder = {
                    Text(text = "Email")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "password")
                },
                placeholder = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(checked = false,
                    onCheckedChange = {
                        //InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    })
                Text(text = "Show Password", fontSize = 18.sp, color = Color.Black)
            }
            OutlinedButton(
                onClick = {
                    if (name.value.isEmpty() || mobile.value.isEmpty()
                        || email.value.isEmpty() || password.value.isEmpty()){
                        Toast.makeText(context, "Please Enter All Fields", Toast.LENGTH_SHORT).show()
                    }else{
                        scope.launch {
                            viewModel.registerUser(email.toString(), password.toString())
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "REGISTER",
                    color = Color.Black,
                )
            }
            Row(modifier = Modifier
                .padding(top = 10.dp)
                .clickable {
                    navController.navigate(Login)
                }) {
                Text(
                    text = "Already have an account",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = "Sign in", fontSize = 18.sp, color = Color.Blue)
            }
        }
        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
                }
            }
        }
        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
