package org.verzhbitski.placeholderapiclient.view.adapter.viewholder

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Album

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.item_album_title)

    fun bind(album: Album) {

        title.text = album.title

        itemView.setOnClickListener {
            val bundle = bundleOf("albumId" to album.id)
            itemView.findNavController().navigate(R.id.action_detailFragment_to_photoListFragment, bundle)
        }
    }
}