package com.example.testtaskfoodapp.ui.food

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.domain.models.FoodDataModel
import com.example.testtaskfoodapp.BuildConfig
import com.example.testtaskfoodapp.R
import com.example.testtaskfoodapp.ui.common.noRippleClickable
import com.example.testtaskfoodapp.ui.food.vm.FoodItemViewModel
import com.example.testtaskfoodapp.ui.main.helper.LoadingState
import java.util.Locale

@Composable
fun FoodItemScreen(
    item: FoodDataModel,
    onBack: () -> Unit
) {

    val viewModel: FoodItemViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getFoodItem(item.id)
    }
    val itemFoodValue = viewModel.getItemsFoodItem.observeAsState()

    var visible by remember { mutableStateOf(false) }

    val statisticValue = viewModel.showLoading.observeAsState()

    if (statisticValue.value == LoadingState.StopLoading) {
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
                    text = itemFoodValue.value?.id?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } ?: "",
                    modifier = Modifier
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
        },
    ) { paddingValues ->

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 2000)) +
                    slideInVertically(initialOffsetY = { -it }),
            exit = fadeOut(animationSpec = tween(durationMillis = 2000)) +
                    slideOutVertically(targetOffsetY = { it })
        ) {
            if (itemFoodValue.value?.id != null) {

                LazyColumn(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    item {
                        FoodTextItem(
                            itemFoodValue.value?.text ?: "",
                            "${BuildConfig.BASE_URL}/images/${item.imageUrl}",
                            item.color
                        )
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
    FoodItemScreen(FoodDataModel("", "", "", 5L)) {}
}