package com.example.testtaskfoodapp.di.usecases

import com.example.data.api.APIRepository
import com.example.data.mappers.FoodListMapper
import com.example.domain.usecases.GetFoodItemUseCase
import com.example.domain.usecases.GetFoodListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCases {

    @Provides
    fun getFoodListUseCase(
        apiRepo: APIRepository,
        foodListMapper: FoodListMapper,
    ) = GetFoodListUseCase(apiRepo,  foodListMapper)

    @Provides
    fun getFoodItemUseCase(apiRepo: APIRepository) = GetFoodItemUseCase(apiRepo)

}