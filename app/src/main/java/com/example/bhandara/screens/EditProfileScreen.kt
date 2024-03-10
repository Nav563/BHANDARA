package com.example.bhandara.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bhandara.R
import com.example.bhandara.navigation.Login
import com.example.bhandara.ui.theme.LightOrange

@Composable
fun EditProfileScreen(navHostController: NavHostController) {

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = LightOrange)){
        TopHeaders {
            navHostController.navigateUp()
        }
        TextColumn()
    }
}

@Composable
fun TextColumn() {
    val name = remember {
        mutableStateOf("")
    }
    val mobile = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val profession = remember {
        mutableStateOf("")
    }
    val bio = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 40.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.man), contentDescription = "",
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = name.value,
            textStyle = LocalTextStyle.current,

            onValueChange = {
                name.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "name")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.name))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
        OutlinedTextField(
            value = mobile.value,
            textStyle = LocalTextStyle.current,

            onValueChange = {
                mobile.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Phone, contentDescription = "phone")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.mobile))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        OutlinedTextField(
            value = email.value,
            textStyle = LocalTextStyle.current,

            onValueChange = {
                email.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
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
            value = profession.value,
            textStyle = LocalTextStyle.current,

            onValueChange = {
                profession.value = it
            },
            leadingIcon = {
                Icon(painterResource(id = R.drawable.book), contentDescription = "profession")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.profession))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        OutlinedTextField(
            value = bio.value,
            textStyle = LocalTextStyle.current,

            onValueChange = {
                bio.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "bio")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.bio))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                //keyboardType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}


@Composable
fun TopHeaders(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightOrange)
            .size(60.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "", tint = Color.Black, modifier = Modifier.size(30.dp))
        }

        Text(
            text = "SAVE",
            modifier = Modifier
                .padding(end = 10.dp),
            fontSize = 25.sp
        )
    }
}