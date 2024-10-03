package com.example.testtaskfoodapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.models.Item
import com.example.testtaskfoodapp.ui.food.FoodItemScreen
import com.example.testtaskfoodapp.ui.food.FoodListScreen
import com.example.testtaskfoodapp.ui.food.vm.FoodItemViewModel
import com.example.testtaskfoodapp.ui.theme.TestTaskFoodAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            TestTaskFoodAppTheme{
                NavHost(
                    navController = navController,
                    startDestination = FOOD_LIST
                ){
                    composable(FOOD_LIST){ _ ->
                        FoodListScreen { item ->

                           val id = item.id
                            val color = item.color
                            val imageUrl = item.imageUrl.substringAfterLast("/")

                            navController.apply {
                                navigate("$FOOD_ITEM/$id/$color/$imageUrl")
                            }
                        }
                    }

                    composable(route = "$FOOD_ITEM/{$FOOD_ID}/{$FOOD_COLOR}/{$FOOD_IMAGE}", arguments = listOf(
                        navArgument(name = FOOD_ID){ type = NavType.StringType },
                        navArgument(name = FOOD_COLOR){ type = NavType.LongType },
                        navArgument(name = FOOD_IMAGE){ type = NavType.StringType },
                    )){ navBackStackEntry ->

                        val id = navBackStackEntry.arguments?.getString(FOOD_ID).toString()
                        val color = navBackStackEntry.arguments?.getLong(FOOD_COLOR) ?: 5L
                        val image = navBackStackEntry.arguments?.getString(FOOD_IMAGE).toString()
                        val itemData = Item(id = id, name = "", imageUrl = image, color = color)

                        FoodItemScreen(
                            itemData
                        ){
                            navController.popBackStack()
                        }
                    }
                }
            }

//            TestTaskFoodAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestTaskFoodAppTheme {
//        Greeting("Android")
//    }
//}