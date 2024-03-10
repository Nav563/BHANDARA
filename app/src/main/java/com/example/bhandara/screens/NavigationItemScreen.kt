package com.example.bhandara.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bhandara.R
import com.example.bhandara.components.SpacerHeight
import com.example.bhandara.components.SpacerWidth
import com.example.bhandara.navigation.Community
import com.example.bhandara.navigation.Food
import com.example.bhandara.navigation.Home
import com.example.bhandara.navigation.Login
import com.example.bhandara.navigation.Profile
import com.example.bhandara.navigation.ShareBhandara
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NavigationItemScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header1 {
            navHostController.navigateUp()
        }
        OthersContent(navHostController)
    }
}

@Composable
fun Header1(onBack: () -> Unit) {
    Column {
        IconButton(
            onClick = onBack,
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                Icons.Default.ArrowBack, contentDescription = "", tint = Color.Black,
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 15.dp, start = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.man), contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
            )
            SpacerWidth(15.dp)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Name",
                    fontSize = 28.sp,
                    fontWeight = W700
                )
                SpacerHeight(10.dp)
                Text(
                    text = "Phone",
                    fontSize = 20.sp,
                    fontWeight = W400
                )
            }
        }
    }

}

@Composable
fun OthersContent(navController: NavHostController) {
    val firebaseAuth = remember {
        mutableStateOf(FirebaseAuth.getInstance())
    }
    val context = LocalContext.current

    val button = listOf(
        ButtonsInfo(Icons.Default.Home, R.string.home) {
            navController.navigate(Home)
        },
        ButtonsInfo(Icons.Default.AccountCircle, R.string.user_profile) {
            navController.navigate(Profile)
        },
        ButtonsInfo(Icons.Default.AddCircle, R.string.daily_bhandara_post) {
            navController.navigate(Food)
        },
        ButtonsInfo(Icons.Default.List, R.string.my_booked_chef_list) { /*TODO*/ },
        ButtonsInfo(Icons.Default.Share, R.string.share_with_friends) {
            navController.navigate(ShareBhandara)
        },
        ButtonsInfo(Icons.Default.Send, R.string.send) {
            navController.navigate(Community)
        },
        ButtonsInfo(Icons.Default.Info, R.string.contact_bhandara_app_support) { /*TODO*/ },
        ButtonsInfo(Icons.Default.Star, R.string.rate_bhandara_app) { /*TODO*/ },
        ButtonsInfo(Icons.Default.ExitToApp, R.string.logout) {
            firebaseAuth.value.signOut()
            //Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
            navController.navigate(Login)
        }
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = 20.dp, start = 10.dp)
    ) {
        button.forEach { buttonsInfo ->
            TextButton(
                onClick = { buttonsInfo.onClick() },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = buttonsInfo.icon, contentDescription = "",
                    modifier = Modifier,
                    colorResource(id = R.color.black)
                )
                SpacerWidth()
                Text(
                    text = stringResource(id = buttonsInfo.labelResId),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = W400
                )
            }
        }
        // App version text
        Text(
            text = stringResource(id = R.string.app_version),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 120.dp),
            fontSize = 18.sp,
            fontWeight = W400, color = Color.LightGray
        )
    }
}

data class ButtonsInfo(val icon: ImageVector, val labelResId: Int, val onClick: () -> Unit)