package com.example.testtaskfoodapp.di.repo

import com.example.data.api.APIRepository
import com.example.data.api.APIService
import com.example.data.mappers.FoodListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class Repositories {

    @Provides
    fun getAPIRepository(apiService: APIService) = APIRepository(apiService)

}