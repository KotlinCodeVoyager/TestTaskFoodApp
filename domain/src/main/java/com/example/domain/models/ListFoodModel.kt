package com.example.domain.models


data class ListFoodModel(
    val title: String,
    val items: List<Item>
)

data class Item(
    val id: String,
    val name: String,
    val imageUrl: String,
    val color: Long
)