package com.example.bhandara.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bhandara.R
import com.example.bhandara.components.SpacerHeight
import com.example.bhandara.data.Category
import com.example.bhandara.data.categoryList
import com.example.bhandara.models.Bhandara
import com.example.bhandara.navigation.EditProfile
import com.example.bhandara.navigation.Food
import com.example.bhandara.navigation.Home
import com.example.bhandara.navigation.Login
import com.example.bhandara.navigation.NavigationItem
import com.example.bhandara.navigation.Profile
import com.example.bhandara.navigation.ShareBhandara
import com.example.bhandara.ui.theme.Yellow_1
import com.example.bhandara.viewmodels.RecentBhandaraViewModel
import com.example.bhandara.viewmodels.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController) {

    //val currentLocation by locationViewModel.currentLocation.collectAsState()
    //val currentLocation by rememberUpdatedState(newValue = locationViewModel.currentLocation.value)
    val recentBhandaraViewModel: RecentBhandaraViewModel = hiltViewModel()

    LaunchedEffect(recentBhandaraViewModel) {
        recentBhandaraViewModel.fetchRecentBhandara()
    }
    val email = remember {
        mutableStateOf("")
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        item {
            Header {
                navController.navigate(NavigationItem)
            }
            CustomTextField(text = email.value) { email.value = it }
            CategoryColumn(navHostController = navController)
            PagerView()
            BannerRow()
        }
    }


}

@Composable
fun Header(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.enjoy_bhandara_every_day), style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W600,
                color = Yellow_1
            )
        )
        IconButton(
            onClick = onClick, modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification), contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text, onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_for_bhandara),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.LightGray
                )
            )
        },
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "search",
                tint = Color.LightGray
            )
        },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 30.dp)
            .fillMaxWidth()
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun CommonText(title: String, subTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 40.dp),
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W600,
                color = Yellow_1
            )
        )
        Text(
            text = subTitle, style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = Color.DarkGray,
            ),
            modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryColumn(navHostController: NavController) {
    Column(
        modifier = Modifier

            .padding(bottom = 10.dp)
    ) {
        CommonText(
            title = stringResource(id = R.string.enjoy_bhandara_every_day),
            subTitle = stringResource(id = R.string.celebrate_with_bhandara_app)
        )
        SpacerHeight(50.dp)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (item in categoryList) {
                CategoryItem(
                    category = item,
                    navController = navHostController
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .clickable {
                navController.navigate(category.destination)
            }
            .padding(vertical = 5.dp)
            .background(category.color, RoundedCornerShape(6.dp))
            .width(165.dp)
            .height(185.dp)

    ) {

        Text(
            text = category.title, style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black,
            ),
            modifier = Modifier.padding(start = 10.dp, top = 20.dp)
        )
        Text(
            text = category.subTitle, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = Color.DarkGray,
            ),
            modifier = Modifier.padding(start = 10.dp, top = 45.dp)
        )
        Image(
            painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun PageUi(bhandara: Bhandara) {
    val painter = rememberImagePainter(
        data = bhandara.bhandaraImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.man)
        }
    )
    val painterState by rememberUpdatedState(painter)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterState,
            contentDescription = bhandara.postedByName,
            modifier = Modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerView() {
    val recentBhandaraViewModel: RecentBhandaraViewModel = hiltViewModel()

    LaunchedEffect(recentBhandaraViewModel) {
        recentBhandaraViewModel.fetchRecentBhandara()
    }
    val recentBhandaraState by recentBhandaraViewModel.recentBhandaraState.collectAsState()
    val pageState = rememberPagerState()

//    LaunchedEffect(pageState) {
//        while (true) {
//            delay(2000)
//            pageState.animateScrollToPage(
//                page = (pageState.currentPage + 1) % recentBhandaraState.size
//            )
//        }
//    }

    Column(modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 0.dp)) {
        HorizontalPager(
            count = recentBhandaraState.size,
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp, 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        ) { page ->
            PageUi(bhandara = recentBhandaraState[page])
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = R.color.sky)
        )
    }
}

@Composable
fun BannerRow() {
    Image(
        painter = painterResource(id = R.drawable.post_bhandara), contentDescription = "",
        modifier = Modifier
            .padding(top = 10.dp, bottom = 50.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(133.dp),
        contentScale = ContentScale.FillBounds
    )
}