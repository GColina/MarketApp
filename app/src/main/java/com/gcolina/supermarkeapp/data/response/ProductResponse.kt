package com.gcolina.supermarkeapp.data.response

import com.gcolina.supermarkeapp.domain.model.Product

class ProductResponse(
    val id: String = "",
    val image: String= "",
    val name: String= "",
    val description: String= "",
    val price: String= "",
){
    fun toDomain():Product{
        return Product(
            id = id,
            imageUrl = image,
            title = name,
            description = description,
            price = price,

        )
    }
}