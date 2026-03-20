package com.example.gerardblasco_ra01_act04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shoppingCartButton = findViewById<ImageButton>(R.id.shopping_cart_button)

        val totalPriceText = findViewById<TextView>(R.id.total_price_text)

        val productList = mutableListOf(
            ProductData("Bread", "0.50€", R.drawable.bread),
            ProductData("Tomatoes", "2.45€", R.drawable.tomato),
            ProductData("Chicken", "6.20€", R.drawable.chicken),
            ProductData("Rice", "1.10€", R.drawable.rice),
            ProductData("Lettuce", "1.00€", R.drawable.lettuce),
            ProductData("Fish", "10.50€", R.drawable.fish),
            ProductData("Milk", "3.25€", R.drawable.milk),
            ProductData("Lemons", "1.50€", R.drawable.lemon),
            ProductData("Yogurt", "4.20€", R.drawable.yogurt),
            ProductData("Water", "1.50€", R.drawable.water)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_01)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(productList, totalPriceText)

        shoppingCartButton.setOnClickListener {
            val itemsPurchased = productList.filter { it.quantity > 0 }

            val changeActivityIntent = Intent(this, ConfirmPurchase::class.java)

            if(itemsPurchased.isNotEmpty()){
                changeActivityIntent.putExtra("shopping_cart_list", ArrayList(itemsPurchased))
                startActivityForResult(changeActivityIntent, 1)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.recycler_view_01).adapter?.notifyDataSetChanged()
    }
}