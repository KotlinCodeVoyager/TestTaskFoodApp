package com.example.testtaskfoodapp.di.mappers

import com.example.data.mappers.FoodListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class Mappers {

    @Provides
    fun getFoodMapper() = FoodListMapper()

}