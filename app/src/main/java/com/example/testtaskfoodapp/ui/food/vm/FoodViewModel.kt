package com.example.testtaskfoodapp.ui.food.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FoodItem
import com.example.domain.models.ListFoodModel
import com.example.domain.usecases.GetFoodItemUseCase
import com.example.domain.usecases.GetFoodListUseCase
import com.example.testtaskfoodapp.BuildConfig
import com.example.testtaskfoodapp.ui.common.logDebug
import com.example.testtaskfoodapp.ui.main.helper.LoadingState
import com.example.testtaskfoodapp.ui.main.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodListUseCase: GetFoodListUseCase
) : ViewModel() {

    val showLoading: SingleLiveEvent<LoadingState> = SingleLiveEvent()

    private var _getItemsFoodList = MutableLiveData<ListFoodModel?>()
    val getItemsFoodList: LiveData<ListFoodModel?> = _getItemsFoodList

    init {
        getFoodList()
    }

    fun reloadFoodList(){
        getFoodList()
    }

    private fun getFoodList(){
        viewModelScope.launch {
            runCatching {
                logDebug("load_start", "load")
                showLoading.postValue(LoadingState.Loading)
                getFoodListUseCase.invoke(BuildConfig.BASE_URL)

            }.onSuccess {
                showLoading.postValue(LoadingState.StopLoading)
                _getItemsFoodList.postValue(it)
                logDebug("load_success", it.toString())

            }.onFailure {
                showLoading.postValue(LoadingState.StopLoading)
                logDebug("load_failure", it.message.toString())
            }
        }
    }

}