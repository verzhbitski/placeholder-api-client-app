package org.verzhbitski.placeholderapiclient.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Post

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleText = itemView.findViewById<TextView>(R.id.item_user_title)
    var bodyText = itemView.findViewById<TextView>(R.id.item_user_body)

    fun bind(post: Post) {
        titleText.text = post.title
        bodyText.text = post.body
    }
}