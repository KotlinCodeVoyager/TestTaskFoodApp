package com.example.testtaskfoodapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.FoodDataModel
import com.example.testtaskfoodapp.ui.common.logDebug
import com.example.testtaskfoodapp.ui.food.FoodItemScreen
import com.example.testtaskfoodapp.ui.food.FoodListScreen
import com.example.testtaskfoodapp.ui.theme.TestTaskFoodAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            TestTaskFoodAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = FOOD_LIST
                ) {
                    composable(route = FOOD_LIST) {
                        FoodListScreen(navController)
                    }

                    composable(
                        route = FOOD_ITEM
                    ) {

                        val data: FoodDataModel? =
                            navController.previousBackStackEntry?.savedStateHandle?.get(FOOD_DATA)

                        data?.let {
                            FoodItemScreen(data) {
                                navController.popBackStack()
                            }
                        }

                        logDebug("FoodItemScreen_1", "load")

                    }
                }
            }
        }
    }
}