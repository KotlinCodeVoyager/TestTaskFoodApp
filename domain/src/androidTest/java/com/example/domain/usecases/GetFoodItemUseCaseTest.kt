package com.example.domain.usecases

import com.example.domain.interfaces.APIRepositoryInterface
import com.example.domain.interfaces.FoodMapperInterface
import com.example.domain.models.FoodItem
import com.example.domain.models.Item
import com.example.domain.models.ListFoodModel
import com.example.domain.models.RemoteFoodList
import com.example.domain.models.RemoteItem
import com.nhaarman.mockitokotlin2.mock
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class FoodUseCaseTests {

    private val foodRepository = mock<APIRepositoryInterface>()
    private val mapper = mock<FoodMapperInterface>()
    private val baseUrl = "baseUrl"

    @Test
    fun itemFoodCaseReturnCorrectData() = runBlocking {

        val expectedFoodItem = FoodItem("cherry", "item - cherry")

        Mockito.`when`(foodRepository.getFoodItems("")).thenReturn(expectedFoodItem)

        val expected = GetFoodItemUseCase(foodRepository).invoke("")

        val actual = FoodItem("cherry", "item - cherry")

        assertEquals(expected, actual)
    }

    @Test
    fun listFoodCaseReturnCorrectData() = runBlocking {

        val expectedFoodList = RemoteFoodList(
            "cherry", listOf(
                RemoteItem(
                    id = "cherry1",
                    name = "cherry2",
                    image = "image",
                    color = "",
                ),
                RemoteItem(
                    id = "banana1",
                    name = "banana2",
                    image = "",
                    color = "",
                )
            )
        )

        Mockito.`when`(foodRepository.getFood()).thenReturn(expectedFoodList)

        val expectedMapperList = ListFoodModel(
            "cherry", listOf(
                Item(
                    id = "cherry1",
                    name = "cherry2",
                    imageUrl = "baseUrl/image",
                    color = 0L,
                )
            )
        )

        Mockito.`when`(mapper.mapFromRemote(expectedFoodList, baseUrl)).thenReturn(expectedMapperList)

        val expected = GetFoodListUseCase(foodRepository, mapper).invoke(baseUrl)

        val actual = ListFoodModel(
            "cherry", listOf(
                Item(
                    id = "cherry1",
                    name = "cherry2",
                    imageUrl = "baseUrl/image",
                    color = 0L,
                )
            )
        )

        assertEquals(expected, actual)
    }

}