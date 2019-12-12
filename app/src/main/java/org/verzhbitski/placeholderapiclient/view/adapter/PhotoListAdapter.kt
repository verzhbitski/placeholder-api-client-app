package org.verzhbitski.placeholderapiclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Photo
import org.verzhbitski.placeholderapiclient.view.adapter.viewholder.PhotoViewHolder

class PhotoListAdapter(private var data: List<Photo>) : RecyclerView.Adapter<PhotoViewHolder>() {

    fun update(data: List<Photo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)

        return PhotoViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(data[position])
    }

}