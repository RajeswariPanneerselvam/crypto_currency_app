package com.app.cryptocurrencyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.cryptocurrencyapp.R
import com.app.cryptocurrencyapp.model.CoinsModel

class PriceAdapter(coins: ArrayList<CoinsModel.DataModel>, context: Context) :
    RecyclerView.Adapter<PriceAdapter.ViewHolder>() {
    lateinit var coins: ArrayList<CoinsModel.DataModel>
    var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View =
            layoutInflater.inflate(R.layout.price_adapter_layout, parent, false)
        return ViewHolder(listItem)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.text_rank.setText(coins[position].rank)
        val f: Float = coins[position].priceUsd!!.toFloat()
        val f1: Float = coins[position].changePercent24Hr!!.toFloat()
        holder.text_price.setText("$"+String.format("%.2f",f))
        holder.text_change.setText(String.format("%.2f",f1)+"%")
        holder.text_name.setText(coins[position].name)
        holder.text_symbol.setText(coins[position].symbol)

    }


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            lateinit var text_rank: TextView
          lateinit var text_symbol:TextView
          lateinit var text_name:   TextView
          lateinit var text_price:TextView
          lateinit var text_change:TextView


            init {
                text_rank = itemView.findViewById(R.id.text_rank)
                text_symbol = itemView.findViewById(R.id.image_symbol)
                text_name=itemView.findViewById(R.id.text_name)
                text_price = itemView.findViewById(R.id.text_price)
                text_change=itemView.findViewById(R.id.text_change)

            }
        }

        init {
            this.coins = coins
            this.context = context
            notifyDataSetChanged()

        }



    override fun getItemCount(): Int {
        return coins.size
    }

    fun filterList(filteredList: ArrayList<CoinsModel.DataModel>) {
        coins = filteredList
        notifyDataSetChanged()
    }

}
