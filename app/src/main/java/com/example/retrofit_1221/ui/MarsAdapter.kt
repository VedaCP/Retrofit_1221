package com.example.retrofit_1221.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_1221.model.POJO.MarsTerrain
import com.example.retrofit_1221.databinding.MarsItemListBinding
import kotlinx.coroutines.selects.select

class MarsAdapter : RecyclerView.Adapter<MarsAdapter.MarsVH>() {

    private var listMarsItem = listOf<MarsTerrain>()

    private val selectedTerrain = MutableLiveData<MarsTerrain>()
    fun selectedItem() = selectedTerrain

    fun update(list: List<MarsTerrain>){
        listMarsItem = list
        notifyDataSetChanged()
    }
    inner class MarsVH(private val binding: MarsItemListBinding) : RecyclerView.ViewHolder
        (binding.root), View.OnClickListener {
        fun bind(marsTerrain: MarsTerrain) {
            Glide.with(binding.imageView)
                .load(marsTerrain.srcImg)
                .centerCrop()
                .into(binding.imageView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedTerrain.value = listMarsItem [adapterPosition]        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH {
        return MarsVH(MarsItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsAdapter.MarsVH, position: Int) {
        val marsTerrain = listMarsItem[position]
        holder.bind(marsTerrain)
    }

    override fun getItemCount(): Int = listMarsItem.size

}