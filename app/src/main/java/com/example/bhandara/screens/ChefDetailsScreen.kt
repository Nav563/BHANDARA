package com.example.bhandara.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bhandara.R
import com.example.bhandara.components.SpacerWidth
import com.example.bhandara.ui.theme.DarkOrange

//@Preview
@Composable
fun ChefDetailsScreen(navHostController: NavHostController) {
    Column (modifier = Modifier.fillMaxSize()){
        Topheader {
            navHostController.navigateUp()
        }
        LazyColumn (modifier = Modifier.padding(bottom = 10.dp)){
            item {
                ChefDetailsHeader()
                AllOtherDetails()
            }
        }
    }
}

@Composable
fun Topheader(
    onBack: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 60.dp, width = 0.dp)
            .background(DarkOrange),
        //horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onBack) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
//        SpacerWidth(50.dp)
//        Text(
//            text = "Share Bhandara Location",
//            fontSize = 20.sp,
//            color = Color.Black
//        )
    }
}

@Composable
fun ChefDetailsHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.man), contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = "Chef Name",
                    fontSize = 16.sp,
                    fontWeight = W600
                )
                Text(
                    text = "Mobile",
                    modifier = Modifier
                        .padding(top = 7.dp),
                    fontSize = 14.sp,
                    fontWeight = W400
                )
                Text(
                    text = "Address",
                    modifier = Modifier
                        .padding(top = 7.dp),
                    fontSize = 14.sp,
                    fontWeight = W400
                )
                Text(
                    text = "Ratings",
                    modifier = Modifier
                        .padding(top = 7.dp),
                    fontSize = 14.sp,
                    fontWeight = W400
                )
            }
        }
    }
}


@Composable
fun AllOtherDetails() {
    val eventType = remember {
        mutableStateOf("")
    }
    val noOfGuests = remember {
        mutableStateOf("")
    }
    val eventAddress = remember {
        mutableStateOf("")
    }
    val mobileNo = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fill all details about Event",
            modifier = Modifier
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = W400
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "Event Type")
            OutlinedTextField(
                value = eventType.value, onValueChange = {
                    eventType.value = it
                },
                placeholder = {
                    Text(text = "Event Type")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
            Text(
                text = "No of Guests",
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(
                value = noOfGuests.value, onValueChange = {
                    noOfGuests.value = it
                },
                placeholder = {
                    Text(text = "No of Guests")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            Text(
                text = "Event Address",
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(
                value = eventAddress.value, onValueChange = {
                    eventAddress.value = it
                },
                placeholder = {
                    Text(text = "Event Address")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            Text(
                text = "Mobile No",
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(
                value = mobileNo.value, onValueChange = {
                    mobileNo.value = it
                },
                placeholder = {
                    Text(text = "Mobile No.")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
            Text(
                text = "Chef Menu",
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(6.dp),
                            color = Color.LightGray
                        ),
                ) {
                    Text(text = "Download Menu")
                }
                SpacerWidth()
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(6.dp),
                            color = Color.LightGray
                        ),
                ) {
                    Text(text = "Download Menu")
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(6.dp),
                            color = Color.LightGray
                        ),
                ) {
                    Text(text = "Book")
                }
                SpacerWidth()
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(6.dp),
                            color = Color.LightGray
                        ),
                ) {
                    Text(text = "Call Chef")
                }
            }
        }
    }
}