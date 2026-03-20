package com.example.gerardblasco_ra01_act04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<ProductData>, private val totalPriceText: TextView) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productName : TextView = itemView.findViewById(R.id.product_name)
        val productPrice : TextView = itemView.findViewById(R.id.product_price)
        val productImage : ImageView = itemView.findViewById(R.id.product_image)
        val productQuantity : TextView = itemView.findViewById(R.id.product_quantity)
        val addButton : Button = itemView.findViewById(R.id.add_button)
        val substractButton : Button = itemView.findViewById(R.id.substract_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productImage.setImageResource(product.imageID)
        holder.productQuantity.text = product.quantity.toString()

        holder.addButton.setOnClickListener {
            var currentPrice = totalPriceText.text.toString().replace("€", "").toDouble()
            var priceToAdd = holder.productPrice.text.toString().replace("€", "").toDouble()
            currentPrice += priceToAdd

            product.quantity++

            totalPriceText.setText(String.format("%.2f", currentPrice) + "€")
            holder.productQuantity.setText(product.quantity.toString())
        }

        holder.substractButton.setOnClickListener {
            var currentPrice = totalPriceText.text.toString().replace("€", "").toDouble()
            var priceToRemove = holder.productPrice.text.toString().replace("€", "").toDouble()
            currentPrice -= priceToRemove

            if(product.quantity > 0){
                product.quantity--
                totalPriceText.setText(String.format("%.2f", currentPrice) + "€")
                holder.productQuantity.setText(product.quantity.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}