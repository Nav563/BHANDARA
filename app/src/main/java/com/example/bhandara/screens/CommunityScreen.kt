package com.example.bhandara.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.bhandara.R
import com.example.bhandara.models.Post
import com.example.bhandara.models.User
import com.example.bhandara.ui.theme.DarkOrange
import com.example.bhandara.ui.theme.LineColor
import com.example.bhandara.ui.theme.Yellow_1
import com.example.bhandara.viewmodels.PostViewModel
import com.example.bhandara.viewmodels.UserViewModel
import org.ocpsoft.prettytime.PrettyTime

@Composable
fun CommunityScreen() {
    Column {
        TopHeader {

        }
        CombinedLazyColumn()
    }
}

@Composable
fun CombinedLazyColumn() {
    //val firebaseAuth = FirebaseAuth.getInstance()
    val viewModel: PostViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    LaunchedEffect(userViewModel) {
        //userViewModel.getUserData(firebaseAuth.currentUser!!.uid)
    }
    //val user by userViewModel.userState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.fetchPosts()
    }
    val dataState by viewModel.postsState.collectAsState()
    LazyColumn(
        modifier = Modifier
            .background(colorResource(id = R.color.sky))
    ) {
        item {
            //user?.let { CreatePostRowItem(it) }
            CreatePostRowItem()
            Divider(color = LineColor, thickness = 1.dp)
        }
        items(dataState.size) { index ->
            PostListItem(dataState[index],)
        }
    }
}

@Composable
fun PostListItem(post: Post) {
    val date = post.timestamp.toDate()
    val prettyTime = PrettyTime()
    val formattedDate = prettyTime.format(date)
    val painter = rememberImagePainter(
        data = post.postImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.man)
        }
    )
    val painterState by rememberUpdatedState(painter)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clip(RectangleShape)
            .background(MaterialTheme.colorScheme.background)
        //.shadow(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)

            ) {
                Image(
                    painter = painterState, contentDescription = "",
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .fillMaxWidth(.20F),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.9F)
                ) {
                    Text(
                        text = post.postedByName,
                        modifier = Modifier,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = formattedDate,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Image(
                    imageVector = Icons.Default.Clear, contentDescription = "clear",
                    modifier = Modifier
                        .fillMaxWidth(.9F)
                )
            }
            Text(
                text = post.postDescription.toString(),
                fontSize = 20.sp,
                maxLines = 100,
                modifier = Modifier
                    .padding(10.dp)
            )
            Image(
                painter = painterState,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(220.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(start = 15.dp, end = 10.dp),
                Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.7F)
                ) {
                    Text(
                        text = "0",
                        modifier = Modifier
                            .fillMaxWidth(.04F)
                    )
                    Text(
                        text = "Like",
                        modifier = Modifier
                            .fillMaxWidth(.7F)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth(.9F)
                ) {
                    Text(
                        text = "0",
                        modifier = Modifier
                            .fillMaxWidth(.1F)
                    )
                    Spacer(modifier = Modifier.padding(0.dp))
                    Text(
                        text = "Comments",
                        modifier = Modifier
                            .fillMaxWidth(.8F)
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .background(LineColor)
                    .height(1.dp)
            )
            LikeCommentScreen()
            Box(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(10.dp, 3.dp, 10.dp, 0.dp)
                    .background(LineColor)
                    .height(1.dp)
            )
        }
    }
}

@Composable
fun LikeCommentScreen() {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp)
            .fillMaxWidth(1F),
        Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.35F)
        ) {
            Image(
                painterResource(id = R.drawable.like), contentDescription = "like",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Like",
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(.6F)
        ) {
            Image(
                painterResource(id = R.drawable.comment), contentDescription = "Comment",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Comment",
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(.8F)
        ) {

            Image(
                painterResource(id = R.drawable.farword), contentDescription = "Share",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Share",
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CreatePostRowItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .background(Color.White),
        Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.man), contentDescription = "profile",
            modifier = Modifier
                .size(70.dp)
                .weight(.2F)
                .padding(start = 10.dp, end = 10.dp)
        )
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(.8F)
                .padding(end = 10.dp)
                .background(Color.White),
            border = BorderStroke(1.dp,LineColor)
        ) {
            Text(
                text = "Write something here",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
            )
        }
    }
}
@Composable
fun TopHeader(onClick: () -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 15.dp, end = 10.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.bhandara), style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W600,
                color = Yellow_1,
            ),
            modifier = Modifier.weight(.8F)
        )
        IconButton(
            onClick = onClick, modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
                .weight(.1F)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle, contentDescription = "",
                tint = DarkOrange, modifier = Modifier.size(35.dp).padding(end = 5.dp)
            )
        }
        IconButton(
            onClick = onClick, modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
                .weight(.1F)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification), contentDescription = "",
                tint = Color.Unspecified,modifier = Modifier.size(35.dp)
            )
        }
    }
}
