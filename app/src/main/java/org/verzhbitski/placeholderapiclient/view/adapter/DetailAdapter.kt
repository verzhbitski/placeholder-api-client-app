package org.verzhbitski.placeholderapiclient.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.verzhbitski.placeholderapiclient.R
import org.verzhbitski.placeholderapiclient.view.ui.albumlist.AlbumListFragment
import org.verzhbitski.placeholderapiclient.view.ui.postlist.PostListFragment

class DetailAdapter(val context: Context?, fragmentManager: FragmentManager, userId: Int) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()

    init {
        fragmentList.add(PostListFragment.newInstance(userId))
        fragmentList.add(AlbumListFragment.newInstance(userId))
    }

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.getString(R.string.posts)
            1 -> context?.getString(R.string.albums)
            else -> null
        }
    }
}