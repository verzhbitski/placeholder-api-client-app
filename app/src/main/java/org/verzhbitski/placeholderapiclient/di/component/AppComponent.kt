package org.verzhbitski.placeholderapiclient.di.component

import dagger.Component
import org.verzhbitski.placeholderapiclient.di.module.RetrofitModule
import org.verzhbitski.placeholderapiclient.view.ui.albumlist.AlbumListViewModel
import org.verzhbitski.placeholderapiclient.view.ui.photolist.PhotoListViewModel
import org.verzhbitski.placeholderapiclient.view.ui.postlist.PostListViewModel
import org.verzhbitski.placeholderapiclient.view.ui.userlist.UserListViewModel
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface AppComponent {

    fun inject(userListViewModel: UserListViewModel)

    fun inject(postListViewModel: PostListViewModel)

    fun inject(photoListViewModel: PhotoListViewModel)

    fun inject(albumListViewModel: AlbumListViewModel)
}