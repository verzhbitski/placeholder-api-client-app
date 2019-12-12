package org.verzhbitski.placeholderapiclient.view.ui.albumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlbumListViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumListViewModel(userId) as T
    }
}