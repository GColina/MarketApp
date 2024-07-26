package com.gcolina.supermarkeapp.domain.model

data class Product(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
)