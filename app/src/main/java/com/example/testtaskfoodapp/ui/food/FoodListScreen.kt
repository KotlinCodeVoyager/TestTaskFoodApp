package com.example.testtaskfoodapp.ui.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.domain.models.FoodDataModel
import com.example.domain.models.Item
import com.example.testtaskfoodapp.R
import com.example.testtaskfoodapp.ui.common.navigateSendData
import com.example.testtaskfoodapp.ui.common.noRippleClickable
import com.example.testtaskfoodapp.ui.food.vm.FoodViewModel
import com.example.testtaskfoodapp.ui.main.FOOD_DATA
import com.example.testtaskfoodapp.ui.main.FOOD_ITEM
import com.example.testtaskfoodapp.ui.main.helper.LoadingState
import kotlinx.coroutines.delay

@Composable
fun FoodListScreen(
    navController: NavHostController?
) {

    val viewModel: FoodViewModel = hiltViewModel()
    val listValue = viewModel.getItemsFoodList.observeAsState().value

    var visible by remember { mutableStateOf(false) }

    val statistic = viewModel.showLoading.observeAsState().value

    LaunchedEffect(statistic == LoadingState.StopLoading) {
        delay(500)
        visible = true
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(color = Color(0xFFFF469E))
            ) {

                Text(
                    text = listValue?.title ?: "", modifier = Modifier
                        .align(Alignment.Center),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(600)
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_refresh_button),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(35.dp)
                        .align(Alignment.CenterEnd)
                        .noRippleClickable {
                            visible = false
                            viewModel.reloadFoodList()
                        }
                )

            }

        },
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 2000)) +
                    slideInVertically(initialOffsetY = { it }),
            exit = fadeOut(animationSpec = tween(durationMillis = 2000)) +
                    slideOutVertically(targetOffsetY = { -it })
        ) {
            if (listValue?.items != null) {

                LazyColumn(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    items(listValue.items) { food ->
                        FoodItem(food) {
                            navController?.navigateSendData(
                                FOOD_ITEM,
                                FOOD_DATA,
                                FoodDataModel.toDataModel(food)
                            )
                        }
                    }
                }
            }
        }

        if (!visible) {
            Loader(paddingValues.calculateTopPadding())
        }
    }
}

@Composable
fun Loader(paddingTop: Dp) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))

    Box(
        modifier = Modifier
            .padding(top = paddingTop)
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopCenter),
        )
    }
}

@Composable
fun FoodItem(foodItem: Item, onFoodItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp)
            .height(100.dp)
            .background(Color(foodItem.color), RoundedCornerShape(16.dp))
            .noRippleClickable { onFoodItemClick() }
    ) {

        Text(
            text = foodItem.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .align(Alignment.CenterVertically)
                .weight(1f, true),
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )

        AsyncImage(
            model = foodItem.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, end = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FoodListScreenPreview() {
    FoodListScreen(null)
}