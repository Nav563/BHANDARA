package com.example.bhandara.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bhandara.screens.ChefDetailsScreen
import com.example.bhandara.screens.CommunityScreen
import com.example.bhandara.screens.EditProfileScreen
import com.example.bhandara.screens.FoodScreen
import com.example.bhandara.screens.HomeScreen
import com.example.bhandara.screens.LoginScreen
import com.example.bhandara.screens.NavigationItemScreen
import com.example.bhandara.screens.ProfileItemRow
import com.example.bhandara.screens.ProfileScreen
import com.example.bhandara.screens.ShareBhandaraScreen
import com.example.bhandara.screens.SignUpScren
import com.example.bhandara.viewpager.OnBoardingScreen

@Composable
fun StartNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnBoarding
    ) {
        composable(OnBoarding){
            OnBoardingScreen(navController)
        }
        composable(Home) {
            HomeScreen(navController)
        }
        composable(Login){
            LoginScreen(navController)
        }
        composable(SignUp){
            SignUpScren(navController)
        }
        composable(NavigationItem){
            NavigationItemScreen(navController)
        }
        composable(Community){
            CommunityScreen()
        }
        composable(Profile){
            ProfileScreen(navController)
        }
        composable(EditProfile){
            EditProfileScreen(navController)
        }
        composable(Food){
            FoodScreen(navController)
        }
        composable(PersonalInfo){
            ProfileItemRow()
        }
        composable(Posts){
            ProfileItemRow()
        }
        composable(PunyaCoins){
            ProfileItemRow()
        }
        composable(ShareBhandara){
            ShareBhandaraScreen()
        }
        composable(ChefDetails){
            ChefDetailsScreen(navController)
        }
    }
}
const val Home = "home_screen"
const val Food = "food_screen"
const val Community = "community_screen"
const val Login = "login_screen"
const val SignUp = "signup_screen"
const val OnBoarding = "onboarding_screen"
const val NavigationItem = "navigation_item_screen"
const val Profile = "profile_screen"
const val EditProfile = "edit_profile_screen"
const val PersonalInfo = "profile_item_row"
const val Posts = "profile_item_row"
const val PunyaCoins = "profile_item_row"
const val ShareBhandara = "share_bhandara_screen"
const val ChefDetails = "chef_details_screen"