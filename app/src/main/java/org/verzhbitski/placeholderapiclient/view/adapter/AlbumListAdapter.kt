package org.verzhbitski.placeholderapiclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Album
import org.verzhbitski.placeholderapiclient.view.adapter.viewholder.AlbumViewHolder

class AlbumListAdapter(private var data: List<Album>) : RecyclerView.Adapter<AlbumViewHolder>() {

    fun update(data: List<Album>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(data[position])
    }
}