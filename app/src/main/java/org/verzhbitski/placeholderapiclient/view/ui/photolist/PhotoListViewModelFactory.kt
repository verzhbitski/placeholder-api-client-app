package org.verzhbitski.placeholderapiclient.view.ui.photolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotoListViewModelFactory(private val albumId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoListViewModel(albumId) as T
    }
}