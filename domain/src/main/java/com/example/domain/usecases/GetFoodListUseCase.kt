package com.example.domain.usecases

import com.example.domain.interfaces.APIRepositoryInterface
import com.example.domain.interfaces.FoodMapperInterface
import com.example.domain.models.ListFoodModel

class GetFoodListUseCase(
    private val searchFoodDataSource: APIRepositoryInterface,
    private val foodMapper: FoodMapperInterface
) {
    suspend operator fun invoke(baseUrl: String) : ListFoodModel? {
        val foodList = searchFoodDataSource.getFood()
        return foodMapper.mapFromRemote(foodList, baseUrl)
    }
}