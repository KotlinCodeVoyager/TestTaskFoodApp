package com.example.data.api

import com.example.data.common.FOOD_INFO
import com.example.data.common.FOOD_LIST
import com.example.data.common.ITEMS_ID
import com.example.domain.models.FoodItem
import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET(FOOD_LIST)
    suspend fun getFood() : Response<RemoteFoodList>

    @GET(FOOD_INFO)
    suspend fun getFoodItems(@Path(ITEMS_ID) itemId: String) : Response<FoodItem>

}