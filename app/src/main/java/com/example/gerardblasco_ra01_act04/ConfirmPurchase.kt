package com.example.gerardblasco_ra01_act04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConfirmPurchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm_purchase)

        val cartList = intent.getSerializableExtra("shopping_cart_list") as ArrayList<ProductData>

        val backShoppingButton = findViewById<ImageButton>(R.id.continue_shopping_button)

        val totalPriceText = findViewById<TextView>(R.id.total_price_text_02)

        val confirmationButton = findViewById<Button>(R.id.confirmation_button)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_02)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ShoopingCartAdapter(cartList, totalPriceText)

        val totalPrice = cartList.sumOf {
            val price = it.price.replace("€", "").toDouble()

            price * it.quantity
        }

        totalPriceText.text = String.format("%.2f", totalPrice) + "€"

        backShoppingButton.setOnClickListener {
            finish()
        }

        confirmationButton.setOnClickListener {
            val productsLog = cartList.joinToString("\n") {
                "${it.name} x${it.quantity}"
            }
            Log.d("Product: ", productsLog)

            val toast = Toast.makeText(this, "Products purchased!", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}