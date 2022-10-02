package com.example.paginationdemo.Actvities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.Actvities.Services.Models.PostResp
import com.example.paginationdemo.databinding.ProgressbarBinding
import com.example.paginationdemo.databinding.SingleItemBinding

class ItemAdapter(var list: ArrayList<PostResp.PostRespItem?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val Loading = 0
    private val Item = 1

    class MyViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)
    class MyProgressbarViewHolder(val binding: ProgressbarBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder

        if (viewType == Item) {
            viewHolder = MyViewHolder(
                SingleItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        else {
            viewHolder = MyProgressbarViewHolder(
                ProgressbarBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (getItemViewType(position) == Item) {
            (holder as MyViewHolder)

            holder.binding.tvNumber.text = list[position]?.id.toString()

        }
        else {
            (holder as MyProgressbarViewHolder)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        if (list[position]?.lastElement == true) {
            return Loading
        }
        else {
            return Item
        }

    }

    fun add(post: PostResp?) {
        if (post != null) {
            list.addAll(post)
        };
        notifyDataSetChanged()
    }

    fun getMyList(): ArrayList<PostResp.PostRespItem?> {
        return list
    }

}