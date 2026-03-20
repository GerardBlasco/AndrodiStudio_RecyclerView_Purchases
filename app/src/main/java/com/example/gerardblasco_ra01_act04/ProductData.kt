package com.example.gerardblasco_ra01_act04

data class ProductData (
    val name: String,
    val price: String,
    val imageID: Int,
    var quantity: Int = 0
):java.io.Serializable