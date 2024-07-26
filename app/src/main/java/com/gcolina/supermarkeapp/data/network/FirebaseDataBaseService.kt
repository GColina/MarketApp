package com.gcolina.supermarkeapp.data.network

import com.gcolina.supermarkeapp.data.response.ProductResponse
import com.gcolina.supermarkeapp.data.response.TopProductsResponse
import com.gcolina.supermarkeapp.domain.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firestore.v1.Target.QueryTarget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseDataBaseService @Inject constructor(private val firestore: FirebaseFirestore) {

    companion object {
        const val PRODUCTS_PATH = "products"
        const val MANAGEMENT_PATH = "management"
        const val TOP_PRODUCT_DOCUMENT = "top_products"
    }

    suspend fun getAllProducts(): List<Product> {
        return firestore.collection(PRODUCTS_PATH).get().await().map { product ->
            product.toObject(ProductResponse::class.java).toDomain()
        }
    }

    /* Hay que tener una cosa en cuenta y es que firebase te da un credito que gastas por cada llamada,
           si esto lo haces asi funcionara, pero llamara a todos los items y no nosconviene.

           return getAllProducts().last()*/

    // Las id se organizan por fecha, entonces le decimos que lo ordene por id en direccion desendiente, y que me de 1 item

    suspend fun getLastProduct(): Product? {
        return firestore.collection(PRODUCTS_PATH).orderBy("id", Query.Direction.DESCENDING)
            .limit(1)
            .get().await().firstOrNull()?.toObject(ProductResponse::class.java)?.toDomain()

    }

    suspend fun getTopProducts(): List<String> {
        return firestore.collection(MANAGEMENT_PATH).document(TOP_PRODUCT_DOCUMENT).get().await()
            .toObject(TopProductsResponse::class.java)?.ids ?: emptyList()
    }
}