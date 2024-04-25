package com.sachin_singh_dighan.learnkotlinproject.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin_singh_dighan.learnkotlinproject.R
import com.sachin_singh_dighan.learnkotlinproject.data.local.entity.User

class UserAdapter(
    private val user: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            val textViewUserName = itemView.findViewById<TextView>(R.id.textViewUserName)
            val textViewUserEmail = itemView.findViewById<TextView>(R.id.textViewUserEmail)
            val imageViewAvatar = itemView.findViewById<ImageView>(R.id.imageViewAvatar)
            textViewUserName.text = user.name
            textViewUserEmail.text = user.email
            Glide.with(imageViewAvatar.context).load(user.avatar)
                .into(imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(user[position])
    }

    override fun getItemCount(): Int = user.size

    fun addData(list: List<User>) {
        user.addAll(list)
    }
}