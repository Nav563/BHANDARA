package com.example.bhandara.viewmodels

//
//@Suppress("DEPRECATION")
//@HiltViewModel
//class LocationViewMode @Inject constructor(
//    private val fusedLocationProviderClient: FusedLocationProviderClient, private val applicationContext: Context
//) : ViewModel() {
//
//    private val _currentLocation = mutableStateOf("Unknown")
//    val currentLocation: State<String> get() = _currentLocation
//
//    init {
//        requestLocationUpdates()
//    }
//
//    private fun requestLocationUpdates() {
//        val locationRequest = LocationRequest.create()
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//            .setInterval(5000)
//
//        if (
//            ActivityCompat.checkSelfPermission(
//                applicationContext,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                applicationContext,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest,
//            object : LocationCallback() {
//                override fun onLocationResult(locationResult: LocationResult) {
//                    val location = locationResult.lastLocation
//                    _currentLocation.value = "Lat: ${location?.latitude}, Lon: ${location?.longitude}"
//                }
//            },
//            Looper.getMainLooper()
//        )
//    }
//}
