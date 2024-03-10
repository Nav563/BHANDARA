package com.example.bhandara.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bhandara.R
import com.example.bhandara.components.SpacerHeight
import com.example.bhandara.components.SpacerWidth
import com.example.bhandara.data.Category
import com.example.bhandara.data.PostData
import com.example.bhandara.data.ProfileData
import com.example.bhandara.data.categoryList
import com.example.bhandara.data.postDataList
import com.example.bhandara.data.profileDataList
import com.example.bhandara.navigation.EditProfile
import com.example.bhandara.navigation.Food
import com.example.bhandara.navigation.Login
import com.example.bhandara.navigation.PersonalInfo
import com.example.bhandara.navigation.Posts
import com.example.bhandara.navigation.PunyaCoins
import com.example.bhandara.ui.theme.DarkOrange
import com.example.bhandara.ui.theme.GrayColor
import com.example.bhandara.ui.theme.LightGray_1
import com.example.bhandara.ui.theme.LightOrange


@Composable
fun ProfileScreen(navHostController: NavHostController) {

    val chips = listOf("Personal Info", "       Posts      ", " Punya Coins ")
    var selected by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
    ) {
        Column {
            HeaderSection {
                navHostController.navigateUp()
            }
            Image(
                painter = painterResource(id = R.drawable.man), contentDescription = "",
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 220.dp)
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
                        CustomChips(
                            title = s,
                            selected = index == selected,
                            index = index,
                            onValueChange = {
                                selected = it

                            })
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        when (selected) {
                            0 -> ProfileItemRow()
                            1 -> PostItemRow()
                            2 -> ProfileItemRow()
                        }
                    }
                }
                SpacerHeight(15.dp)
                Divider(modifier = Modifier.fillMaxWidth(), color = GrayColor, thickness = 5.dp)

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        ProfileItemRow()
                    }
                }
            }
            EditProfileButton(modifier = Modifier.align(BottomCenter)) {
                navHostController.navigate(EditProfile)
            }
        }
    }

}

@Composable
fun EditProfileButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    )
    {
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RectangleShape)
                .background(LightOrange),

        ) {
            Text(
                text = stringResource(id = R.string.edit_profile),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileItemRow() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.dp, end = 7.dp, top = 10.dp, bottom = 60.dp),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        profileDataList.forEach {
            ProfileItem(profile = it)
        }
    }
}

@Composable
fun ProfileItem(
    profile: ProfileData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier

    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Center)
                .padding(top = 10.dp)
                .size(width = 150.dp, height = 130.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = .000122.dp)

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = profile.title, style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = profile.description, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300,
                        color = Color.Black
                    ), maxLines = 5
                )
            }
        }
    }
}


@Composable
fun HeaderSection(
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
            Icon(Icons.Default.ArrowBack, contentDescription = "", tint = Color.Black, modifier = Modifier.size(30.dp))
        }

        Icon(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp)
                .size(35.dp)
        )
    }
}

@Composable
fun CustomChips(
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostItemRow() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.dp, end = 7.dp, top = 10.dp, bottom = 60.dp),
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        postDataList.forEach {
            PostItem(post = it)
        }
    }
}

@Composable
fun PostItem(
    post: PostData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier

    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Center)
                .padding(top = 10.dp)
                .size(width = 100.dp, height = 100.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = .000122.dp)

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = post.title, style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = post.description, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


