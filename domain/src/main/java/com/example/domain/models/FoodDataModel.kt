package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDataModel (
    val id: String,
    val name: String,
    val imageUrl: String,
    val color: Long
) : Parcelable{

    companion object {
        fun toDataModel(item: Item) : FoodDataModel {
            return FoodDataModel(
                id = item.id,
                name = item.name,
                imageUrl = item.imageUrl.substringAfterLast("/"),
                color = item.color,
            )
        }
    }

}