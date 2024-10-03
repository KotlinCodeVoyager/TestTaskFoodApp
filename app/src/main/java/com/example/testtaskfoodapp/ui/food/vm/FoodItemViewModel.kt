package com.example.testtaskfoodapp.ui.food.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FoodItem
import com.example.domain.models.ListFoodModel
import com.example.domain.usecases.GetFoodItemUseCase
import com.example.domain.usecases.GetFoodListUseCase
import com.example.testtaskfoodapp.ui.common.logDebug
import com.example.testtaskfoodapp.ui.main.helper.LoadingState
import com.example.testtaskfoodapp.ui.main.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodItemViewModel @Inject constructor(
    private val getFoodItemUseCase: GetFoodItemUseCase,
) : ViewModel() {

    val showLoading: SingleLiveEvent<LoadingState> = SingleLiveEvent()

    private var _getItemsFoodItem = MutableLiveData<FoodItem?>()
    val getItemsFoodItem: LiveData<FoodItem?> = _getItemsFoodItem

    fun getFoodItem(itemId: String){
        viewModelScope.launch {
            runCatching {
                logDebug("load_start", "load")
                showLoading.postValue(LoadingState.Loading)
                getFoodItemUseCase.invoke(itemId)

            }.onSuccess {
                showLoading.postValue(LoadingState.StopLoading)
                _getItemsFoodItem.postValue(it)
                logDebug("load_success", it.toString())

            }.onFailure {
                showLoading.postValue(LoadingState.StopLoading)
                logDebug("load_failure", it.message.toString())

            }
        }
    }

}