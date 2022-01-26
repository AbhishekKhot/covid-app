package com.example.covidtrackingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covidtrackingapp.R
import com.example.covidtrackingapp.model.Regional
import kotlinx.android.synthetic.main.rv_item.view.*

class CovidDataAdapter(): RecyclerView.Adapter<CovidDataAdapter.RecyclerViewHolder>(){

    inner class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Regional>() {
        override fun areItemsTheSame(oldItem: Regional, newItem: Regional): Boolean {
            return oldItem.totalConfirmed == newItem.totalConfirmed
        }

        override fun areContentsTheSame(oldItem: Regional, newItem: Regional): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
       val list_data = differ.currentList[position]
        holder.itemView.apply {
            tv_state_name.text = list_data.loc
            tv_cases.text = list_data.totalConfirmed.toString()
            tv_deaths.text = list_data.deaths.toString()
            tv_recovered.text = list_data.discharged.toString()
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }


}
