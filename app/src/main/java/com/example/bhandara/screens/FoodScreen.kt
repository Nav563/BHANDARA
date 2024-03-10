package com.example.bhandara.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.bhandara.R
import com.example.bhandara.components.SpacerWidth
import com.example.bhandara.data.PostData
import com.example.bhandara.data.ProfileData
import com.example.bhandara.data.postDataList
import com.example.bhandara.data.profileDataList
import com.example.bhandara.models.Bhandara
import com.example.bhandara.ui.theme.DarkOrange
import com.example.bhandara.ui.theme.GrayColor
import com.example.bhandara.ui.theme.LightGray_1
import com.example.bhandara.ui.theme.LightOrange
import com.example.bhandara.viewmodels.RecentBhandaraViewModel

@Composable
fun FoodScreen(navHostController: NavHostController) {

    val recentBhandaraViewModel : RecentBhandaraViewModel = hiltViewModel()

    LaunchedEffect(recentBhandaraViewModel){
        recentBhandaraViewModel.fetchRecentBhandara()
    }
    val recentBhandaraState by recentBhandaraViewModel.recentBhandaraState.collectAsState()

    val chips = listOf("   DAILY BHANDARA     ", "     RECENT BHANDARA    ")
    var selected by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
    ) {
        Column {
            HeaderSec {
                navHostController.navigateUp()
            }
//            Image(
//                painter = painterResource(id = R.drawable.man), contentDescription = "",
//                modifier = Modifier
//                    .size(140.dp)
//                    .align(Alignment.CenterHorizontally)
//            )
        }

        Box(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(36.dp))

        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    chips.forEachIndexed { index, s ->
                        BhandaraChips(
                            title = s,
                            selected = index == selected,
                            index = index,
                            onValueChange = {
                                selected = it

                            })
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = GrayColor, thickness = 5.dp)
                LazyColumn(modifier = Modifier.fillMaxSize()
                    .padding(bottom = 20.dp)) {
                    items(recentBhandaraState.size) { index ->
                        when (selected) {
                            0 -> DailyBhandaraItem(recentBhandaraState[index])
                        }

                    }
                    items(recentBhandaraState.size) { index ->
                        when (selected) {
                            1 -> RecentBhadaraItem(recentBhandaraState[index])
                        }

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .align(BottomCenter)
                    .size(20.dp)
            ) {

            }
        }
    }

}

@Composable
fun DailyBhandaraItem(
    dailyBhandara: Bhandara,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(
        data = dailyBhandara.bhandaraImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.man)
        }
    )
    val painterState by rememberUpdatedState(painter)
    Box(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)


    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Center)
                .padding(top = 10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = .22.dp)

        ) {
            Row (modifier = Modifier.padding( top = 10.dp)){
                Image(
                    painter = painterState, contentDescription = "",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = dailyBhandara.bhandaraDay, style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = dailyBhandara.bhandaraAddress.toString(), style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W300,
                            color = Color.Black
                        ), maxLines = 5
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = dailyBhandara.bhandaraDate,
                            fontSize = 16.sp,
                            color = Color.LightGray
                        )
                        Divider(
                            modifier = Modifier
                                .width(4.dp)
                                .height(25.dp), color = GrayColor
                        )

                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(LightOrange, RoundedCornerShape(0.dp))
                        ) {
                            TextButton(onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    //.clip(RectangleShape)
                                    .background(LightOrange)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn, contentDescription = "",
                                    modifier = Modifier
                                    , colorResource(id = R.color.black)
                                )
                                Text(text = "Open Details",
                                    color = Color.Black)
                            }
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun HeaderSec(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onBack) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "",
                tint = DarkOrange,
                modifier = Modifier.size(30.dp)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_add_comment_24),
            contentDescription = "",
            tint = DarkOrange,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp)
                .size(35.dp)
        )
    }
}

@Composable
fun BhandaraChips(
    title: String,
    selected: Boolean,
    index: Int,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit,

    ) {

    TextButton(
        onClick = { onValueChange(index) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) LightOrange else Color.Transparent,
            contentColor = if (selected) DarkOrange else LightGray_1
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = modifier.padding(start = 20.dp)
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
            )
        )
    }

}

@Composable
fun RecentBhadaraItem(
    recentBhandara: Bhandara,
    modifier: Modifier = Modifier,
) {
    val painter = rememberImagePainter(
        data = recentBhandara.bhandaraImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.man)
        }
    )
    val painterState by rememberUpdatedState(painter)
    Box(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)

    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Center)
                .padding(top = 10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = .22.dp)

        ) {
            Row (modifier = Modifier.padding( top = 10.dp)
            ){
                Image(
                    painter = painterState, contentDescription = "",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = recentBhandara.postedByName, style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = recentBhandara.bhandaraAddress.toString(), style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W300,
                            color = Color.Black
                        ), maxLines = 5
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = recentBhandara.bhandaraDate,
                            fontSize = 16.sp,
                            color = Color.LightGray
                        )
                        Divider(
                            modifier = Modifier
                                .width(4.dp)
                                .height(25.dp), color = GrayColor
                        )

                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(LightOrange, RoundedCornerShape(0.dp))
                        ) {
                            TextButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn, contentDescription = "",
                                    modifier = Modifier
                                    , colorResource(id = R.color.black)
                                )
                                Text(text = "Open Details",
                                    color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}


