package org.verzhbitski.placeholderapiclient.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.item_user_name)
    private val username: TextView = itemView.findViewById(R.id.item_user_username)
    private val email: TextView = itemView.findViewById(R.id.item_user_email)
    private val phone: TextView = itemView.findViewById(R.id.item_user_phone)

    fun bind(user: User) {
        name.text = user.name
        username.text = user.username
        email.text = user.email
        phone.text = user.phone

        itemView.setOnClickListener {
            val bundle = bundleOf("userId" to user.id)
            itemView.findNavController().navigate(R.id.action_userListFragment_to_detailFragment, bundle)
        }
    }
}