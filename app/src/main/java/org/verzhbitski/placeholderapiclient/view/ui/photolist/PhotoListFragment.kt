package org.verzhbitski.placeholderapiclient.view.ui.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.photo_list_fragment.*
import org.verzhbitski.placeholderapiclient.databinding.PhotoListFragmentBinding
import org.verzhbitski.placeholderapiclient.view.adapter.PhotoListAdapter

class PhotoListFragment : Fragment() {
    companion object {
        fun newInstance() = PhotoListFragment()
    }

    private lateinit var viewDataBinding: PhotoListFragmentBinding

    private lateinit var photoListAdapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = PhotoListFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@PhotoListFragment, arguments?.getInt("albumId")?.let {
                PhotoListViewModelFactory(it)
            }).get(PhotoListViewModel::class.java)
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
        val gridLayoutManager = GridLayoutManager(context, 3)
        photoListRecyclerView.layoutManager = gridLayoutManager
        photoListAdapter = PhotoListAdapter(ArrayList())
        photoListRecyclerView.adapter = photoListAdapter
    }

    private fun setupObservers() {
        viewDataBinding.viewModel?.photoList?.observe(viewLifecycleOwner, Observer {
            photoListAdapter.update(it)
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