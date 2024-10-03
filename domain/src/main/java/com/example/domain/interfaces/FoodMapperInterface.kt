package com.example.domain.interfaces

import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList

interface FoodMapperInterface {
    fun mapFromRemote(remoteFoodList: RemoteFoodList?, baseUrl: String): ListFoodModel?
}