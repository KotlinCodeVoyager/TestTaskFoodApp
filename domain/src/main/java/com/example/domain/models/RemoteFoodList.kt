package com.example.domain.models

data class RemoteFoodList(
    val title: String,
    val items: List<RemoteItem>
)

data class RemoteItem(
    val id: String,
    val name: String,
    val image: String,
    val color: String
)