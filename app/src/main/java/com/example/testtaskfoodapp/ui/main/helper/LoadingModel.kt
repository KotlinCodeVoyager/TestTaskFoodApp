package com.example.testtaskfoodapp.ui.main.helper

sealed class LoadingState {
    data object StopLoading : LoadingState()
    data object Loading : LoadingState()
}
