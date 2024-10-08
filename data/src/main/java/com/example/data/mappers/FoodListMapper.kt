package com.example.data.mappers

import com.example.domain.interfaces.FoodMapperInterface
import com.example.domain.models.Item
import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList

class FoodListMapper : FoodMapperInterface {

    override fun mapFromRemote(remoteFoodList: RemoteFoodList?, baseUrl: String): ListFoodModel? {
        return when (remoteFoodList) {
            null -> null
            else -> ListFoodModel(
                title = remoteFoodList.title,
                items = remoteFoodList.items
                    .filter {
                        it.image.isNotBlank()
                    }.map {
                        Item(
                            id = it.id,
                            name = it.name,
                            imageUrl = "$baseUrl${it.image}",
                            color = "FF${it.color}".toLong(16),
                        )
                    }
            )
        }
    }

}