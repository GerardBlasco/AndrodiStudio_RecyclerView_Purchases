package com.example.gerardblasco_ra01_act04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoopingCartAdapter(private val productList: List<ProductData>, private val totalPriceText: TextView) :
    RecyclerView.Adapter<ShoopingCartAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productName : TextView = itemView.findViewById(R.id.product_name)
        val productPrice : TextView = itemView.findViewById(R.id.product_price)
        val productImage : ImageView = itemView.findViewById(R.id.product_image)
        val productQuantity : TextView = itemView.findViewById(R.id.product_quantity)
        val productTotalPrice : TextView = itemView.findViewById(R.id.product_total_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        val totalProductPrice = product.price.replace("€", "").toDouble() * product.quantity

        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productImage.setImageResource(product.imageID)
        holder.productQuantity.setText("x" + product.quantity.toString())
        holder.productTotalPrice.setText("Total: " + String.format("%.2f", totalProductPrice) + "€")
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}