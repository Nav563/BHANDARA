package com.example.bhandara.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bhandara.R
import com.example.bhandara.navigation.Food
import com.example.bhandara.navigation.Home
import com.example.bhandara.navigation.Login
import com.example.bhandara.navigation.SignUp
import com.example.bhandara.viewmodels.AuthViewModel
import com.example.bhandara.viewmodels.SignInViewModel
import com.example.bhandara.viewmodels.SignUpViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val isError = remember { mutableStateOf(false) }
    var isPasswordCorrect = remember {
        mutableStateOf(true)
    }
    var check = remember {
        mutableStateOf(false)
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,

        ) {
        Image(
            painter = painterResource(id = R.drawable.sign_in), contentDescription = "Signin",
            modifier = Modifier
                .fillMaxWidth(1F)
                .size(200.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(1F)
                .background(Color.White),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google",
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Sign in with Google",
                modifier = Modifier,
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(1F)
                .background(Color.White)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.17F)
                    .fillMaxHeight(.01F)
                    .background(
                        Color(0xFF000000)
                    ),
            )
            Text(
                text = "Or Continue with",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(.77F),
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.9F)
                    .fillMaxHeight(.01F)
                    .background(
                        Color(0xFF000000)
                    )
            )
        }
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
                .padding(top = 10.dp)
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(checked = check.value,
                onCheckedChange = {
                    check.value = it
                })
            Text(text = "Show Password", fontSize = 18.sp, color = Color.Black)
        }
        OutlinedButton(
            onClick = {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
                } else if (password.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
                } else if (isPasswordCorrect.value) {
                    scope.launch {
                        viewModel.loginUser(email.toString(), password.toString())
                        navHostController.navigate(Home)
                    }
                } else {
                    Toast.makeText(context, "Password is incorrect!", Toast.LENGTH_SHORT).show()
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RectangleShape)
        ) {
            Text(
                text = "SIGNIN",
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Forgot Password?", fontSize = 18.sp,
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(.43F)
                    .fillMaxHeight(.01F)
                    .background(
                        Color(0xFF000000)
                    )
            )
        }
        Row(modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                navHostController.navigate(SignUp)
            }
            .clip(RoundedCornerShape(6.dp))
        ) {
            Text(text = "Not a member?", fontSize = 18.sp)
            Text(
                text = "Create an account", fontSize = 18.sp, color = Color.Blue,
                modifier = Modifier
            )
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
