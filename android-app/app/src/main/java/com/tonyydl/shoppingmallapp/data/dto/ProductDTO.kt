package com.tonyydl.shoppingmallapp.data.dto

import com.google.gson.annotations.SerializedName
import com.tonyydl.shoppingmallapp.data.Mapper
import com.tonyydl.shoppingmallapp.data.ProductCategory
import com.tonyydl.shoppingmallapp.data.vo.Product

data class ProductDTO(
    @SerializedName("product_id") val productId: Int = 0,
    @SerializedName("product_name") val productName: String? = null,
    val category: ProductCategory? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    val price: Int = 0,
    val stock: Int = 0,
    val description: String? = null,
    @SerializedName("created_date")
    val createdDate: Long? = null,
    @SerializedName("last_modified_date")
    val lastModifiedDate: Long? = null
) : Mapper<Product> {
    override fun transform(): Product {
        return Product(
            productId = productId,
            productName = productName.orEmpty(),
            category = category ?: ProductCategory.NONE,
            imageUrl = imageUrl.orEmpty(),
            price = price,
            stock = stock,
            description = description.orEmpty(),
            createdDate = createdDate ?: 0L,
            lastModifiedDate = lastModifiedDate ?: 0L
        )
    }
}