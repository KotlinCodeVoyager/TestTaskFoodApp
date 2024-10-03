package com.example.testtaskfoodapp.ui.food

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.domain.models.FoodItem
import com.example.domain.models.Item
import com.example.testtaskfoodapp.R
import com.example.testtaskfoodapp.ui.common.noRippleClickable
import com.example.testtaskfoodapp.ui.food.vm.FoodItemViewModel
import com.example.testtaskfoodapp.ui.food.vm.FoodViewModel
import com.example.testtaskfoodapp.ui.main.helper.LoadingState

@Composable
fun FoodItemScreen(
    item: Item,
    viewModel: FoodItemViewModel = hiltViewModel(),
    onBack:() -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getFoodItem(item.id)
    }
    val itemFood = viewModel.getItemsFoodItem.observeAsState()
    val itemFoodValue = itemFood.value

    var visible by remember { mutableStateOf(false) }

    val statistic = viewModel.showLoading.observeAsState()
    val statisticValue = statistic.value

    LaunchedEffect(statisticValue == LoadingState.StopLoading){ visible = true }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = Color(0xFFFF469E))
        ) {

            Text(
                text = itemFoodValue?.id ?: "", modifier = Modifier
                    .align(Alignment.Center),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(600)
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_beige),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(35.dp)
                    .rotate(180f)
                    .align(Alignment.CenterStart)
                    .noRippleClickable {
                        onBack()
                    }
            )

        }
        if(visible) {
            AnimatedVisibility(visible = visible) {
                if (itemFoodValue?.id != null) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp)
                    ) {
                        item {
                            FoodTextItem(
                                itemFoodValue.text,
                                "https://test-task-server.mediolanum.f17y.com/images/${item.imageUrl}",
                                item.color
                            )
                        }
                    }
                }
            }

        }else{
            Loader()
        }
    }

}

@Composable
fun FoodTextItem(text: String, imageUrl: String, color: Long) {

    Log.e("jnkjshcj", "$text, $imageUrl, $color")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .background(Color(color), RoundedCornerShape(16.dp))
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 35.dp, start = 24.dp, end = 24.dp)
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 35.dp, top = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemScreenPreview() {
    FoodItemScreen(Item("", "", "", 5L)) {}
}