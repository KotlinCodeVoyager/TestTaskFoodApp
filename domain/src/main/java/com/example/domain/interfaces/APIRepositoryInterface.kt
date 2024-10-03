package com.example.domain.interfaces

import com.example.domain.models.FoodItem
import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList

interface APIRepositoryInterface {

    suspend fun getFood() : RemoteFoodList?

    suspend fun getFoodItems(itemId: String) : FoodItem?
}