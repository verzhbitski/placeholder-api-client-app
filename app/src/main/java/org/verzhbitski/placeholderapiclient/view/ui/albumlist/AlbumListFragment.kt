package org.verzhbitski.placeholderapiclient.view.ui.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.album_list_fragment.*
import org.verzhbitski.placeholderapiclient.databinding.AlbumListFragmentBinding
import org.verzhbitski.placeholderapiclient.view.adapter.AlbumListAdapter

private const val ARG_USER_ID = "userId"

class AlbumListFragment : Fragment() {

    companion object {
        fun newInstance(userId: Int) =
            AlbumListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_USER_ID, userId)
                }
            }
    }

    private var userId = 0

    private lateinit var viewDataBinding: AlbumListFragmentBinding

    private lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = AlbumListFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@AlbumListFragment,
                AlbumListViewModelFactory(userId)
            ).get(AlbumListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSwipeToRefresh()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        albumListRecyclerView.layoutManager = linearLayoutManager
        albumListRecyclerView.addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        albumListAdapter = AlbumListAdapter(ArrayList())
        albumListRecyclerView.adapter = albumListAdapter
    }

    private fun setupObservers() {
        viewDataBinding.viewModel?.albumList?.observe(viewLifecycleOwner, Observer {
            albumListAdapter.update(it)
        })

        viewDataBinding.viewModel?.refreshing?.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = it
        })

        viewDataBinding.viewModel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewDataBinding.viewModel?.fetch()
        }
    }
}
