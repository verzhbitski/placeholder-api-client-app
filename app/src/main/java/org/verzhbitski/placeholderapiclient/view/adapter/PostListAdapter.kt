package org.verzhbitski.placeholderapiclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Post
import org.verzhbitski.placeholderapiclient.view.adapter.viewholder.PostViewHolder

class PostListAdapter(private var data: List<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    fun update(data: List<Post>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(data[position])
    }
}