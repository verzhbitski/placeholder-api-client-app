package org.verzhbitski.placeholderapiclient.view.ui.postlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostListViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostListViewModel(userId) as T
    }
}