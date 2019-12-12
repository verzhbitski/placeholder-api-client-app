package org.verzhbitski.placeholderapiclient.view.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.model.retrofit.Photo

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val photo: ImageView = itemView.findViewById(R.id.item_photo_photo)

    fun bind(item: Photo) {
        Glide.with(photo).load(item.thumbnailUrl).into(photo)
    }
}