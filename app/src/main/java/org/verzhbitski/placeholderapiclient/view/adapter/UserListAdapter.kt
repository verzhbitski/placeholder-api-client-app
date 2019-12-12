package org.verzhbitski.placeholderapiclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.User
import org.verzhbitski.placeholderapiclient.view.adapter.viewholder.UserViewHolder

class UserListAdapter(private var data: List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    fun update(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }
}