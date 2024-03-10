package com.example.bhandara.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bhandara.R
import com.example.bhandara.components.SpacerHeight
import com.example.bhandara.components.SpacerWidth
import com.example.bhandara.ui.theme.DarkOrange
import com.example.bhandara.ui.theme.LightOrange
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.time.LocalDate
import java.util.Locale

val LocaleSelectedDate = compositionLocalOf { mutableStateOf(LocalDate.now()) }

@Composable
fun ShareBhandaraScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
    ) {
        ShareBhandaraHeader {

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightOrange)
        ) {
            item {
                ImageCapture()
                LocationContent()
            }
        }
    }
}

@Composable
fun ShareBhandaraHeader(
    onBack: () -> Unit
) {
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
        SpacerWidth(50.dp)
        Text(
            text = "Share Bhandara Location",
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

//@Composable
//fun ImageCaptureContainer(){
//    ImageCapture()
//}

@Composable
fun ImageCapture() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(height = 200.dp, width = 0.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(width = 2.dp, shape = RoundedCornerShape(10.dp), color = Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.food_app), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(height = 200.dp, width = 0.dp)
                .border(width = 2.dp, shape = RoundedCornerShape(10.dp), color = Color.LightGray)
                .background(Color.LightGray, RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun LocationContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Select Day",
                modifier = Modifier,
                fontSize = 16.sp,
                color = Color.Black
            )
            DayContainer()
            DateContainer()
            TimeContainer()
            SpacerHeight()
            BhandaraAddress()
            BhandaraLocation()

        }
    }
}

@Composable
fun DayContainer() {
    val selectedDay = remember { mutableStateOf("Monday") }

    WeekdayDropdown(
        selectedDay = selectedDay.value,
        onDaySelected = {
            selectedDay.value = it
        }
    )
}

@Composable
fun WeekdayDropdown(
    // Initial selected day
    selectedDay: String,
    onDaySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val weekdays =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    Column {

        OutlinedTextField(
            value = selectedDay,
            onValueChange = {},
            label = { Text("Select Day") },
            trailingIcon = {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            weekdays.forEach { day ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onDaySelected(day)
                }, text = {
                    Text(text = day)
                })
            }
        }
    }
}


@Preview
@Composable
fun DateContainer() {

    val selectedDate = LocaleSelectedDate.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(6.dp)),
        //.border(2.dp, Color.LightGray, RoundedCornerShape(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//        OutlinedTextField(
//            readOnly = true,
//            //onValueChange = { selectedDate.value = it},
//            value = selectedDate.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//            label = { Text(text = "Selected Date") },
//            trailingicon = {
//                IconButton(onClick = { ShowDatePickerDialog() }) {
//                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Open date picker")
//                }
//            })
    }
}

@Composable
fun TimeContainer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(width = 2.dp, shape = RoundedCornerShape(6.dp), color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Start Time", color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "--:--:--", color = Color.Black,
                fontSize = 16.sp
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Start Time", color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "--:--:--", color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ShowDatePickerDialog() {
//    val state = remember {
//        DateRangePickerState(1, 31, 1, 1947..3000, DisplayMode.Picker)
//    }
//    val dateState = remember {
//       DatePickerState
//    }
//    val openDialog = remember {
//        mutableStateOf(true)
//    }
//    if (openDialog.value) {
//        DatePicker(
//            onDismissRequest = { openDialog.value = false },
//            state = dateState,
//            initialSelection = state.selectedStartDateMillis,
//            displayMode = DisplayMode.Picker,
//            calendarLocale = Locale.ENGLISH,
//            onSelectionChange = { selectedDate ->
//                LocaleSelectedDate.current.value = selectedDate
//            }
//        )
//    }
//}

@Composable
fun BhandaraAddress() {
    val address = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = address.value,
        onValueChange = {
            address.value = it
        },
        placeholder = {
            Text(text = "Enter Bhandara Address")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BhandaraLocation() {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .border(width = 2.dp, shape = RoundedCornerShape(6.dp), color = Color.LightGray)
    ) {
        Text(
            text = "Location Details",
            modifier = Modifier.padding(10.dp)
        )
        SpacerHeight()
       MapLocation()
    }
}

@Composable
fun MapLocation() {
    val context = LocalContext.current
    var currentLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var currentAddress by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, shape = RoundedCornerShape(6.dp), color = Color.Cyan)
                .padding(10.dp)
        ) {
            Text(
                text = "$currentAddress",
                fontSize = 22.sp,
                maxLines = 5
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MapComponent(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(250.dp),
            onLocationChanged = { location ->
                currentLocation = location
                currentAddress = getAddressFromLocation(context, location)
            }
        )
    }
}

@Composable
fun MapComponent(
    modifier: Modifier = Modifier,
    onLocationChanged: (LatLng) -> Unit,
) {
    var mapView: MapView? by remember { mutableStateOf(null) }
    var googleMap: GoogleMap? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val fusedLocationClient by remember {
        mutableStateOf(
            LocationServices.getFusedLocationProviderClient(
                context
            )
        )
    }

    DisposableEffect(Unit) {
        mapView?.onCreate(Bundle.EMPTY)
        mapView?.getMapAsync { map ->
            googleMap = map
            if (hasLocationPermissions(context)) {
                // Enable location tracking
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@getMapAsync
                }
                googleMap?.isMyLocationEnabled = true

                // Focus on the current location
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        googleMap?.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                currentLatLng,
                                15f
                            )
                        )
                        onLocationChanged(currentLatLng)
                    }
                }

                // Set a marker at the current location
                googleMap?.addMarker(
                    MarkerOptions().position(LatLng(0.0, 0.0)).title("Current Location")
                )
            } else {
                // Handle the case where permissions are not granted
            }
        }

        onDispose {
            mapView?.onDestroy()
        }
    }

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                mapView = this
            }
        },
        modifier = modifier
    )
}


private fun hasLocationPermissions(context: Context): Boolean {
    return (ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
}

// Function to get the address from a location using Geocoder
private fun getAddressFromLocation(context: Context, location: LatLng): String {
    val geocoder = Geocoder(context, Locale.getDefault())

    try {
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses!!.isNotEmpty()) {
            return addresses[0].getAddressLine(0) ?: "Unknown Address"
        }
    } catch (e: Exception) {
        // Handle exceptions (e.g., IOException, IllegalArgumentException)
        e.printStackTrace()
    }

    return "Address not available"
}
