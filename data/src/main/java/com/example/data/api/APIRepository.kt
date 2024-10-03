package com.example.data.api

import com.example.domain.interfaces.APIRepositoryInterface
import com.example.domain.models.FoodItem
import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList

class APIRepository(
    private val apiService: APIService,
) : APIRepositoryInterface {

    override suspend fun getFood(): RemoteFoodList? {

        val response = apiService.getFood()
        if (response.isSuccessful)
            return response.body()
        else throw Exception(response.errorBody()?.string())

    }

    override suspend fun getFoodItems(itemId: String): FoodItem? {

        val response = apiService.getFoodItems(itemId)
        if (response.isSuccessful)
            return response.body()
        else throw Exception(response.errorBody()?.string())

    }

}