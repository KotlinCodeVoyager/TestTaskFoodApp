package com.example.domain.usecases

import com.example.domain.interfaces.APIRepositoryInterface

class GetFoodItemUseCase (private val searchFoodDataSource: APIRepositoryInterface) {
    suspend operator fun invoke(itemId: String) = searchFoodDataSource.getFoodItems(itemId)
}