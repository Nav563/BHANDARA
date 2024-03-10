package com.example.bhandara.viewpager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.bhandara.R
import com.example.bhandara.data.Pager
import com.example.bhandara.data.dataList
import com.example.bhandara.navigation.Login
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    //val navigator = LocalNavigator.currentOrThrow
    val pageState = rememberPagerState()
    Column {
        HorizontalPager(
            count = dataList.size,
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) { page ->
            PageUi(pager = dataList[page])
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = R.color.sky)
        )
        AnimatedVisibility(visible = pageState.currentPage == 2) {
            Button(onClick = {
                //navigator.push(LoginScreen)
                             navController.navigate(Login)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Getting Started", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}

@Composable
fun PageUi(pager: Pager) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = pager.image),
            contentDescription = pager.des,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = pager.des)
    }
}


